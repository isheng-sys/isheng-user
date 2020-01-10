package com.isheng.hpb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = { "com.isheng.hpb" })
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
