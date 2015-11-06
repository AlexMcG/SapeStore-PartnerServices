package com.sapestore.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.SearchBook;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for searching book from database.
 *
 * CHANGE LOG VERSION    DATE            AUTHOR                  MESSAGE 
 *   1.0               21-10-2015    VINAY & NIMISHA             Initial
 * version
 */

@Repository
public class SearchDao {

@Autowired
private HibernateTemplate hibernateTemplate;

	Logger log = Logger.getLogger(SearchDao.class.getName());

	/**
	 * Method to fetch the book by Book Name.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<SearchBook> searchByBookName(String bookTitle) {

		List<SearchBook> listBook = (List<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookTitle",
						"bookTitle", '%' + bookTitle.toUpperCase() + '%');
		return listBook;
	}

	/**
	 * Method to fetch the book by Book Name for PredictiveSearch.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<String> predictSearchByTitle(String bookTitle) {

		List<String> listBook = (List<String>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.PredictSearchByTitle",
						"bookTitle", '%' + bookTitle.toUpperCase() + '%');
		return listBook;
	}

	/**
	 * Method to fetch the book by Book Author for PredictiveSearch.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<String> predictSearchByAuthor(String bookAuthor) {

		List<String> listBook = (List<String>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.PredictSearchByAuthor",
						"bookAuthor", '%' + bookAuthor.toUpperCase() + '%');
		return listBook;
	}

	/**
	 * Method to fetch the book by Book Category for PredictiveSearch.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<String> predictSearchByCategory(String categoryName) {

		List<String> listBook = (List<String>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.PredictSearchByCategory",
						"categoryName", '%' + categoryName.toUpperCase() + '%');
		return listBook;
	}

	/**
	 * Method to fetch the book by Author.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<SearchBook> searchByAuthor(String bookAuthor) {

		List<SearchBook> listBookAuthor = (List<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookAuthor",
						"bookAuthor", '%' + bookAuthor.toUpperCase() + '%');

		return listBookAuthor;
	}

	/**
	 * Method to fetch the book by Categeory Name.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<SearchBook> searchByCategeory(String categoryName) {

		List<SearchBook> listBookCategory = (List<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookCategory",
						"categoryName", '%' + categoryName.toUpperCase() + '%');
		return listBookCategory;
	}

	/**
	 * Method to fetch the book by ISBN.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<SearchBook> searchByISBN(String isbn) {

		List<SearchBook> listBookISBN = (List<SearchBook>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.findByBookISBN", "isbn",
						'%' + isbn.toUpperCase() + '%');
		return listBookISBN;
	}

	
	/*@SuppressWarnings("unchecked")
	public List<Book> sortResultByBookTitle(String bookTitle) {

		List<Book> listBook = (List<Book>) hibernateTemplate
				.findByNamedQueryAndNamedParam("Book.SortByBookTitle",
						"bookTitle", '%' + bookTitle + '%');
		return listBook;
	}*/
	
	
	/**
	 * Method to fetch the book on the basis of no of comments in desc order.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	
	
	@SuppressWarnings("unchecked")
	public List<SearchBook> searchByBookComments(String isbn){
		
		List<SearchBook> listBookComments = (List<SearchBook>) hibernateTemplate
		.findByNamedQueryAndNamedParam(
				"BookRatingComments.findByBookISBN", "isbn", isbn);
		
		return listBookComments;
		
	}

}
