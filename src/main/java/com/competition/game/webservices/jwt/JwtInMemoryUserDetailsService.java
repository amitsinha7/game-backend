package com.competition.game.webservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "user01",
				"$2a$10$sVBbiYsJua6BvlNDdQME/uR.qdc4WeLRPJdWhviGZamzqvMFxkHiK", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(1L, "user02",
				"$2a$10$.wWl7y2ZR0J1TxtYA.Pb8OhJU3REq9fmX6BfFrDJVaH60R82uL.Om", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(1L, "user03",
				"$2a$10$xdlKw9/vKYJkJKuSSlWg1Od5PUBUbtVnGz6NkyLlhd.hn/uH2VWke", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(1L, "amitsinha",
				"$2a$10$28z9Z/ooKIyqmF5ZVVaCVuiWOUw/foshmfoPoYCgbQcvksD7rDcHu", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(1L, "amit",
				"$2a$10$y/JglEJyKXs10VL7bZcfru3b9KW8zdt./1QtEsCsB5n/P7L5nrm5a", "ROLE_USER_2"));

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
