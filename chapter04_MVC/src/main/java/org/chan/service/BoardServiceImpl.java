package org.chan.service;

import java.io.File;
import java.util.List;

import org.chan.domain.BoardAttachVO;
import org.chan.domain.BoardVO;
import org.chan.domain.Criteria;
import org.chan.mapper.BoardAttachMapper;
import org.chan.mapper.BoardMapper;
import org.chan.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private ReplyMapper rmapper;
	
	@Autowired
	private BoardAttachMapper attachmapper;
	
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
	@Transactional
	public void register(BoardVO bvo) {
		log.info("register..." + bvo);
		// 게시글 등록
		mapper.insert(bvo);
		
		List<BoardAttachVO> list = bvo.getAttachList();
		int bno = bvo.getBno();
		
		// 첨부 파일 등록
		if(list != null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				BoardAttachVO attachvo = list.get(i);
				attachvo.setBno(bno);
				attachmapper.insert(attachvo);
			}
		}
	}
	
	@Override
	public BoardVO get(int bno) {
		log.info("get..." + bno);
		return mapper.read(bno);
	}
	
	@Transactional
	@Override
	public boolean remove(int bno) {
		log.info("remove..." + bno);
		int removeReplyCnt = rmapper.deleteAll(bno);
		log.info("removeReplyCnt : " + removeReplyCnt);
		List<BoardAttachVO> uuidList = attachmapper.findByBno(bno);
		for(int i=0; i<uuidList.size(); i++) {
			String uuid = uuidList.get(i).getUuid();
			attachmapper.delete(uuid);
		}
		int result = mapper.delete(bno);
		
		for(int i=0; i<uuidList.size(); i++) {
			try {
				File file = new File("C:\\upload\\" + uuidList.get(i).getUploadPath() + "\\" +
								uuidList.get(i).getUuid() + "_" + uuidList.get(i).getFileName());
				
				log.info("삭제 시도 경로: " + file.getAbsolutePath());
				log.info("exists(): " + file.exists());
				
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result == 1;
	}
	
	@Override
	public boolean modify(BoardVO bvo) {
		log.info("modify..." + bvo);
		
		// 새로 첨부한 파일만 DB에 추가
		List<BoardAttachVO> list = bvo.getAttachList();
		int bno = bvo.getBno();
		
		// 첨부 파일 등록
		if(list != null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				BoardAttachVO attachvo = list.get(i);
				attachvo.setBno(bno);
				attachmapper.delete(attachvo.getUuid());
				attachmapper.insert(attachvo);
			}
		}
		
		return mapper.update(bvo) == 1;
	}	
	
	@Override
	public List<BoardAttachVO> getAttachList(int bno) {
		log.info("getAttachList..." + bno);
		return attachmapper.findByBno(bno);
	}
	
	// 첨부 파일 삭제
	@Override
	public void removeFile(String uuid) {
		attachmapper.delete(uuid);
	}
}
