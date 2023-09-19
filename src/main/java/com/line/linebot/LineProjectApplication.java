package com.line.linebot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler

public class LineProjectApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(LineProjectApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
}
