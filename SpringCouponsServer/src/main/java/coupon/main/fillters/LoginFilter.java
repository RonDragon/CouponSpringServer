package coupon.main.fillters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coupon.main.beans.User;

@WebFilter("/rest/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException  {		
		
		System.out.println("filtering.....");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		HttpSession session = request.getSession(false);
		
		if(session == null ) {
			response.sendError(401, "You are not logged in !");
			return;
		}
		User loggedUser=  (User) request.getSession(false).getAttribute("user");
		
		if(loggedUser == null) {
			response.sendError(401, "You are not logged in !");
			return;
		}
		
		System.out.println("filter has been done !");
		
		chain.doFilter(request, servletResponse);
		
		
		
		
	}

}