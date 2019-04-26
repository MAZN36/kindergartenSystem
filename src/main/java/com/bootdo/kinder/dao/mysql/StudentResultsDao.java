package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.StudentResultsVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学生成绩表
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface StudentResultsDao {

	public StudentResultsVO get(String rId);

    public List<StudentResultsVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(StudentResultsVO studentResults);

    public int update(StudentResultsVO studentResults);

    public int remove(String r_Id);

    public int batchRemove(String[] rIds);
}
