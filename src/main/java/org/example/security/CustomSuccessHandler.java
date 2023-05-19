package org.example.security;

import org.example.constant.UserConstant;
import org.example.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUtl = determineTargetUrl(authentication);
        if (response.isCommitted()) return;
        redirectStrategy.sendRedirect(request, response, targetUtl);
    }



    private String determineTargetUrl (Authentication authentication) {
        String targetUrl = "";
        List<String> authorities = SecurityUtils.getAuthorities();
        if (isAdmin(authorities)) targetUrl = "/dashboard";
        else if (isCustomer(authorities)) targetUrl = "/home";
        return targetUrl;
    }

    private boolean isAdmin (List<String> roles) {
        return roles.contains(UserConstant.ADMIN);
    }
    private boolean isCustomer (List<String> roles) {
        return roles.contains(UserConstant.CUSTOMER);
    }
}
