package main.internet.shop.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.internet.shop.lib.Injector;
import main.internet.shop.model.User;
import main.internet.shop.model.role.UserRole;
import main.internet.shop.service.UserService;

public class AuthorisationFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final Injector injector =
            Injector.getInstance("main.internet.shop");
    private Map<String, List<UserRole>> protectedUrls;
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        protectedUrls = new HashMap<>();
        protectedUrls.put("/user/all", List.of(UserRole.of("ADMIN")));
        protectedUrls.put("/user/delete", List.of(UserRole.of("ADMIN")));
        protectedUrls.put("/product/add", List.of(UserRole.of("ADMIN")));
        protectedUrls.put("/admin/delete-order", List.of(UserRole.of("ADMIN")));
        protectedUrls.put("/admin/products", List.of(UserRole.of("ADMIN")));
        protectedUrls.put("/admin/product-delete", List.of(UserRole.of("ADMIN")));
        protectedUrls.put("/product/all", List.of(UserRole.of("USER")));
        protectedUrls.put("/product/buy", List.of(UserRole.of("USER")));
        protectedUrls.put("/shopping-cart/products", List.of(UserRole.of("USER")));
        protectedUrls.put("/product/success-buying", List.of(UserRole.of("USER")));
        protectedUrls.put("/shopping-cart/delete-item", List.of(UserRole.of("USER")));
        protectedUrls.put("/order/user-orders", List.of(UserRole.of("USER")));
        protectedUrls.put("/order/create", List.of(UserRole.of("USER")));
        protectedUrls.put("/order/details", List.of(UserRole.of("USER")));
        protectedUrls.put("/main-menu", List.of(UserRole.of("USER")));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestedUrl = request.getServletPath();

        if (protectedUrls.get(requestedUrl) == null) {
            chain.doFilter(request, response);
            return;
        }

        User user = userService.get((Long)request.getSession().getAttribute(USER_ID));
        if (isAuthorized(user, protectedUrls.get(requestedUrl))) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/access-denied.jsp")
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, List<UserRole> authorizedRoles) {
        for (UserRole authorizedRole : authorizedRoles) {
            for (UserRole userRole : user.getUserRoles()) {
                if (authorizedRole.equals(userRole)) {
                    return true;
                }
            }
        }
        return false;
    }
}
