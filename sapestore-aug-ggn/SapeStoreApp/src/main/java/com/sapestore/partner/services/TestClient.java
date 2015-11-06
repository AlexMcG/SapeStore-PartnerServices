package com.sapestore.partner.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.controller.AccountController;
import com.sapestore.hibernate.entity.Book;

/**
 * Test function that tests the functionality of the web services prior to integration
 */
public class TestClient {

	/**
	 * Test function that tests the functionality of the web services prior to integration
	 * 
	 * @param String[] args
	 */
	public static void main(String[] args) {
		
		SSPartnerWebService service  = new SSPartnerWebService();
		SSPartnerWebServicePortType client = service.getSSPartnerWebServiceHttpSoap11Endpoint();
		List<SSPartnerBooksListBean> testList = client.getSearchBookList("", false);
		System.out.println("PARTNER STORE BOOKS:");
		System.out.println("");
		for (SSPartnerBooksListBean listBean : testList) {
			System.out.println("TITLE: " + listBean.getBookTitle());
			System.out.println("CATEGORY_ID: " + listBean.getCategoryIdpr());
			System.out.println("AUTHOR: " + listBean.getBookAuthor());
			System.out.println("");
		}
		System.out.println("SIZE: " + testList.size());
		System.out.println("END");
	}
	
	/**
	 * Function converts the results from a list of SSPartnerBooksListBean to
	 * a list of Book so it can be merges with the incorporated with the rest
	 * of the books
	 * 
	 * @param List<SSPartnerBooksListBean> testList
	 * @return List<Book> bookList
	 */
	public List<Book> formatChange(List<SSPartnerBooksListBean> testList) {
		List<Book> bookList = new ArrayList<Book>();
		for (SSPartnerBooksListBean partnerBook : testList) {
			Book book = new Book();
			book.setIsbn(partnerBook.getIsbn());
			book.setPublisherName(partnerBook.getPublisherName());
			book.setCategoryId(partnerBook.getCategoryIdpr());
			book.setBookTitle(partnerBook.getBookTitle());
			book.setQuantity(partnerBook.getQuantity());
			book.setBookAuthor(partnerBook.getBookAuthor());
			book.setBookThumbImage(partnerBook.getThumbImageUrl());
			book.setBookFullImage(partnerBook.getFullImageUrl());
			book.setBookShortDescription(partnerBook.getBookShortDesc());
			book.setBookDetailDescription(partnerBook.getBookDetailDesc());
			book.setCreatedDate(null);
			book.setUpdatedDate(null);
			book.setIsActive(partnerBook.getActive());
			book.setRentAvailability("N");
			book.setRentPrice(null);
			book.setLateFee(null);
			book.setIsFromPartnerStore("Y");
			book.setAverageRating(null);
			book.setBookPrice(new BigDecimal(partnerBook.getBookPrice()));
			bookList.add(book);
		}
		return bookList;
	}

}
