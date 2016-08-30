package filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import filter.util.HttpRequestWrapper;
import i18n.locale.LocaleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constant;


public class LocalizationFilter implements Filter {

    private static final String LOCALE = "en";

    private ServletContext context;
    private LocaleManager localeManager;
    private Locale defaultLocale;
    private String rawLocaleString;

    @Override
    public void init(FilterConfig config) throws ServletException {
        localeManager = (LocaleManager) config.getServletContext().getAttribute(Constant.LOCALE_MANAGER);
        context = config.getServletContext();
        defaultLocale = new Locale(context.getInitParameter(Constant.LOCALE_DEFAULT));
        rawLocaleString = context.getInitParameter(Constant.LOCALES);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        Locale userLocale = (Locale) httpRequest.getSession().getAttribute(LOCALE);
        if (userLocale == null) {
            userLocale = localeManager.getLocale(httpRequest);
        }
        if (userLocale == null) {
            userLocale = getLocale(httpRequest, httpResponse);
        }
        String langParam = httpRequest.getParameter(Constant.USER_QUERY_LANGUAGE);
        if (langParam != null) {
            userLocale = new Locale(langParam);
        }

        if(getSelectedLang(httpRequest) != null){
            userLocale = new Locale(getSelectedLang(httpRequest));
        }
        Config.set(((HttpServletRequest) req).getSession(), Config.FMT_LOCALE, userLocale);

        localeManager.setLocale(httpRequest, httpResponse, userLocale);
        chain.doFilter(new HttpRequestWrapper(httpRequest, userLocale), resp);
    }

    public Locale getLocale(HttpServletRequest req, HttpServletResponse resp) {
        Locale locale = null;
        ArrayList<Locale> browserLocales = null;
        ArrayList<Locale> locales;
        Locale userLocale = localeManager.getLocale(req);

        if (userLocale != null) {
            return userLocale;
        }
        if (req.getLocales() == null || !req.getLocales().hasMoreElements()) {
            return defaultLocale;
        }
        locales = getLocales();
        browserLocales = Collections.list(req.getLocales());
        Iterator<Locale> iterator = browserLocales.iterator();
        while (iterator.hasNext()) {
            locale = iterator.next();
            if (locales.contains(locale)) {
                localeManager.setLocale(req, resp, locale);
                return locale;
            }
        }
        return (locale == null) ? defaultLocale : locale;
    }

    public ArrayList<Locale> getLocales() {
        String[] localesArray = rawLocaleString.split(",");
        ArrayList<Locale> locales = new ArrayList<>();
        for (String localeItem : localesArray) {
            locales.add(new Locale(localeItem));
        }
        return locales;
    }

    private String getSelectedLang(HttpServletRequest req){
        if(req.getParameter("lang" ) != null && !req.getParameter("lang").equals("")){
            return req.getParameter("lang");
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}
