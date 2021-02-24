package com.competition.game.webservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		for(int i=0;i<10;i++) {
			// String encodeString = encoder.encode("AmitSinha!@");
			String encodeString = encoder.encode("Amit");
			System.out.println(encodeString);
		}
		
	}

	
}
