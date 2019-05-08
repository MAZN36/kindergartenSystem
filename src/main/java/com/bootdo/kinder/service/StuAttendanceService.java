package com.bootdo.kinder.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.kinder.entity.StuAttendanceVO;

import java.util.List;
import java.util.Map;

/**
 * 学生出勤表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface StuAttendanceService {
	
	StuAttendanceVO get(String aId);
	
	List<StuAttendanceVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StuAttendanceVO stuAttendance);
	
	int update(StuAttendanceVO stuAttendance);
	
	int remove(String aId);
	
	int batchRemove(String[] aIds);

	public PageUtils findPage(Query query);
}
