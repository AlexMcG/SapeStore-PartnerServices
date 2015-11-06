package com.sapestore.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "SAPESTORE_BOOKS")
@NamedQueries(value = {
		@NamedQuery(name = "Book.findByBookTitle", query = "from SearchBook b where upper(b.bookTitle) LIKE :bookTitle"),
		@NamedQuery(name = "Book.findByBookAuthor", query = "from SearchBook b where upper(b.bookAuthor) LIKE :bookAuthor)"),
		@NamedQuery(name = "Book.findByBookCategory", query = "from SearchBook b where b.categoryId IN(Select categoryId from BookCategory where upper(categoryName) LIKE :categoryName)"),
		@NamedQuery(name = "Book.findByBookISBN", query = "from SearchBook b where b.isbn LIKE :isbn)"),

		@NamedQuery(name = "Book.PredictSearchByTitle", query = "select s.bookTitle from SearchBook s where s.bookTitle LIKE :bookTitle"

		),
		@NamedQuery(name = "Book.PredictSearchByAuthor", query = "select s.bookAuthor from SearchBook s where s.bookAuthor LIKE :bookAuthor"

		) })

public class SearchBook {

	@Id
	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@OneToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", insertable = false, updatable = false)
	private BookCategory bookCategory;

	@Transient
	private String categoryName;

	@Column(name = "BOOK_TITLE")
	private String bookTitle;

	@Column(name = "BOOK_AUTHOR")
	private String bookAuthor;

	@Column(name = "BOOK_THUMB_IMAGE")
	private String bookThumbImage;
	
	
	


	@Column(name="BOOK_PRICE")
	private double bookPrice;
	
	
	/**
	 * @return the Book Price
	 */
	public double getBookPrice() {
		return bookPrice;
	}


	/**
	 * @param bookPrice the BookPrice to set
	 */
	
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	/**
	 * @return the Book ISBN
	 */
	public String getIsbn() {
		return isbn;
	}
	
	
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	/**
	 * @return the CategoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	/**
	 * @return the BookCategory
	 */
	public BookCategory getBookCategory() {
		return bookCategory;
	}

	/**
	 * @param bookCategory the bookCategory to set
	 */
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	
	
	/**
	 * @return the CategoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	/**
	 * @return the BookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	
	/**
	 * @return the BookAuthor
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}

	
	/**
	 * @param bookAuthor the bookAuthor to set
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	
	/**
	 * @return the BookThumbImage
	 */
	public String getBookThumbImage() {
		return bookThumbImage;
	}

	
	/**
	 * @param bookThumbImage the bookThumbImage to set
	 */
	public void setBookThumbImage(String bookThumbImage) {
		this.bookThumbImage = bookThumbImage;
	}
}
