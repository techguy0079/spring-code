package com.spring.boot.training.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.training.config.ConfigurationsFile;
import com.spring.boot.training.model.Course;


@RestController 
public class RestEndpoints {

	@Autowired
	private ConfigurationsFile courseConfig;
	
	@RequestMapping(value="/course")
	public Course getEndpoint(@RequestParam(value="name" ,defaultValue="springboot",required=false) String name,
			@RequestParam(value="chapterCount" ,defaultValue="2",required=false) int chapterCount) {
		
		return new Course(name,chapterCount);
	}
	
	@RequestMapping(value="/course-prop")
	public HashMap<String,Object> getCourseFromConfig(@RequestParam(value="name" ,defaultValue="springboot",required=false) String name,
			@RequestParam(value="chapterCount" ,defaultValue="2",required=false) int chapterCount) {
			
		HashMap<String,Object> map = new HashMap<>();
		map.put("name", courseConfig.getName());
		map.put("chapterCount", courseConfig.getChapterCount());
		map.put("rating", courseConfig.getRating());
		map.put("author", courseConfig.getAuthor());
		
		return map;
		
		
	}
	

	@RequestMapping(method= RequestMethod.POST,value="/course")
	public String saveCourse(@RequestBody Course course  ) {
		return "Course named "+ course.getName() + " is created successfully with Chapter count" + course.getChapterCount();
		
	}
}
