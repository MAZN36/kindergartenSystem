package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.CourseVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface CourseDao {

	public CourseVO get(String courseId);

    public List<CourseVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(CourseVO course);

    public int update(CourseVO course);

    public int remove(String course_id);

    public int batchRemove(String[] courseIds);
}
