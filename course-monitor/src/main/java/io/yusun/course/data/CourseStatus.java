package io.yusun.course.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "course-new")
public class CourseStatus {
	
	private String id;
	private Long timestamp = System.currentTimeMillis();
	private String info;
		
	
	public CourseStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseStatus(String id, String info) {
		super();
		this.id = id;
		this.info = info;
	}

	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	@DynamoDBHashKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
