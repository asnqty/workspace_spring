package org.chan.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
// context를 로딩하여 그 안의 bean을 사용하기 위한 어노테이션이다. tomcat이 컨테이너 역할을 하기에 junit을 이용한
// 테스트 과정에서 사용하는 어노테이션이
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class MemberTests {
	@Autowired
	private DataSource ds;

	@Autowired
	private PasswordEncoder pwencoder;

//	@Test
//	public void testInsertMember() {
//		String sql = "insert into tbl_member(userid, userpw, username)" + "values(?, ?, ?)";
//		
//		for(int i=0; i<100; i++) {
//			Connection con = null;
//			PreparedStatement ps = null;
//			
//			try {
//				con = ds.getConnection();
//				ps = con.prepareStatement(sql);
//				
//				// 비밀번호 인코딩
//				ps.setString(2, pwencoder.encode("pw" + (i+1)));
//				
//				// id&이름
//				if(i<80) {
//					ps.setString(1, "user" + (i+1));
//					ps.setString(3, "유저" + (i+1));
//				}
//				else if(i<90) {
//					ps.setString(1, "manager" + (i-79));
//					ps.setString(3, "운영자" + (i-79));
//				}
//				else {
//					ps.setString(1, "admin" + (i-89));
//					ps.setString(3, "관리자" + (i-89));
//				}
//				
//				ps.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if(ps != null) ps.close();
//					if(con != null) con.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//		}
//	}

//	@Test
//	public void testInsertMemberAuth() {
//		// 권한 테이블 (tbl_member_auth)에 권한 추가
//		// user* 계정들 > "ROLE_USER"
//		// manager* 계정들 > "ROLE_MANAGER"
//		// admin* 계정들 > "ROLE_ADMIN"
//		String sql = "insert into tbl_member_auth(userid, auth)" + "values(?, ?)";
//		for (int i = 0; i < 100; i++) {
//			Connection con = null;
//			PreparedStatement ps = null;
//
//			try {
//				con = ds.getConnection();
//				ps = con.prepareStatement(sql);
//
//				if (i < 80) {
//					ps.setString(1, "user" + (i + 1));
//					ps.setString(2, "ROLE_USER");
//				} else if (i < 90) {
//					ps.setString(1, "manager" + (i - 79));
//					ps.setString(2, "ROLE_MANAGER");
//				} else {
//					ps.setString(1, "admin" + (i - 89));
//					ps.setString(2, "ROLE_ADMIN");
//				}
//
//				ps.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (ps != null)
//						ps.close();
//					if (con != null)
//						con.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//		}
//	}
}
