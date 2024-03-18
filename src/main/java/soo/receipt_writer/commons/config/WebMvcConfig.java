package soo.receipt_writer.commons.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import soo.receipt_writer.commons.interceptor.AuthInterceptor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "/login/**", "/register/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(3600, TimeUnit.SECONDS))
                .setEtagGenerator((resource) -> {
                    try {
                        InputStream inputStream = resource.getInputStream();
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] dataBytes = new byte[1024];

                        int nread;
                        while ((nread = inputStream.read(dataBytes)) != -1) {
                            md.update(dataBytes, 0, nread);
                        }

                        byte[] mdBytes = md.digest();

                        BigInteger no = new BigInteger(1, mdBytes);
                        String md5 = no.toString(16);
                        while (md5.length() < 32) {
                            md5 = "0" + md5;
                        }

                        inputStream.close();

                        return md5;
                    } catch (IOException | NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
