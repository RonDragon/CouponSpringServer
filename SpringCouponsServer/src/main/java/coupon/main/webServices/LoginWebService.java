package coupon.main.webServices;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import coupon.main.beans.User;


import coupon.main.services.LoginService;

@RestController
@RequestMapping("login")
public class LoginWebService {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(method=RequestMethod.POST)
	public User login (@RequestBody User user , HttpServletRequest request ) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		session = request.getSession();		
		User newUser = loginService.login(user.getUserName(), user.getPassword(), user.getClientType());	
		session.setAttribute("user", newUser);		
		return newUser ;
	}
	
	
	
	@GetMapping(path="logout")
	public ResponseEntity<?> logout( HttpServletRequest HttpRequest){
		HttpSession session = HttpRequest.getSession(false);
		if(session!=null) {
			session.invalidate();
			
		}
		
		return new ResponseEntity<String>("logged out succsefuly", HttpStatus.OK);
		
	}

}
