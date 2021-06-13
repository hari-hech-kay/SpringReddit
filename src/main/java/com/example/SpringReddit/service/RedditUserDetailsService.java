package com.example.SpringReddit.service;

import com.example.SpringReddit.model.RedditUser;
import com.example.SpringReddit.repository.RedditUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class RedditUserDetailsService implements UserDetailsService {

	private final RedditUserRepository userRepository;
	private final String USER_ROLE = "ROLE_USER";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RedditUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.isEnabled(),
				true, true, true,
				getAuthorities());
	}

	private Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(USER_ROLE));
	}
}
