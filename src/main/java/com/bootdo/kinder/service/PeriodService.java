package com.bootdo.kinder.service;

import com.bootdo.kinder.entity.PeriodVO;

import java.util.List;
import java.util.Map;

/**
 * 课时表,课程安排
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface PeriodService {
	
	PeriodVO get(String pId);
	
	List<PeriodVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PeriodVO period);
	
	int update(PeriodVO period);
	
	int remove(String pId);
	
	int batchRemove(String[] pIds);
}
