package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CacheFilter.class);

    @Override
    public void destroy() {
        logger.info("CacheFilter destroyed");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, final FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse) resp).setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate, max-age=0");
        logger.trace("Cache control: off.");
        ((HttpServletResponse) resp).setDateHeader("Expires", 0);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.info("CacheFilter initialized");
    }
}
