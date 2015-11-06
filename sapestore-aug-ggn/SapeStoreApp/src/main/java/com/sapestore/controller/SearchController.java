
package com.sapestore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.SearchBook;
import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;
import com.sapestore.partner.services.SSPartnerWebServicePortType;
import com.sapestore.service.impl.SearchServiceImpl;

/**
 * @author mku106
 * this is the controller  class for search functionality
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    21-10-2015     SAPIENT      Initial version
 */

@Controller
@RequestMapping("/search")
public class SearchController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(SearchController.class.getName());

	String emptyListInfo;
	
	@Autowired
	SearchServiceImpl searchServiceImpRef;
	
	


	public SearchController() {
		
	}


	/**
	 * Processes the search requests
	 * @param searchKeyword
	 * @param modelMap
	 * @param partnerStoreCheck
	 * @return
	 *
	 */

	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	public String searchBook(
			@RequestParam("search") String searchKeyword,
			
			ModelMap model) {

		//if (!partnerStoreCheck) {
			
			List<SearchBook> resultBookList = searchServiceImpRef
					.searchBook(searchKeyword);
			List<SearchBook> partnerBookList = searchServiceImpRef
					.searchPartnerBook(searchKeyword);
			resultBookList.addAll(partnerBookList);
			
			if (resultBookList == null || resultBookList.isEmpty()) {
				emptyListInfo = "Result not found";
				model.addAttribute("emptyListInfo", emptyListInfo);
			} else {
				emptyListInfo="Result found";
				model.addAttribute("resultBookList", resultBookList);
			}

		//}

		/*else {

			SearchServiceImpl searchServiceImpRef = new SearchServiceImpl();
			List<SearchBook> resultBookList = searchServiceImpRef
					.searchBook(searchKeyword);

			SSPartnerWebService partnerWebServiceRef = new SSPartnerWebService();
			SSPartnerWebServicePortType partnerStoreClient = partnerWebServiceRef
					.getSSPartnerWebServiceHttpSoap11Endpoint();
			List<SSPartnerBooksListBean> partnerStoreBookListResult = partnerStoreClient
					.getBookList(searchKeyword);

			if ((resultBookList == null || resultBookList.isEmpty())
					&& (partnerStoreBookListResult == null || partnerStoreBookListResult
					.isEmpty())) {
				emptyListInfo = "Result not found in both the stores";
				model.addAttribute("emptyListInfo", emptyListInfo);
			} else {
					emptyListInfo="result found";
				model.addAttribute("resultBookList", resultBookList);
				model.addAttribute("partnerStoreBookListResult",
						partnerStoreBookListResult);
			}*/

	

		return "searchResult";

	}


	/**
	 * Processes the predictive search  search requests
	 * 
	 * @return
	 *
	 */

	@RequestMapping(value = "/predictiveSearch", method = RequestMethod.POST)
	public void predictiveSearch(
			HttpServletRequest request, HttpServletResponse response)
					throws IOException {

		response.setContentType("application/json");
		try {
			String searchKeyword = request.getParameter("search");
		

			SearchServiceImpl searchServiceImpRef = new SearchServiceImpl();
			List<String> resultBookList = searchServiceImpRef.searchText(searchKeyword);

			// ArrayList<String> list = dataDao.getFrameWork(term);
			
			Gson gsonObj = new Gson();
			String finalSearchList = gsonObj.toJson(resultBookList);
			response.getWriter().write(finalSearchList);
		} catch (IOException ioExceptionRef) {
			LOGGER.error("IOException in predictive search method while using response in searchContoller ");
		}
	}

}
