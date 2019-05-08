package com.bootdo.kinder.service.impl;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.kinder.entity.StuAttendanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.TeaAttendanceDao;
import com.bootdo.kinder.entity.TeaAttendanceVO;
import com.bootdo.kinder.service.TeaAttendanceService;



@Service
public class TeaAttendanceServiceImpl implements TeaAttendanceService {
	@Autowired
	private TeaAttendanceDao teaAttendanceDao;
	
	@Override
	public TeaAttendanceVO get(String aId){
		return teaAttendanceDao.get(aId);
	}
	
	@Override
	public List<TeaAttendanceVO> list(Map<String, Object> map){
		return teaAttendanceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teaAttendanceDao.count(map);
	}
	
	@Override
	public int save(TeaAttendanceVO teaAttendance){
		return teaAttendanceDao.save(teaAttendance);
	}
	
	@Override
	public int update(TeaAttendanceVO teaAttendance){
		return teaAttendanceDao.update(teaAttendance);
	}
	
	@Override
	public int remove(String aId){
		return teaAttendanceDao.remove(aId);
	}
	
	@Override
	public int batchRemove(String[] aIds){
		return teaAttendanceDao.batchRemove(aIds);
	}

	@Override
	public PageUtils findPage(Query query){
		List<TeaAttendanceVO> teaAttendanceList = this.list(query);
		int total = this.count(query);
		PageUtils pageUtils = new PageUtils(teaAttendanceList, total);
		return pageUtils;
	}
}
