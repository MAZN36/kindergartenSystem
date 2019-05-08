package com.bootdo.kinder.service.impl;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.kinder.entity.StudentInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.StudentResultsDao;
import com.bootdo.kinder.entity.StudentResultsVO;
import com.bootdo.kinder.service.StudentResultsService;



@Service
public class StudentResultsServiceImpl implements StudentResultsService {
	@Autowired
	private StudentResultsDao studentResultsDao;
	
	@Override
	public StudentResultsVO get(String rId){
		return studentResultsDao.get(rId);
	}
	
	@Override
	public List<StudentResultsVO> list(Map<String, Object> map){
		return studentResultsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentResultsDao.count(map);
	}
	
	@Override
	public int save(StudentResultsVO studentResults){
		return studentResultsDao.save(studentResults);
	}
	
	@Override
	public int update(StudentResultsVO studentResults){
		return studentResultsDao.update(studentResults);
	}
	
	@Override
	public int remove(String rId){
		return studentResultsDao.remove(rId);
	}
	
	@Override
	public int batchRemove(String[] rIds){
		return studentResultsDao.batchRemove(rIds);
	}

	@Override
	public PageUtils findPage(Query query){
		List<StudentResultsVO> studentResultsList = this.list(query);
		int total = this.count(query);
		PageUtils pageUtils = new PageUtils(studentResultsList, total);
		return pageUtils;
	}
	
}
