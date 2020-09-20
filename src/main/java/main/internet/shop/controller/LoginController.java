package main.internet.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.internet.shop.exception.AuthenticationException;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.User;
import main.internet.shop.model.role.UserRole;
import main.internet.shop.security.AuthenticationService;

public class LoginController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private static final String USER_ID = "userId";
    private Map<Set<UserRole>, String> urlMap;
    private AuthenticationService authenticationService = (AuthenticationService)
            injector.getInstance(AuthenticationService.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        urlMap = new HashMap<>();
        urlMap.put(new HashSet<>(Set.of(UserRole.of("ADMIN"))), "/admin/main-menu");
        urlMap.put(new HashSet<>(Set.of(UserRole.of("USER"))), "/main-menu");
        urlMap.put(new HashSet<>(Set.of(UserRole.of("USER"), UserRole.of("ADMIN"))),
                "/general-main-menu");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        User user;

        try {
            user = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute(USER_ID, user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("errMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + urlMap.get(user.getUserRoles()));
    }
}
