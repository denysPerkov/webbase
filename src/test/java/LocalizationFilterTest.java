import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filter.LocalizationFilter;
import filter.util.HttpRequestWrapper;
import i18n.locale.LocaleManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;
import util.Constant;


@RunWith(Parameterized.class)
public class LocalizationFilterTest {

    private final String browserLocales;
    private final String userRequestLocale;
    private final String userQueryLocale;
    private final String xmlLocales;
    private final String defaultXmlLocale;
    private final String userLocale;

    private LocaleManager localeManager;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;
    private ServletContext servletContext;
    private HttpSession session;
    private FilterConfig filterConfig;

    @Parameters
    public static Collection<Object[]> initLocalesCollection() {
        return Arrays.asList(new Object[][] { { "en,uk", null, null, "en,uk", "en", "en" }, // en
                { "uk", null, null, "en,uk", "en", "uk" }, // uk
                { "am,ak", null, null, "en,uk", "en", "en" }, // en
                { "am,ak", "uk", null, "en,uk", "en", "uk" }, // uk
                { "am,ak", null, "uk", "en,uk", "en", "uk" }, // uk
                { "am,ak", "en", "uk", "en,uk", "en", "uk" }, // en
                { null, null, null, "en,uk", "en", "en" }, // en
        });
    }

    public LocalizationFilterTest(String browserLocales, String userRequestLocale,
                                  String userQueryLocale, String xmlLocales, String defaultXmlLocale, String userLocale) {
        this.browserLocales = browserLocales;
        this.userRequestLocale = userRequestLocale;
        this.userQueryLocale = userQueryLocale;
        this.xmlLocales = xmlLocales;
        this.defaultXmlLocale = defaultXmlLocale;
        this.userLocale = userLocale;
    }

    @Before
    public void init() {
        localeManager = mock(LocaleManager.class);
        filterConfig = mock(FilterConfig.class);
        servletContext = mock(ServletContext.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        session = mock(HttpSession.class);

        when(filterConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(Constant.LOCALES)).thenReturn(xmlLocales);
        when(servletContext.getInitParameter(Constant.LOCALE_DEFAULT))
                .thenReturn(defaultXmlLocale);
        when(servletContext.getInitParameter(Constant.LOCALE_MODE)).thenReturn(
                Constant.LOCALE_SESSION);
        when(request.getParameter(Constant.USER_QUERY_LANGUAGE)).thenReturn(userQueryLocale);
        when(request.getParameter(Constant.USER_BROWSER_LANGUAGES)).thenReturn(browserLocales);
        when(request.getSession()).thenReturn(session);
        when(request.getLocales()).thenReturn(getLocales());
        when(session.getAttribute("locale")).thenReturn(userRequestLocale);
        when(localeManager.getLocale(any(HttpServletRequest.class))).thenReturn(
                localeFromString(userLocale));
    }

    private Enumeration<Locale> getLocales() {
        String rawLocaleString = "en,uk";
        String[] localesArray = rawLocaleString.split(",");
        ArrayList<Locale> locales = new ArrayList<>();
        for (String localeItem : localesArray) {
            locales.add(new Locale(localeItem));
        }
        return Collections.enumeration(locales);
    }

    @Test
    public void test() throws IOException, ServletException {
        when(filterConfig.getServletContext()).thenReturn(servletContext);
        LocalizationFilter localizationFilter = new LocalizationFilter();
        when(servletContext.getAttribute(Constant.LOCALE_MANAGER)).thenReturn(localeManager);
        localizationFilter.init(filterConfig);
        localizationFilter.doFilter(request, response, filterChain);
        ArgumentCaptor<HttpServletRequest> argument = ArgumentCaptor
                .forClass(HttpServletRequest.class);
        verify(filterChain).doFilter(argument.capture(), any(ServletResponse.class));
        assertEquals(localeFromString(userLocale),
                ((HttpRequestWrapper) argument.getValue()).getLocale());
    }

    private Locale localeFromString(String locale) {
        if (locale != null) {
            return new Locale(locale);
        }
        return null;
    }

}
