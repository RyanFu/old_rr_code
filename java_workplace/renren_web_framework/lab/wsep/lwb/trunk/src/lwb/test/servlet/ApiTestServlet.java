package lwb.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiTestServlet extends BaseTestServlet {

	private static final long serialVersionUID = 1L;

	public static final String CRLF = "\r\n";
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String method = request.getMethod();
		String protocol = request.getProtocol();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		if (queryString != null) {
			uri += "?" + queryString;
		}
		
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.append(method);
		out.append(' ');
		out.append(uri);
		out.append(' ');
		out.append(protocol);
		out.append(CRLF);
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			Enumeration<String> headers = request.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				out.append(headerName);
				out.append(": ");
				out.append(headerValue);
				out.append(CRLF);
			}
		}
		
		out.append(CRLF);
		
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Entry<String, String[]> entry : paramMap.entrySet()) {
			for (String value : entry.getValue()) {
				out.append(entry.getKey());
				out.append("=");
				out.append(value);
				out.append("&");
				//不编码，不考虑末尾的&了
			}
		}
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
