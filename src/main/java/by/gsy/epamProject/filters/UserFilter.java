package by.gsy.epamProject.filters;

import by.gsy.epamProject.controller.ConstantsController;
import by.gsy.epamProject.model.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(ConstantsController.Fields.USER);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(ConstantsController.Pages.LOGIN);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
