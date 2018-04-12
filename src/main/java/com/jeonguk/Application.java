package com.jeonguk;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Aspect
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
