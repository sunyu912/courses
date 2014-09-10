package io.yusun.course.controller;

import io.yusun.course.data.CourseStatus;
import io.yusun.course.repositories.StatusRepository;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

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
}
