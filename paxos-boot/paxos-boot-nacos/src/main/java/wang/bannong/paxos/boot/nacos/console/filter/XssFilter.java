package wang.bannong.paxos.boot.nacos.console.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.filter.OncePerRequestFilter;

public class XssFilter extends OncePerRequestFilter {

	private static final String CONTENT_SECURITY_POLICY_HEADER = "Content-Security-Policy";

	private static final String CONTENT_SECURITY_POLICY = "script-src 'self'";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.setHeader(CONTENT_SECURITY_POLICY_HEADER, CONTENT_SECURITY_POLICY);
		filterChain.doFilter(request, response);
	}

}
