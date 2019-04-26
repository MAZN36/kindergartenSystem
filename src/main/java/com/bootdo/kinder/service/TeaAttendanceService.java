package com.bootdo.kinder.service;

import com.bootdo.kinder.entity.TeaAttendanceVO;

import java.util.List;
import java.util.Map;

/**
 * 学生出勤表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface TeaAttendanceService {
	
	TeaAttendanceVO get(String aId);
	
	List<TeaAttendanceVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeaAttendanceVO teaAttendance);
	
	int update(TeaAttendanceVO teaAttendance);
	
	int remove(String aId);
	
	int batchRemove(String[] aIds);
}
