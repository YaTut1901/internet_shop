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
    private Map<String, List<UserRole.RoleName>> protectedUrls;
    private UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        protectedUrls = new HashMap<>();
        protectedUrls.put("/user/all", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/user/delete", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/product/add", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/admin/delete-order", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/admin/products", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/admin/product-delete", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/admin/orders", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/admin/main-menu", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/inject", List.of(UserRole.RoleName.ADMIN));
        protectedUrls.put("/product/all", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/product/buy", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/shopping-cart/products", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/product/success-buying", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/shopping-cart/delete-item", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/order/user-orders", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/order/create", List.of(UserRole.RoleName.USER));
        protectedUrls.put("/order/details", List.of(UserRole.RoleName.USER,
                UserRole.RoleName.ADMIN));
        protectedUrls.put("/main-menu", List.of(UserRole.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestedUrl = request.getServletPath();

        if (!protectedUrls.containsKey(requestedUrl)) {
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

    private boolean isAuthorized(User user, List<UserRole.RoleName> authorizedRoles) {
        for (UserRole.RoleName authorizedRole : authorizedRoles) {
            for (UserRole.RoleName roleName : user.getUserRolesNames()) {
                if (authorizedRole.equals(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
