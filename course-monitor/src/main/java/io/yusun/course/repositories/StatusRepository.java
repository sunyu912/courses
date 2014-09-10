package io.yusun.course.repositories;

import io.yusun.course.data.CourseStatus;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface StatusRepository extends CrudRepository<CourseStatus, String> {
	
}