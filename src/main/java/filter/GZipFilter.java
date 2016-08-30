package filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import filter.util.ResponseWrapper;


public class GZipFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(GZipFilter.class);

    @Override
    public void destroy() {
        logger.trace("GZip filter destroyed.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        if (!isGzipSupported(httpRequest)) {
            ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);
            chain.doFilter(httpRequest, httpResponse);
            String responseEncoding = responseWrapper.getCharacterEncoding();

            if (isText(httpRequest)) {
                httpResponse.setHeader("Content-Encoding", "gzip");
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(
                        httpResponse.getOutputStream());
                if (responseWrapper.isStream()) {
                    gzipOutputStream.write(responseWrapper.getBytes());
                }
                if (responseWrapper.isWriter()) {
                    gzipOutputStream.write(responseWrapper.toString().getBytes(responseEncoding));
                }
                gzipOutputStream.close();
            } else {
                httpResponse.getOutputStream().write(responseWrapper.getBytes());
                httpResponse.getOutputStream().close();
            }
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }

    }

    private boolean isText(final HttpServletRequest req) {
        String contentType = req.getContentType();
        return contentType != null
                && ((contentType.startsWith("text/") || contentType.endsWith("javascript")));
    }

    private boolean isGzipSupported(final HttpServletRequest req) {
        String encodings = req.getHeader("Accept-Encoding");
        String encodeFlag = req.getParameter("encoding");
        if (encodings != null && encodings.contains("gzip") && !"none".equals(encodeFlag)) {
            logger.trace("Browser supports gzip");
            return true;
        }
        logger.trace("Browser is not support gzip");
        return false;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.trace("GZip filter initialized.");
    }
}