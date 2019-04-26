package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.StudentInfoVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 家长+学生信息
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface StudentInfoDao {

	public StudentInfoVO get(String stuId);

    public List<StudentInfoVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(StudentInfoVO studentInfo);

    public int update(StudentInfoVO studentInfo);

    public int remove(String stu_Id);

    public int batchRemove(String[] stuIds);
}
