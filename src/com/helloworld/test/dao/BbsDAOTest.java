package com.helloworld.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.helloworld.dao.BbsDAO;
import com.helloworld.model.Bbs;
import com.helloworld.util.CurrentLocalDateTime;

public class BbsDAOTest {

	BbsDAO bbsDAO;
	
	@BeforeEach
	void init() {
		bbsDAO = new BbsDAO();
	}
	
	@Test
	@DisplayName("게시판 아이디로 게시판 조회")
	void findByIdTest() {
		
		Bbs bbs = bbsDAO.findById(1L);
		System.out.println(bbs);
	}
	
	@Test
	@DisplayName("모든 게시물 조회")
	void findAllTest() {
		List<Bbs> bbsList = bbsDAO.findAll();
		
		for (Bbs bbs : bbsList) {
			System.out.println(bbs);
		}
	}
	
	@Test
	@DisplayName("게시물 페이지네이션")
	void bbsPagination() {
		List<Bbs> bbsList = bbsDAO.getBbsPage(2);
		
		System.out.println("Bbs List Size of " + bbsList.size());
		for (Bbs bbs : bbsList) {
			System.out.println("page = " + bbs);
		}
	}
	
	@Test
	@DisplayName("사용자 이름으로 게시물 페이지네이션")
	void bbsPaginationWithUserName() {
		List<Bbs> bbsList = bbsDAO.getBbsPage("김민석", 1);
		
		System.out.println("Bbs List Size of " + bbsList.size());
		for (Bbs bbs : bbsList) {
			System.out.println("userName = " + bbs);
		}
	}
	
	@Test
	@DisplayName("게시물 갯수 테스트")
	void bbsGetCount() {
		System.out.println(bbsDAO.bbsCount());
	}
	
	@Test
	@DisplayName("게시물 사용자 이름으로 검색")
	void bbsGetCountByUserName() {
		System.out.println(bbsDAO.bbsCount("김민석"));
	}
	
	@Test
	@DisplayName("사용자 아이디로 게시물 검색 테스트")
	void testGetBbsListByUserId() {
		List<Bbs> bbsList = bbsDAO.getBbsListByUserId("silverlight_me");
		
		for (Bbs bbs : bbsList) {
			System.out.println("silverlight_me = " + bbs);
		}
	}
	
	@Test
	@DisplayName("Jdbc auto-commit mode 확인")
	void testAutoCommitMode() {
		assertTrue(bbsDAO.getAutoCommitMode());
	}
	
	@Test
	@DisplayName("Jdbc auto-commit mode off 확인")
	void testOffAutoCommitMode() {
		assertTrue(bbsDAO.getAutoCommitMode());
	}
	
	@Test
	@DisplayName("Bbs Create Test")
	void testCreateBbs() {
		
		Bbs bbs = new Bbs();
		
		bbs.setTitle("테스트 제목 commit 하지 않을 떄");
		bbs.setUserId("sliverlight_me");
		bbs.setUserName("김은희");
		bbs.setBbsDate(CurrentLocalDateTime.now());
		bbs.setContent("테스트 내용입니다. commit 하지 않을 떄.");
		bbs.setLatitude(0);
		bbs.setLongitude(0);
		
		int result = bbsDAO.write(bbs);
		System.out.println("create result = " + result);
		assertEquals(result, 1);
	}
}
