package filter.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class HttpRequestWrapper extends HttpServletRequestWrapper {

    private final Locale locale;
    private final Locale currentLocale;

    public HttpRequestWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        currentLocale = locale;
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return currentLocale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(Arrays.asList(locale));
    }

    @Override
    public String getHeader(String name) {
        if ((name == null) || !name.equals("Accept-Language")) {
            return super.getHeader(name);
        }
        return currentLocale.toLanguageTag();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if ((name == null) || !name.equals("Accept-Language")) {
            return super.getHeaders(name);
        }
        return Collections.enumeration(Arrays.asList(currentLocale.toLanguageTag()));
    }
}