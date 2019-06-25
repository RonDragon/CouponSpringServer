package coupon.main;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import coupon.main.exception.CouponSystemException;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws CouponSystemException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		
	}

}

