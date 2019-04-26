package com.bootdo.kinder.service;

import com.bootdo.kinder.entity.SchoolLunchVO;

import java.util.List;
import java.util.Map;

/**
 * 午餐表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface SchoolLunchService {
	
	SchoolLunchVO get(String slId);
	
	List<SchoolLunchVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolLunchVO schoolLunch);
	
	int update(SchoolLunchVO schoolLunch);
	
	int remove(String slId);
	
	int batchRemove(String[] slIds);
}
