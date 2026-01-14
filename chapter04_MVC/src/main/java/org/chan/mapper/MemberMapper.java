package org.chan.mapper;

import org.chan.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
}
