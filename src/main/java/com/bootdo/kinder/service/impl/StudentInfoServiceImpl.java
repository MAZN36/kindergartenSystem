package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.StudentInfoDao;
import com.bootdo.kinder.entity.StudentInfoVO;
import com.bootdo.kinder.service.StudentInfoService;



@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	@Autowired
	private StudentInfoDao studentInfoDao;
	
	@Override
	public StudentInfoVO get(String stuId){
		return studentInfoDao.get(stuId);
	}
	
	@Override
	public List<StudentInfoVO> list(Map<String, Object> map){
		return studentInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoVO studentInfo){
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoVO studentInfo){
		return studentInfoDao.update(studentInfo);
	}
	
	@Override
	public int remove(String stuId){
		return studentInfoDao.remove(stuId);
	}
	
	@Override
	public int batchRemove(String[] stuIds){
		return studentInfoDao.batchRemove(stuIds);
	}
	
}
