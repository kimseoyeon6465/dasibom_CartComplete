package com.booksajo.dasibom.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.booksajo.dasibom.service.BookService;
import com.booksajo.dasibom.service.dao.BookDAO;
import com.booksajo.dasibom.vo.BookSearchResult;
import com.booksajo.dasibom.vo.BookVO;
import com.booksajo.dasibom.vo.ReviewVO;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;

	SimpleDateFormat naverDateFormat = new SimpleDateFormat("yyyyMMdd");

	@Override
	@Transactional
	public ArrayList<BookVO> getAllBook() {
		// TODO Auto-generated method stub
		return bookDAO.getAllBook();
	}

	@Override
	public ArrayList<BookVO> getBest() {
		return bookDAO.getBest();
	}

	@Override
	public ArrayList<BookVO> getNew() {
		return bookDAO.getNew();
	}

	@Override
	@Transactional
	public BookVO getTelinfo(BookVO bookVO) {
		return bookDAO.getTelinfo(bookVO);
	}

	@Override
	@Transactional
	public ArrayList<ReviewVO> getAllReview() {
		// TODO Auto-generated method stub
		return bookDAO.getAllReview();
	}

	@Override
	public void insertReview(ReviewVO reviewVO) {
		bookDAO.insertReview(reviewVO);
	}

	@Override
	public void updateReview(ReviewVO reviewVO) {
		bookDAO.updateReview(reviewVO);
	}

	@Override
	public void deleteReview(ReviewVO reviewVO) {
		bookDAO.deleteReview(reviewVO);
	}

	@Override
	public ArrayList<ReviewVO> getAllReviewByIsbn(String isbn) {
		return bookDAO.getAllReviewByIsbn(isbn);
	}

	// 평점
	@Override
	public double getAverageRating(String isbn) {
		return bookDAO.getAverageRating(isbn);
	}

	// 좋아요버튼
	@Override
	public void incrementLikes(int cid) {
		bookDAO.incrementLikes(cid);
	}

	@Override
	public BookSearchResult searchBooks(String query, int startnum) {
		String clientId = "k1JoyA9mKyzQfVoyHmUW";
		String clientSecret = "liT0c_CuXU";
		ArrayList<BookVO> resultList = new ArrayList<BookVO>();
		int total = 0;

		try {
			String text = URLEncoder.encode(query, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl=" + text + "&start="
					+ Math.max(1, startnum);

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8") // Java 6은
																										// StandardCharsets
																										// 사용 불가
			);

			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			// XML 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(response.toString()));
			Document doc = builder.parse(is);

			total = Integer.parseInt(getTagValue(doc.getDocumentElement(), "total")); // 루트 element
			NodeList items = doc.getElementsByTagName("item");

			for (int i = 0; i < items.getLength(); i++) {
				Element item = (Element) items.item(i);
				BookVO book = new BookVO();

				book.setIsbn(getTagValue(item, "isbn"));
				book.setTitle(getTagValue(item, "title"));
				book.setAuthor(getTagValue(item, "author"));
				book.setPublisher(getTagValue(item, "publisher"));
				book.setPrice(Integer.parseInt(getTagValue(item, "discount"))); // 가격
				book.setSummary(getTagValue(item, "description"));
				book.setImgurl(getTagValue(item, "image"));
				book.setLink(getTagValue(item, "link"));
				resultList.add(book);

				String pubDateStr = getTagValue(item, "pubdate");

				Date pubDate = null;
				try {
					if (pubDateStr != null && !pubDateStr.trim().isEmpty()) {
						pubDate = naverDateFormat.parse(pubDateStr);
					}
				} catch (ParseException e) {
					e.printStackTrace(); // 또는 로거 처리
				}
				book.setPub_date(pubDate);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new BookSearchResult(resultList, total);
	}

	private String getTagValue(Element item, String tag) {
		NodeList nodes = item.getElementsByTagName(tag);
		if (nodes.getLength() > 0) {
			return nodes.item(0).getTextContent();
		}
		return "";
	}

	@Override
	public ArrayList<BookVO> searchBooksByGenre(String category, int start, int end) {
		return bookDAO.searchBooksByGenre(category, start, end);
	}

	@Override
	public BookVO getBookByIsbn(String isbn) {
		return bookDAO.getBookByIsbn(isbn);
	}

	@Override
	public int getBookCountByGenre(String category) {
		return bookDAO.getBookCountByGenre(category);
	}
}