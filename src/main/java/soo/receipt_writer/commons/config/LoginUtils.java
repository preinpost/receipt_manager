package soo.receipt_writer.commons.config;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import soo.receipt_writer.users.LoginSession;

public class LoginUtils {
    static public LoginSession loginSession() {
        return (LoginSession) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession().getAttribute("loginSession");
    }
}
