package org.chan.service;

import java.util.List;

import org.chan.domain.ReplyVO;

public interface ReplyService {
	
	// 댓글 삽입
	public int register(ReplyVO rvo);
	
	// 댓글 목록
	public List<ReplyVO> getList(int bno);
	
	// 댓글 읽기
	public ReplyVO get(int rno);
	
	// 댓글 삭제
	public boolean remove(int rno);
	
	// 댓글 수정
	public boolean modify(ReplyVO rvo);
}
