package web.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date: 2/4/13
 * Time: 8:29 PM
 */

@WebFilter(filterName = "UserRedirectFilter")
public class UserRedirectFilter implements Filter{

    private String contextPath;

    public UserRedirectFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getUserPrincipal() != null) {

            httpResponse.sendRedirect(contextPath + "/auth/home.xhtml");
            return; //Added not in Vineet Code
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        contextPath = filterConfig.getServletContext().getContextPath();


    }

    public void destroy() {

    }


}
