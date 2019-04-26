package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.StuAttendanceDao;
import com.bootdo.kinder.entity.StuAttendanceVO;
import com.bootdo.kinder.service.StuAttendanceService;



@Service
public class StuAttendanceServiceImpl implements StuAttendanceService {
	@Autowired
	private StuAttendanceDao stuAttendanceDao;
	
	@Override
	public StuAttendanceVO get(String aId){
		return stuAttendanceDao.get(aId);
	}
	
	@Override
	public List<StuAttendanceVO> list(Map<String, Object> map){
		return stuAttendanceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return stuAttendanceDao.count(map);
	}
	
	@Override
	public int save(StuAttendanceVO stuAttendance){
		return stuAttendanceDao.save(stuAttendance);
	}
	
	@Override
	public int update(StuAttendanceVO stuAttendance){
		return stuAttendanceDao.update(stuAttendance);
	}
	
	@Override
	public int remove(String aId){
		return stuAttendanceDao.remove(aId);
	}
	
	@Override
	public int batchRemove(String[] aIds){
		return stuAttendanceDao.batchRemove(aIds);
	}
	
}
