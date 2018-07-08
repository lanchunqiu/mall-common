package com.lancq.commom.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lancq
 * @Description
 * @Date 2018/7/5
 **/
public class CookieUtil {
    private static final Log log = LogFactory.getLog(CookieUtil.class);

    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public static Cookie genCookieWithDomain(String key, String value, int maxAge, String domain){
        Cookie cookie = new Cookie(key, value);
        enrichCookie(cookie, "/", maxAge, domain);
        return cookie;
    }

    public static Cookie genCookie(String key, String value, String uri, int maxAge, String domain){
        Cookie cookie = new Cookie(key, value);
        enrichCookie(cookie, uri, maxAge, domain);
        return cookie;
    }

    public static void enrichCookie(Cookie cookie, String uri, int maxAge, String domain){
        log.debug("cookie path is=[" + uri + "], maxAge=[" + maxAge + "], domain=[" + domain + "]");
        cookie.setPath(uri);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
    }

    public static void setCookie(HttpServletResponse response, Cookie cookie){
        response.addCookie(cookie);
    }


    public static boolean isAjax(HttpServletRequest request){
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
