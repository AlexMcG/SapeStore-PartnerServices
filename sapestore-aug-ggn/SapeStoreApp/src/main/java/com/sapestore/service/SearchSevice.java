package com.sapestore.service;

import java.util.List;



import org.springframework.stereotype.Service;

import com.sapestore.hibernate.entity.SearchBook;


/**
 * Service interface for Book Search functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	          MESSAGE 
 * 1.0 		21-10-2015 	VINAY & NIMISHA  Initial version
 */


@Service
public interface SearchSevice {

	/**
	 * Returns list of books matching the keyWord
	 * @param keyWord
	 * @return
	 */
	List<SearchBook> searchBook(String keyWord);
	
	
	/**
	 * Returns list of books for PredictiveSearch
	 * @param searchText
	 * @return
	 */
    List<String> searchText(String searchText);
    
    
    /**
	 * Returns list of books in order of descending usercomments
	 * @param books
	 * @return
	 */
    List<SearchBook> SearchByComments(List<SearchBook> books);
}
