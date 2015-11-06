package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.SearchDao;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.SearchBook;
import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;
import com.sapestore.partner.services.SSPartnerWebServicePortType;
import com.sapestore.service.SearchSevice;

/**
 * Service class for Book Search and Result display.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 21-10-2015 VINAY & NIMISHA Initial
 * version
 */
@Service("searchService")
public class SearchServiceImpl implements SearchSevice {

	@Autowired
	SearchDao search;
	

	@Override
	public List<SearchBook> searchBook(String searchText) {

		List<SearchBook> listSearchByName = search.searchByBookName(searchText);
		List<SearchBook> listSearchByCategory = search
				.searchByCategeory(searchText);
		List<SearchBook> listSearchByAuthor = search.searchByAuthor(searchText);
		List<SearchBook> listSearchByISBN = search.searchByISBN(searchText);

		List<SearchBook> listBookFinal = new ArrayList<SearchBook>();

		listBookFinal.addAll(listSearchByName);
		listBookFinal.addAll(listSearchByCategory);
		listBookFinal.addAll(listSearchByAuthor);
		listBookFinal.addAll(listSearchByISBN);

		return listBookFinal;
	}
	
	public List<SearchBook> searchPartnerBook(String searchText) {
		List<SearchBook> listBookFinal = new ArrayList<SearchBook>();
		
		SSPartnerWebService service  = new SSPartnerWebService();
		SSPartnerWebServicePortType client = service.getSSPartnerWebServiceHttpSoap11Endpoint();
		List<SSPartnerBooksListBean> testList = client.getSearchBookList(searchText, false);
		
		for (SSPartnerBooksListBean partnerBook : testList) {
			SearchBook book = new SearchBook();
			book.setIsbn(partnerBook.getIsbn());
			book.setCategoryId(partnerBook.getCategoryIdpr());
			book.setBookTitle(partnerBook.getBookTitle());
			book.setBookAuthor(partnerBook.getBookAuthor());
			book.setBookThumbImage(partnerBook.getThumbImageUrl());
			book.setBookPrice((double) partnerBook.getBookPrice());
			listBookFinal.add(book);
		}
		
		return listBookFinal;
	}

	@Override
	public List<String> searchText(String searchText) {

		List<String> predictFinal = new ArrayList<String>();

		List<String> listSearchByTitle = search
				.predictSearchByTitle(searchText);
		List<String> listSearchByAuthor = search
				.predictSearchByAuthor(searchText);
		List<String> listSearchByCategory = search
				.predictSearchByCategory(searchText);

		predictFinal.addAll(listSearchByTitle);
		predictFinal.addAll(listSearchByAuthor);
		predictFinal.addAll(listSearchByCategory);

		return predictFinal;
	}

	
	
	public List<SearchBook> SearchByComments(List<SearchBook> books) {
		String isbn;

		TreeMap<String, Integer> isbn_comments = new TreeMap<String, Integer>();

		List<SearchBook> temporaryBooks = null;

		int noOfComments;

		for (SearchBook book : books) {
			isbn = book.getIsbn();
			temporaryBooks = search.searchByBookComments(isbn);
			int length = temporaryBooks.size();
			isbn_comments.put(isbn, length);

		}

		List<Entry<String, Integer>> sortedEntries = new ArrayList<Entry<String, Integer>>(
				isbn_comments.entrySet());

		Collections.sort(sortedEntries,
				new Comparator<Entry<String, Integer>>() {

					@Override
					public int compare(Entry<String, Integer> e1,
							Entry<String, Integer> e2) {
						return e2.getValue().compareTo(e1.getValue());
					}
				}
		);

		books.clear();
		int treeIndex = 0;
		for (Entry<String, Integer> arr : sortedEntries) {
			isbn = arr.getKey();
			temporaryBooks =search.searchByISBN(isbn);
			books.add(treeIndex, temporaryBooks.get(0));
			treeIndex++;
		}
		return books;

	}
}
