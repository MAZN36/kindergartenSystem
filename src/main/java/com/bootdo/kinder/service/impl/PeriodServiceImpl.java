package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.PeriodDao;
import com.bootdo.kinder.entity.PeriodVO;
import com.bootdo.kinder.service.PeriodService;



@Service
public class PeriodServiceImpl implements PeriodService {
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	public PeriodVO get(String pId){
		return periodDao.get(pId);
	}
	
	@Override
	public List<PeriodVO> list(Map<String, Object> map){
		return periodDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return periodDao.count(map);
	}
	
	@Override
	public int save(PeriodVO period){
		return periodDao.save(period);
	}
	
	@Override
	public int update(PeriodVO period){
		return periodDao.update(period);
	}
	
	@Override
	public int remove(String pId){
		return periodDao.remove(pId);
	}
	
	@Override
	public int batchRemove(String[] pIds){
		return periodDao.batchRemove(pIds);
	}
	
}
