package app.woops.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import app.woops.domain.Transfer;
import app.woops.repositories.TransferRepository;
import app.woops.repositories.UserRepository;
import app.woops.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransferController {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping({"/", "/transfers"})
    public ModelAndView home(HttpServletRequest request) {
        Iterable<Transfer> transfers = transferRepository.findAllToAndFromUser(sessionService.getCurrentUser(request));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transfers", transfers);

        return new ModelAndView("transfers/index", model);
    }

    @RequestMapping("/transfers/new")
    public String newAd() {
        return "/transfers/new";
    }

    @RequestMapping(value = "/transfers", method = RequestMethod.POST)
    public String create(HttpServletRequest request) {
        Transfer transfer = new Transfer(
                sessionService.getCurrentUser(request),
                userRepository.findByPhone(request.getParameter("to")),
                Integer.parseInt(request.getParameter("amount")),
                request.getParameter("comment")
        );
        transferRepository.save(transfer);
        return "redirect:/";
    }

}
