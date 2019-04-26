package com.bootdo.kinder.service;

import com.bootdo.kinder.entity.CourseVO;

import java.util.List;
import java.util.Map;

/**
 * 课程
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface CourseService {
	
	CourseVO get(String courseId);
	
	List<CourseVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseVO course);
	
	int update(CourseVO course);
	
	int remove(String courseId);
	
	int batchRemove(String[] courseIds);
}
