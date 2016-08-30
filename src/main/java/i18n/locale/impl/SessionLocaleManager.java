package i18n.locale.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import i18n.locale.LocaleManager;
import util.Constant;

public class SessionLocaleManager implements LocaleManager {

    @Override
    public Locale getLocale(HttpServletRequest req) {
        return (Locale) req.getSession().getAttribute(Constant.USER_LOCALE);
    }

    @Override
    public void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale userLocale) {
        req.getSession().setAttribute(Constant.USER_LOCALE, userLocale);
    }

}
