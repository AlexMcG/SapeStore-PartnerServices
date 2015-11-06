package com.sapestore.partner.services.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;

public class SSPartnerWebServiceTest {
	private static SSPartnerWebService test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test = new SSPartnerWebService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		test = null;
	}

	@Test
	public void testGetSearchBookList() {
		List<SSPartnerBooksListBean> actualResult = test.getSearchBookList("", false);
		assertTrue(actualResult.size() == 5);
	}
	
	@Test
	public void testGetSearchBookListOrdered() {
		List<SSPartnerBooksListBean> actualResult = test.getSearchBookList("", true);
		assertTrue(actualResult.size() == 5);
	}

	@Test
	public void testGetBookListByKeywordUsingTitle() {
		List<SSPartnerBooksListBean> actualResult = test.getBookListByKeyword(test.titleSQL, "");
		assertTrue(actualResult.size() == 5);
	}
	
	@Test
	public void testGetBookListByKeywordUsingTitleOrdered() {
		List<SSPartnerBooksListBean> actualResult = test.getBookListByKeyword(test.titleSQLOrderByTotalCommentsDesc, "");
		assertTrue(actualResult.size() == 5);
	}
	
	@Test
	public void testGetBookListByKeywordUsingCategory() {
		List<SSPartnerBooksListBean> actualResult = test.getBookListByKeyword(test.categorySQL, "");
		assertTrue(actualResult.size() == 5);
	}
	
	@Test
	public void testGetBookListByKeywordUsingCategoryOrdered() {
		List<SSPartnerBooksListBean> actualResult = test.getBookListByKeyword(test.categorySQLOrderByTotalCommentsDesc, "");
		assertTrue(actualResult.size() == 5);
	}
	
	@Test
	public void testGetBookListByKeywordUsingAuthor() {
		List<SSPartnerBooksListBean> actualResult = test.getBookListByKeyword(test.authorSQL, "");
		assertTrue(actualResult.size() == 5);
	}
	
	@Test
	public void testGetBookListByKeywordUsingauthorOrdered() {
		List<SSPartnerBooksListBean> actualResult = test.getBookListByKeyword(test.authorSQLOrderByTotalCommentsDesc, "");
		assertTrue(actualResult.size() == 5);
	}

}
