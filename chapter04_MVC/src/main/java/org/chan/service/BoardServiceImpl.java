package org.chan.service;

import java.util.List;

import org.chan.domain.BoardVO;
import org.chan.domain.Criteria;
import org.chan.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList...");
		return mapper.getList(cri);
	}
	
	@Override
	public int getTotal() {
		log.info("getTotal...");
		return mapper.getTotal();
	}
	
	@Override
	public void register(BoardVO bvo) {
		log.info("register..." + bvo);
		mapper.insert(bvo);
	}
	
	@Override
	public BoardVO get(int bno) {
		log.info("get..." + bno);
		return mapper.read(bno);
	}
	
	@Override
	public boolean remove(int bno) {
		log.info("remove..." + bno);
		return mapper.delete(bno) == 1;
	}
	
	@Override
	public boolean modify(BoardVO bvo) {
		log.info("modify..." + bvo);
		return mapper.update(bvo) == 1;
	}	
}
