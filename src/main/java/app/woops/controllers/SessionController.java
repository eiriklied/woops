package app.woops.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.woops.domain.User;
import app.woops.repositories.UserRepository;
import app.woops.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    @RequestMapping("/sessions/new")
    public String login() {
        return "sessions/new";
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    public String create(HttpServletRequest request, HttpServletResponse response) {
        User user = findOrCreateUser(request);
        sessionService.login(user, response);
        return "redirect:/";
    }

    @RequestMapping("/sessions/destroy")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        sessionService.logout(request, response);
        return "redirect:/";
    }

    private User findOrCreateUser(HttpServletRequest request) {
        User user = userRepository.findByPhone(request.getParameter("phone"));
        if(user == null) {
            user = userRepository.save(new User(request.getParameter("phone")));
        }
        return user;
    }

}
