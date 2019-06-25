package coupon.main.tasks;

import java.sql.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import coupon.main.repositories.CouponRepository;

public class Task {

	private boolean running;
	private Thread thread;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@PostConstruct
	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(running) {
					couponRepository.removeExpiredCoupons(new Date (System.currentTimeMillis()));
					try {
						Thread.sleep(1000*60*60*24);
					}catch (InterruptedException e) {
						System.err.print("Error In Daily Task");
					}
				}
				
			}
			
		});
		thread.start();
	}
	
}
