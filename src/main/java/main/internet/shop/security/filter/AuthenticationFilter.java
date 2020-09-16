package main.internet.shop.security.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.service.UserService;

public class AuthenticationFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getServletPath();
        if (url.equals("/login") || url.equals("/registration") || url.equals("/")) {
            filterChain.doFilter(request, response);
            return;
        }

        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
