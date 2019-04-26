package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.PeriodVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课时表,课程安排
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface PeriodDao {

	public PeriodVO get(String pId);

    public List<PeriodVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(PeriodVO period);

    public int update(PeriodVO period);

    public int remove(String p_Id);

    public int batchRemove(String[] pIds);
}
