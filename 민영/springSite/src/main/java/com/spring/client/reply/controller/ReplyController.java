package com.spring.client.reply.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.client.reply.service.ReplyService;
import com.spring.client.reply.vo.ReplyVO;

/**************************************************************
 * * 참고 : @RestController (@Controller + @ResponesBody) * 기존의 특정한 JSP와 같은 뷰를 만들어
 * 내는 것이 목적이 아닌 REST 방식의 데이터 처리를 * 위해서 사용하는(데이터 자체를 반환) 어노테이션이다.
 **************************************************************/
@RestController
@RequestMapping(value = "/replies")
public class ReplyController {
	Logger logger = Logger.getLogger(ReplyController.class);

	@Autowired
	private ReplyService replyService;

	/**************************************************************
	 * * 댓글 글목록 구현하기 * @return List<ReplyVO> * 참고 : @PathVariable는 URI의 경로에서 원하는
	 * 데이터를 추출하는 용도의 어노테이션. * 현재 요청 URL :
	 * http://localhost:8080/replies/all/게시판글번호.do * 예전 요청 URL :
	 * http://localhost:8080/replies/replyList.do?b_num=게시판글번호
	 **************************************************************/
	@RequestMapping(value = "/all/{b_num}.do", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("b_num") Integer b_num) {
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(replyService.replyList(b_num), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/**************************************************************
	 *  댓글 글쓰기 구현하기 *
	 *  @return String *
	 *  참고 : @RequestBody
	 **************************************************************/
	@RequestMapping(value="/replyInsert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo){
		logger.info("replyInsert 호출 성공 ");
		ResponseEntity<String> entity = null;
		int result;
		try {
			result = replyService.replyInsert(rvo);
			if(result==1) {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
}
