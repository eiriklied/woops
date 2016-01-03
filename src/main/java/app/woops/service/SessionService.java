package app.woops.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.woops.domain.Session;
import app.woops.domain.User;
import app.woops.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void login(User user, HttpServletResponse response) {
        Session session = sessionRepository.save(new Session(user));
        response.addCookie(new Cookie("_woops_session", session.getSessionKey()));
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Session session = getSession(request);
        sessionRepository.delete(session);
        Cookie cookie = new Cookie("_woops_session", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public boolean isLoggedIn(HttpServletRequest request) {
        return getCurrentUser(request) != null;
    }

    public User getCurrentUser(HttpServletRequest request) {
        Session currentSession = getSession(request);
        return currentSession != null ? currentSession.getUser() : null;
    }

    public Session getSession(HttpServletRequest request) {
        return sessionRepository.findBySessionKey(sessionKey(request));
    }

    private String sessionKey(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "_woops_session");
        return cookie != null ? cookie.getValue() : null;
    }
}
