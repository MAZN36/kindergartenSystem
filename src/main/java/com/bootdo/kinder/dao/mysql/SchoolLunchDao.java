package com.bootdo.kinder.dao.mysql;

import com.bootdo.kinder.entity.SchoolLunchVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 午餐表
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface SchoolLunchDao {

	public SchoolLunchVO get(String slId);

    public List<SchoolLunchVO> list(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public int save(SchoolLunchVO schoolLunch);

    public int update(SchoolLunchVO schoolLunch);

    public int remove(String sl_id);

    public int batchRemove(String[] slIds);
}
