package spring.coupons;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import coupon_system.CouponSystem;

@SpringBootApplication
public class Application {
	
	@PostConstruct
	public void init() {
		try {
			CouponSystem.getInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
