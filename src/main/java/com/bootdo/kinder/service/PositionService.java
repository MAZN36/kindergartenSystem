package com.bootdo.kinder.service;

import com.bootdo.kinder.entity.PositionVO;

import java.util.List;
import java.util.Map;

/**
 * 教师职位
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface PositionService {
	
	PositionVO get(String pid);
	
	List<PositionVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PositionVO position);
	
	int update(PositionVO position);
	
	int remove(String pid);
	
	int batchRemove(String[] pids);
}
