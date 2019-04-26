package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.SchoolLunchDao;
import com.bootdo.kinder.entity.SchoolLunchVO;
import com.bootdo.kinder.service.SchoolLunchService;



@Service
public class SchoolLunchServiceImpl implements SchoolLunchService {
	@Autowired
	private SchoolLunchDao schoolLunchDao;
	
	@Override
	public SchoolLunchVO get(String slId){
		return schoolLunchDao.get(slId);
	}
	
	@Override
	public List<SchoolLunchVO> list(Map<String, Object> map){
		return schoolLunchDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolLunchDao.count(map);
	}
	
	@Override
	public int save(SchoolLunchVO schoolLunch){
		return schoolLunchDao.save(schoolLunch);
	}
	
	@Override
	public int update(SchoolLunchVO schoolLunch){
		return schoolLunchDao.update(schoolLunch);
	}
	
	@Override
	public int remove(String slId){
		return schoolLunchDao.remove(slId);
	}
	
	@Override
	public int batchRemove(String[] slIds){
		return schoolLunchDao.batchRemove(slIds);
	}
	
}
