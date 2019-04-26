package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.TeacherVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 教师信息
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface TeacherDao {

	public TeacherVO get(String tId);

    public List<TeacherVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(TeacherVO teacher);

    public int update(TeacherVO teacher);

    public int remove(String t_id);

    public int batchRemove(String[] tIds);
}
