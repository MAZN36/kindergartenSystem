package com.bootdo.kinder.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.kinder.entity.StudentInfoVO;

import java.util.List;
import java.util.Map;

/**
 * 家长+学生信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public interface StudentInfoService {
	
	StudentInfoVO get(String stuId);
	
	List<StudentInfoVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInfoVO studentInfo);
	
	int update(StudentInfoVO studentInfo);
	
	int remove(String stuId);
	
	int batchRemove(String[] stuIds);

	public PageUtils findPage(Query query);

	/**
	 * 校验学生是否存在
	 * @param studentNo
	 * @return
	 */
	public boolean verifyTeacherNo(String studentNo);
}
