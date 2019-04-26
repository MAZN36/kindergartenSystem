package com.bootdo.kinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.PositionDao;
import com.bootdo.kinder.entity.PositionVO;
import com.bootdo.kinder.service.PositionService;



@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;
	
	@Override
	public PositionVO get(String pid){
		return positionDao.get(pid);
	}
	
	@Override
	public List<PositionVO> list(Map<String, Object> map){
		return positionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return positionDao.count(map);
	}
	
	@Override
	public int save(PositionVO position){
		return positionDao.save(position);
	}
	
	@Override
	public int update(PositionVO position){
		return positionDao.update(position);
	}
	
	@Override
	public int remove(String pid){
		return positionDao.remove(pid);
	}
	
	@Override
	public int batchRemove(String[] pids){
		return positionDao.batchRemove(pids);
	}
	
}
