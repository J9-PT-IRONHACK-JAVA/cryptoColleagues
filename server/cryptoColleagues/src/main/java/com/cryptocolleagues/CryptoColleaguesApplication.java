package com.cryptocolleagues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients
public class CryptoColleaguesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoColleaguesApplication.class, args);
	}

}
