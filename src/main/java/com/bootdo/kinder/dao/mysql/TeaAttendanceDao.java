package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.TeaAttendanceVO;

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
public interface TeaAttendanceDao {

	public TeaAttendanceVO get(String aId);

    public List<TeaAttendanceVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(TeaAttendanceVO teaAttendance);

    public int update(TeaAttendanceVO teaAttendance);

    public int remove(String a_Id);

    public int batchRemove(String[] aIds);
}
