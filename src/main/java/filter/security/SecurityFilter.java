package filter.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;


import model.User;


import org.xml.sax.SAXException;



public class SecurityFilter implements Filter {

	private String xmlSecurityFile;
	private SecurityManager securityManager = null;

	public SecurityFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestPath = httpRequest.getRequestURI().replaceAll(httpRequest.getContextPath(), "");

		if (!securityManager.containInConstraints(requestPath)) {
			chain.doFilter(request, response);
		} else {
			User user = (User) httpRequest.getSession().getAttribute("user");

			if (user == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
				return;
			}
			if (securityManager.checkConstraintForRole(user, requestPath)) {
				chain.doFilter(httpRequest, response);
			} else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/noAuth");
				return;
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		xmlSecurityFile = filterConfig.getInitParameter("XmlSecurityFile");

		try {
			XMLSecurityParser xmParser = new XMLSecurityParser();
			ArrayList<Constraints> constraintsList = xmParser.parse(xmlSecurityFile);
			securityManager = new SecurityManager(constraintsList);

			if (securityManager == null) {
				throw new IOException("Fail create security manager.");
			}
		} catch (SAXException | ParserConfigurationException | IOException e) {
			throw new ServletException(e);
		}
	}
}
