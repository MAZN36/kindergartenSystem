package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.CourseDao;
import com.bootdo.kinder.entity.CourseVO;
import com.bootdo.kinder.service.CourseService;



@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public CourseVO get(String courseId){
		return courseDao.get(courseId);
	}
	
	@Override
	public List<CourseVO> list(Map<String, Object> map){
		return courseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseDao.count(map);
	}
	
	@Override
	public int save(CourseVO course){
		return courseDao.save(course);
	}
	
	@Override
	public int update(CourseVO course){
		return courseDao.update(course);
	}
	
	@Override
	public int remove(String courseId){
		return courseDao.remove(courseId);
	}
	
	@Override
	public int batchRemove(String[] courseIds){
		return courseDao.batchRemove(courseIds);
	}
	
}
