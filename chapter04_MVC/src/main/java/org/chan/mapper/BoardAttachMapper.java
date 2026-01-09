package org.chan.mapper;

import java.util.List;

import org.chan.domain.BoardAttachVO;

public interface BoardAttachMapper {
	// 파일 등록
	public void insert(BoardAttachVO vo);
	
	// 파일 삭제
	public void delete(String uuid);
	
	// 파일 불러오기
	public List<BoardAttachVO> findByBno(int bno);
	
}
