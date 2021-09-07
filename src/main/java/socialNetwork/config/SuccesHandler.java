//package socialNetwork.config;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//class SuccesHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        String url = "";
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        List<String> roles = new ArrayList<>();
//        for (GrantedAuthority a : authorities) {
//            roles.add(a.getAuthority());
//        }
//
//        String role = roles.get(0);
//        if (role.equals("ROLE_USER")) {
//            url = "/user";
//        } else if (role.equals("ROLE_ADMIN")) {
//            url = "/admin";
//        } else {
//            url = "/khongcoquyen";
//        }
//
//        redirectStrategy.sendRedirect(request, response, url);
//    }
//
//}
