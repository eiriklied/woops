package app.woops.controllers;

import javax.servlet.http.HttpServletRequest;

import app.woops.domain.User;
import app.woops.repositories.UserRepository;
import app.woops.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    @RequestMapping("/profile")
    public String profile() {
        return "profile/show";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(HttpServletRequest request) {
        User currentUser = sessionService.getCurrentUser(request);
        currentUser.setName(request.getParameter("name"));
        userRepository.save(currentUser);

        return "redirect:/profile";
    }
}
