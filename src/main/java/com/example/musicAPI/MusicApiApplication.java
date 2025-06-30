package com.example.musicAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MusicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicApiApplication.class, args);
	}

}
