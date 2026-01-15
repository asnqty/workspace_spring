package org.chan.security;

import org.chan.domain.MemberVO;
import org.chan.mapper.MemberMapper;
import org.chan.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("loag user by username : " + username);
		
		MemberVO mvo = mapper.read(username);
		
		log.warn("mapper : " + mvo);
		
		return mvo == null ? null : new CustomUser(mvo);
	}
}
