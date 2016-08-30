package i18n.locale.impl;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import i18n.locale.LocaleManager;
import util.Constant;


public class CookieLocaleManager implements LocaleManager {

	private final int maxAge;

	public CookieLocaleManager(int maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public Locale getLocale(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookieItem : cookies) {
			if (cookieItem.getName().equals(Constant.USER_LOCALE)) {
				return new Locale(cookieItem.getValue());
			}
		}
		return null;
	}

	@Override
	public void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale locale) {
		Cookie cookie = new Cookie(Constant.USER_LOCALE, locale.toLanguageTag());
		cookie.setMaxAge(maxAge);
		resp.addCookie(cookie);
	}

}
