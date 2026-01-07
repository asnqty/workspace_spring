package org.chan.service;

import java.util.List;

import org.chan.domain.ReplyVO;
import org.chan.mapper.BoardMapper;
import org.chan.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper bmapper;
	
	// 댓글 삽입
	@Transactional
	@Override
	public int register(ReplyVO rvo) {
		log.info("register..." + rvo);
		bmapper.updateReplyCnt(rvo.getBno(), 1);
		return mapper.insert(rvo);
	}
	
	// 댓글 목록
	@Override
	public List<ReplyVO> getList(int bno) {
		log.info("getList..." + bno);
		return mapper.getList(bno);
	}	
	
	// 댓글 조회
	@Override
	public ReplyVO get(int rno) {
		log.info("get..." + rno);
		return mapper.read(rno);
	}
	
	// 댓글 삭제
	@Transactional
	@Override
	public boolean remove(int rno, int bno) {
		log.info("remove..." + rno);
		// bno를 받아올 필요 없이 mapper의 read를 사용하면 bno가 담긴 rvo 객체를 받아올 수 있다.
		// ReplyVO rvo = mapper.read(rno);
		// bmapper.updateReplyCnt(rvo.getBno(), -1);
		bmapper.updateReplyCnt(bno, -1);
		return mapper.delete(rno) == 1;
	}
	
	// 댓글 수정
	@Override
	public boolean modify(ReplyVO rvo) {
		log.info("modify..." + rvo);
		return mapper.update(rvo) == 1;
	}
}
