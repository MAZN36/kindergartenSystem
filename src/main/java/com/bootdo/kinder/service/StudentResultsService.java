package com.bootdo.kinder.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.kinder.entity.StudentResultsVO;

import java.util.List;
import java.util.Map;

/**
 * 学生成绩表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface StudentResultsService {
	
	StudentResultsVO get(String rId);
	
	List<StudentResultsVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentResultsVO studentResults);
	
	int update(StudentResultsVO studentResults);
	
	int remove(String rId);
	
	int batchRemove(String[] rIds);

	public PageUtils findPage(Query query);
}
