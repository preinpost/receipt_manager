package soo.receipt_writer.commons.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import soo.receipt_writer.users.LoginSession;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");

        if (loginSession == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
