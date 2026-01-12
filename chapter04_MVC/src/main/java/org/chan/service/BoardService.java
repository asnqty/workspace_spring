package org.chan.service;

import java.util.List;

import org.chan.domain.BoardAttachVO;
import org.chan.domain.BoardVO;
import org.chan.domain.Criteria;

public interface BoardService {
	
	// 전체 리스트
	public List<BoardVO> getList(Criteria cri);
	
	// 전체 게시글 수
	public int getTotal();
	
	// 데이터 삽입
	public void register(BoardVO bvo);
	
	// 단일 데이터
	public BoardVO get(int bno);
	
	// 데이터 삭제
	public boolean remove(int bno);
	
	// 데이터 수정
	public boolean modify(BoardVO bvo);
	
	// 첨부 파일 리스트
	public List<BoardAttachVO> getAttachList(int bno);
	
	// 첨부 파일 삭제
	public void removeFile(String uuid);
}
