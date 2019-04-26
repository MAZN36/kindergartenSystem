package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.StuAttendanceVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学生出勤表
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface StuAttendanceDao {

	public StuAttendanceVO get(String aId);

    public List<StuAttendanceVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(StuAttendanceVO stuAttendance);

    public int update(StuAttendanceVO stuAttendance);

    public int remove(String a_Id);

    public int batchRemove(String[] aIds);
}
