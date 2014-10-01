package io.yusun.course.controller;

import io.yusun.course.data.CourseStatus;
import io.yusun.course.data.StatManager;
import io.yusun.course.repositories.StatusRepository;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	private Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private StatusRepository repository;	

	@RequestMapping(value = "/status")
	public String addFeedback(@RequestParam("data") String status,
			HttpServletRequest request) {
		CourseStatus courseStatus = new CourseStatus(status, request.getRemoteAddr());
		repository.save(courseStatus);		
		return "OK";
	}
	
	@RequestMapping(value = "/status2")
	public String addFeedback(@RequestParam("data") String status) {
		CourseStatus courseStatus = new CourseStatus(status, "");		
		repository.save(courseStatus);		
		return "OK";
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String healthCheck() {			
		return "OK";
	}
	
	
	@RequestMapping(value = "reset", method = RequestMethod.GET)
    public void resetGroup(
            HttpServletResponse response) throws Exception {
	    StatManager.get().resetMap();
	    
        response.setStatus(200);
        response.getWriter().write("success");
    }
	
	@RequestMapping(value = "stat", method = RequestMethod.GET)
	public Map<String, Integer> getStat() {
		return StatManager.get().getStatMap();
	}
	
	@RequestMapping(value = "stat/view", method = RequestMethod.GET)
	public Map<String, Integer> getStatView() {
		return StatManager.get().getStatMap();
	}
	
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public int getCount() {
		return StatManager.get().getTotalSubmission();
	}	
	
	@RequestMapping(value = "submit", method = RequestMethod.GET)
	public void submitAnswer(
			@RequestParam(value = "answer", required = true) String answers,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    logger.info("Request from session ID: " + request.getSession().getId() + " answer: " + answers);
	    if (answers == null || answers.isEmpty()) {
	    	return;
	    }
	    
	    String userId = request.getSession().getId();
	    StatManager.get().addSubmission(userId, Arrays.asList(answers.split(",")));
	    
	    response.setContentType("text/html; charset=utf-8");
	    response.setStatus(200);        
	}
}
