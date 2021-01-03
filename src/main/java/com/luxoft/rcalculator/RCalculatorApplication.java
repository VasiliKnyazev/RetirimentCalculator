package com.luxoft.rcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class RCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RCalculatorApplication.class, args);
	}

}
