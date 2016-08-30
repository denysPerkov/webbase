package i18n.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocaleManager {

    Locale getLocale(HttpServletRequest req);

    void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale l);

}

