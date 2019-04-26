package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.PositionVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 教师职位
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface PositionDao {

	public PositionVO get(String pid);

    public List<PositionVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(PositionVO position);

    public int update(PositionVO position);

    public int remove(String pid);

    public int batchRemove(String[] pids);
}
