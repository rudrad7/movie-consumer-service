package com.cg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class MovieConsumerService2Application {

	public static void main(String[] args) {
		SpringApplication.run(MovieConsumerService2Application.class, args);
		System.out.println("MovieConsumerService strted on 9092");
	}
	@Bean
	//@LoadBalanced
	//@Primary
	public RestTemplate getRestTemplet()
	{
		return new RestTemplate();
	}

}
