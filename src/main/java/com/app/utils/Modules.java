package com.app.utils;

import com.app.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Modules {

    public static void setUserCookies(HttpServletResponse response, User user) {
        Cookie nameCookie = new Cookie(Constants.COOKIE_USER_NAME, user.getName());
        Cookie emailCookie = new Cookie(Constants.COOKIE_USER_EMAIL, user.getEmail());
        Cookie passwordCookie = new Cookie(Constants.COOKIE_USER_PASSWORD, user.getPassword());
        Cookie idCookie = new Cookie(Constants.COOKIE_USER_ID, String.valueOf(user.getId()));

        // durée de vie des cookies
        int maxAge = 7 * 24 * 60 * 60; //7 jours
        nameCookie.setMaxAge(maxAge);
        emailCookie.setMaxAge(maxAge);
        passwordCookie.setMaxAge(maxAge);
        idCookie.setMaxAge(maxAge);

        // Enregistrer les cookies
        response.addCookie(nameCookie);
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        response.addCookie(idCookie);
    }

    public static User getUserFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        User user = new User();

        for (Cookie cookie : cookies) {
            switch (cookie.getName()) {
                case Constants.COOKIE_USER_NAME:
                    user.setName(cookie.getValue());
                    break;
                case Constants.COOKIE_USER_EMAIL:
                    user.setEmail(cookie.getValue());
                    break;
                case Constants.COOKIE_USER_PASSWORD:
                    user.setPassword(cookie.getValue());
                    break;
                case Constants.COOKIE_USER_ID:
                    user.setId(Integer.parseInt(cookie.getValue()));
                    break;
            }
        }

        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            return null;
        }

        return user;
    }
    public static void removeUserCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case Constants.COOKIE_USER_NAME:
                    case Constants.COOKIE_USER_EMAIL:
                    case Constants.COOKIE_USER_PASSWORD:
                    case Constants.COOKIE_USER_ID:
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                }
            }
        }
    }

}
