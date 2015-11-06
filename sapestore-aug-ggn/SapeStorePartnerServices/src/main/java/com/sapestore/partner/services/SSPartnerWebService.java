package com.sapestore.partner.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SSPartnerWebService {
	
	// Logger to log information
	private final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(SSPartnerWebService.class.getName());
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@delvmpllcai01.sapient.com:1521:caidb1";

	// Database credentials
	static final String USER = "GGN_SAPESTORE_BATCH_A";
	static final String PASS = "GGN_SAPESTORE_BATCH_A";

	//Prepared SQL statements
	public String titleSQL = "SELECT * FROM SAPESTORE_BOOKS WHERE UPPER(BOOK_TITLE) LIKE ? AND IS_FROM_PARTNER_STORE='Y'";
	public String titleSQLOrderByTotalCommentsDesc = "" +
								"SELECT SAPESTORE_BOOKS.*, TOTAL_COMMENTS " +
								"FROM SAPESTORE_BOOKS " +
								"JOIN (" +
								"SELECT SAPESTORE_BOOKS.ISBN, COUNT(BOOK_RATING_COMMENTS.ISBN) AS TOTAL_COMMENTS " +
								"FROM SAPESTORE_BOOKS " +
								"LEFT JOIN BOOK_RATING_COMMENTS ON BOOK_RATING_COMMENTS.ISBN = SAPESTORE_BOOKS.ISBN " +
								"GROUP BY SAPESTORE_BOOKS.ISBN " +
								"ORDER BY COUNT(BOOK_RATING_COMMENTS.ISBN)" +
								") COMMENTS " +
								"ON SAPESTORE_BOOKS.ISBN = COMMENTS.ISBN " +
								"WHERE UPPER(BOOK_TITLE) LIKE ? " +
								"AND IS_FROM_PARTNER_STORE='Y' "+
								"ORDER BY TOTAL_COMMENTS";
	public String categorySQL = "SELECT * FROM SAPESTORE_BOOKS "
								+ "INNER JOIN SAPESTORE_BOOKS_CATEGORIES " 
								+ "ON SAPESTORE_BOOKS.CATEGORY_ID = SAPESTORE_BOOKS_CATEGORIES.CATEGORY_ID "
								+ "WHERE UPPER(CATEGORY_NAME) LIKE ? "
								+ "AND IS_FROM_PARTNER_STORE='Y'";
	public String categorySQLOrderByTotalCommentsDesc = "" +
								"SELECT SAPESTORE_BOOKS.*, TOTAL_COMMENTS " +
								"FROM SAPESTORE_BOOKS " +
								"JOIN (" +
								"SELECT SAPESTORE_BOOKS.ISBN, COUNT(BOOK_RATING_COMMENTS.ISBN) AS TOTAL_COMMENTS " +
								"FROM SAPESTORE_BOOKS " +
								"LEFT JOIN BOOK_RATING_COMMENTS ON BOOK_RATING_COMMENTS.ISBN = SAPESTORE_BOOKS.ISBN " +
								"GROUP BY SAPESTORE_BOOKS.ISBN " +
								"ORDER BY COUNT(BOOK_RATING_COMMENTS.ISBN) " +
								") COMMENTS " +
								"ON SAPESTORE_BOOKS.ISBN = COMMENTS.ISBN " +
								"INNER JOIN SAPESTORE_BOOKS_CATEGORIES " +
								"ON SAPESTORE_BOOKS.CATEGORY_ID = SAPESTORE_BOOKS_CATEGORIES.CATEGORY_ID " +
								"WHERE UPPER(CATEGORY_NAME) LIKE ? " +
								"AND IS_FROM_PARTNER_STORE='Y' " +
								"ORDER BY TOTAL_COMMENTS";
	public String authorSQL = "SELECT * FROM SAPESTORE_BOOKS WHERE UPPER(BOOK_AUTHOR) LIKE ? AND IS_FROM_PARTNER_STORE='Y'";
	public String authorSQLOrderByTotalCommentsDesc = "" +
								"SELECT SAPESTORE_BOOKS.*, TOTAL_COMMENTS " +
								"FROM SAPESTORE_BOOKS " +
								"JOIN (" +
								"SELECT SAPESTORE_BOOKS.ISBN, COUNT(BOOK_RATING_COMMENTS.ISBN) AS TOTAL_COMMENTS " +
								"FROM SAPESTORE_BOOKS " +
								"LEFT JOIN BOOK_RATING_COMMENTS ON BOOK_RATING_COMMENTS.ISBN = SAPESTORE_BOOKS.ISBN " +
								"GROUP BY SAPESTORE_BOOKS.ISBN " +
								"ORDER BY COUNT(BOOK_RATING_COMMENTS.ISBN)" +
								") COMMENTS " +
								"ON SAPESTORE_BOOKS.ISBN = COMMENTS.ISBN " +
								"WHERE UPPER(BOOK_AUTHOR) LIKE ? " +
								"AND IS_FROM_PARTNER_STORE='Y' "+
								"ORDER BY TOTAL_COMMENTS";
	
	public List<SSPartnerBooksListBean> getBookList(String catId) {
		List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement.....");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM SAPESTORE_BOOKS WHERE CATEGORY_ID=" + catId + " AND IS_FROM_PARTNER_STORE='Y'";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
				transDO.setIsbn(rs.getString("ISBN"));
				transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
				transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
				transDO.setBookTitle(rs.getString("BOOK_TITLE"));
				transDO.setQuantity(rs.getInt("QUANTITY"));
				transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
				transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
				transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
				transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
				transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
				transDO.setActive(rs.getString("IS_ACTIVE"));
				finalList.add(transDO);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			LOGGER.debug("SQLException in partner services is "+se);
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			LOGGER.debug("SQLException in partner services is "+e);
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return finalList;
	}// end main here
	
	/**
	 * Returns a SSPartnerBooksListBean representing the partner store's books
	 * 
	 * @param String keyword
	 * @return List<SSPartnerBooksListBean> finalList
	 * 
	 */
	public List<SSPartnerBooksListBean> getSearchBookList(String keyword, boolean isSortedByComments) {
		List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
		
		//Checks if queries should be sorted by amount of comments
		if (isSortedByComments) {
			//Attempts search by title query sorted by amount of comments
			finalList = getBookListByKeyword(titleSQLOrderByTotalCommentsDesc, keyword);
			
			//If query is empty attempts search by category sorted by amount of comments
			if (finalList.isEmpty()) { finalList = getBookListByKeyword(categorySQLOrderByTotalCommentsDesc, keyword); }
			
			//If query is empty attempts search by author sorted by amount of comments
			if (finalList.isEmpty()) { finalList = getBookListByKeyword(authorSQLOrderByTotalCommentsDesc, keyword); }
		} else {
			//Attempts search by title query
			finalList = getBookListByKeyword(titleSQL, keyword);
			
			//If query is empty attempts search by category
			if (finalList.isEmpty()) { finalList = getBookListByKeyword(categorySQL, keyword); }
			
			//If query is empty attempts search by author
			if (finalList.isEmpty()) { finalList = getBookListByKeyword(authorSQL, keyword); }
		}
		return finalList;
	}// end main here
	
    /**
     * Function executes prepared SQL statements to retrieve the partner store's books
     * 
     * @param sql
     * @param keyword
     * @return List<SSPartnerBooksListBean> finalList
     * 
     */
	public List<SSPartnerBooksListBean> getBookListByKeyword(String sql, String keyword) {
		List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement.....");
			stmt = conn.createStatement();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + keyword.toUpperCase() + "%");
			ResultSet rs = preparedStatement.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
				transDO.setIsbn(rs.getString("ISBN"));
				transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
				transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
				transDO.setBookTitle(rs.getString("BOOK_TITLE"));
				transDO.setQuantity(rs.getInt("QUANTITY"));
				transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
				transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
				transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
				transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
				transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
				transDO.setActive(rs.getString("IS_ACTIVE"));
				finalList.add(transDO);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			LOGGER.debug("SQLException in partner services is "+se);
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			LOGGER.debug("SQLException in partner services is "+e);
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return finalList;
	}// end main here
}
