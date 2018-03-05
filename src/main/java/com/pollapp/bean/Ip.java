package com.pollapp.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class Ip {

    public static String remote() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String remote = request.getHeader("X-FORWARDED-FOR");
        if (remote == null || "".equals(remote)) {
            return request.getRemoteAddr();
        }
        return remote;
    }
}