package org.chan.service;

import java.util.List;

import org.chan.domain.ReplyVO;
import org.chan.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper mapper;
	
	// 댓글 삽입
	@Override
	public int register(ReplyVO rvo) {
		log.info("register..." + rvo);
		return mapper.insert(rvo);
	}
	
	// 댓글 목록
	@Override
	public List<ReplyVO> getList(int bno) {
		log.info("getList..." + bno);
		return mapper.getList(bno);
	}	
	
	// 댓글 읽기
	@Override
	public ReplyVO get(int rno) {
		log.info("get..." + rno);
		return mapper.read(rno);
	}
	
	// 댓글 삭제
	@Override
	public boolean remove(int rno) {
		log.info("remove..." + rno);
		return mapper.delete(rno) == 1;
	}
	
	// 댓글 수정
	@Override
	public boolean modify(ReplyVO rvo) {
		log.info("modify..." + rvo);
		return mapper.update(rvo) == 1;
	}
}
