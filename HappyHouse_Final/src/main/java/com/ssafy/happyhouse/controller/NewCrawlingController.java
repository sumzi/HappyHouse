package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.PageBean;
import com.ssafy.happyhouse.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Api("뉴스 Controller API V1") // Controller에 대한 설명 Swagger Annotation
@RequestMapping("/news")
public class NewCrawlingController {
	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);

	@Autowired
	private QnAService service;
	private static final String SUCCESS = "success";

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.debug("ErrorHandler.............................");
		logger.debug("errorMassage............................." + e.getMessage());
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}

	// TODO page번호 보이기
	@ApiOperation(value = "뉴스 목록", notes = "전체 네이버 뉴스 목록을 조회한 결과")
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getNewsList(PageBean bean) throws Exception {
		logger.debug("getNewsList............................");
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
//		Calendar currentTime = Calendar.getInstance();

//		String search = bean.getWord();
//		int pageNo = Integer.parseInt(bean.getPageNo());
//		int spp = bean.getSpp();
//		int start = 1 + pageNo * spp;

		StringBuilder crawlURL = new StringBuilder(
				"https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=260&sid1=101");

		Map<String, Object> resultMap = new HashMap<>();

		Document doc = Jsoup.connect(crawlURL.toString()).get();
		doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml);

		String newsSelector = "div.list_body>ul>li>dl";
		Elements news = doc.select(newsSelector);
		int elSize = news.size();
//		Elements content = headlines.select("dt:not(.photo)");

//		Elements imgEls = news.select("img");
//		Elements titleEls = news.select("dt:not(.photo)>a");
//		Elements contentEls = news.select("dd>span.lede");
//		Elements writeEls = news.select("dd>span.writing");
//		Elements dateEls = news.select("dd>span.date");
//
//		String[] imgTag = new String[elSize]; 
//		for (int i = 0; i < elSize; i++) {
//			imgTag[i] = imgEls.get(i).toString();
//		}
//		resultMap.put("imgEls", imgTag);

		Elements alist = news.select("a");

		String[] detailLink = new String[elSize];
		Elements content = news.select("dt:not(.photo)>a");
		for (int i = 0; i < elSize; i++) {
			detailLink[i] = content.get(i).attr("href");
		}
		alist.tagName("div");
		news.tagName("div");
		news.attr("class", "news_item");

		System.out.println(news.size() + "실행");
		String[] elStrings = new String[elSize];

		for (int i = 0; i < elSize; i++) {
			elStrings[i] = news.get(i).toString();
		}
		resultMap.put("news", elStrings);
		resultMap.put("detailLink", detailLink);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "뉴스 상세 정보", notes = "네이버 뉴스 페이지의 상세 정보를 크롤링해서 보여줌")
	@GetMapping("/detail")
	public ResponseEntity<Map<String, Object>> getNewsDetail(@RequestParam("url") String url) throws Exception {
		logger.debug("getNewsList............................");
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
//			Calendar currentTime = Calendar.getInstance();
		System.out.println(url);
		Map<String, Object> resultMap = new HashMap<>();

		Document doc = Jsoup.connect(url).get();
		doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml);

		String pressLogoSelector = "div.press_logo";
		Element pressLogo = doc.select(pressLogoSelector).get(0);
		Element title = doc.getElementById("articleTitle");
		Element content = doc.getElementById("articleBodyContents");
		Element byline = doc.getElementsByClass("byline").get(0);

		resultMap.put("pressLogo", pressLogo.toString());
		resultMap.put("title", title.toString());
		resultMap.put("content", content.toString());
		resultMap.put("byline", byline.toString());

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
