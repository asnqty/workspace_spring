package org.chan.service;

import org.chan.mapper.Sample1Mapper;
import org.chan.mapper.Sample2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService{
	@Autowired
	private Sample1Mapper mapper1;
	@Autowired
	private Sample2Mapper mapper2;
	
	@Transactional
	// 메소드가 실행하는 모든 작업을 하나의 트랜잭션으로 묶음
	// 따라서 해당 어노테이션을 붙이지 않으면 1번 쿼리를 실행하고 커밋, 2번 쿼리를 실행하고 커밋을 하는데
	// 해당 어노테이션을 붙이면 메소드에서 실행하는 모든 쿼리를 실행하고 마지막에 커밋을 하거나 하나라도 실패하면 롤백을 함
	@Override
	public void addData(String value) {
		log.info("mapper1....");
		mapper1.insertCol1(value);
		
		log.info("mapper2....");
		mapper2.insertCol2(value);
		
		log.info("end!!!");
	}
}
