package io.yusun.course.controller;

import io.yusun.course.data.CourseStatus;
import io.yusun.course.data.StatManager;
import io.yusun.course.repositories.StatusRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	    
	    logger.info("Reset done: " + StatManager.get().getTotalSubmission());
	    
        response.setStatus(200);
        response.getWriter().write("success");
    }
	
	@RequestMapping(value = "stat", method = RequestMethod.GET)
	public Map<String, Integer> getStat() {
		Map<String, Integer> map = StatManager.get().getStatMap();
		logger.info("Get result: " + map);
		return map;
	}
	
	@RequestMapping(value = "stat/view", method = RequestMethod.GET)
	public Map<String, Integer> getStatView() {
		Map<String, Integer> map = StatManager.get().getStatMap();
		logger.info("Get result: " + map);
		return map;
	}
	
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public int getCount() {
		return StatManager.get().getTotalSubmission();
	}	
	
	@RequestMapping(value = "submit", method = RequestMethod.GET)
	public void submitAnswer(
			@RequestParam(value = "answer", required = true) String answers,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    logger.info("Request from session ID: " + request.getSession().getId() 
	    		+ " answer: " + answers + " total: " + StatManager.get().getTotalSubmission());
	    if (answers == null || answers.isEmpty()) {
	    	return;
	    }
	    
	    String userId = request.getSession().getId();
	    Set<String> submittedAnswerSet = new HashSet<String>();
	    for (String s : Arrays.asList(answers.split(","))) {
	    		submittedAnswerSet.add(s);
	    }
	    StatManager.get().addSubmission(userId, submittedAnswerSet);
	    
	    response.setContentType("text/html; charset=utf-8");
	    response.setStatus(200);        
	}
}
