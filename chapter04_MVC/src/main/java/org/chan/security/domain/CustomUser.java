package org.chan.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.chan.domain.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(MemberVO mvo) {
        super(
            mvo.getUserId(),
            mvo.getUserPw(),
            mvo.getAuthList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                .collect(Collectors.toList())
        );
        this.member = mvo;
    }
}
