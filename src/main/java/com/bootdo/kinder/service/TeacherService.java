package com.bootdo.kinder.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.kinder.entity.TeacherVO;

import java.util.List;
import java.util.Map;

/**
 * 教师信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface TeacherService {
	
	TeacherVO get(String tId);
	
	List<TeacherVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TeacherVO teacher);
	
	int update(TeacherVO teacher);
	
	int remove(String tId);
	
	int batchRemove(String[] tIds);

	public PageUtils findPage(Query query);

	/**
	 * 校验老师是否存在
	 * @param teacherNo
	 * @return
	 */
	public boolean verifyTeacherNo(String teacherNo);
}
