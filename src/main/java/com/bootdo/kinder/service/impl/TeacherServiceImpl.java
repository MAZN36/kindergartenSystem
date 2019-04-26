package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.TeacherDao;
import com.bootdo.kinder.entity.TeacherVO;
import com.bootdo.kinder.service.TeacherService;



@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public TeacherVO get(String tId){
		return teacherDao.get(tId);
	}
	
	@Override
	public List<TeacherVO> list(Map<String, Object> map){
		return teacherDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teacherDao.count(map);
	}
	
	@Override
	public int save(TeacherVO teacher){
		return teacherDao.save(teacher);
	}
	
	@Override
	public int update(TeacherVO teacher){
		return teacherDao.update(teacher);
	}
	
	@Override
	public int remove(String tId){
		return teacherDao.remove(tId);
	}
	
	@Override
	public int batchRemove(String[] tIds){
		return teacherDao.batchRemove(tIds);
	}
	
}
