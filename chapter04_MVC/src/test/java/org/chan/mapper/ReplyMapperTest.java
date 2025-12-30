package org.chan.mapper;

import java.sql.Date;
import java.util.List;

import org.chan.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
//	public void testInsert() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBno(97);
//		rvo.setReply("테스트댓글내용");
//		rvo.setReplyer("테스트댓글작성자");
//		int result = mapper.insert(rvo);
//		log.info(result);
//	}
	
//	@Test
//	public void testGetList() {
//		List<ReplyVO> list = mapper.getList(97);
//		for(ReplyVO rvo : list) {
//			log.info(rvo);
//		}
//	}
	
//	@Test
//	public void testRead() {
//		ReplyVO rvo = mapper.read(1);
//		log.info(rvo);
//	}
	
//	@Test
//	public void testDelete() {
//		int result = mapper.delete(2);
//		log.info(result);
//	}
	
//	@Test
//	public void testUpdate() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setReply("수정테스트댓글내용");
//		rvo.setUpdatedate(time);
//		int result = mapper.update(rvo);
//		log.info(result);
//	}
}
