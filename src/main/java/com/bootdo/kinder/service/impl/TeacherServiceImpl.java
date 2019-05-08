package com.bootdo.kinder.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.kinder.dao.mysql.TeacherDao;
import com.bootdo.kinder.entity.TeacherVO;
import com.bootdo.kinder.service.TeacherService;



@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;
	@Override
	public TeacherVO get(String tId){
		return teacherDao.get(tId);
	}
	
	@Override
	public List<TeacherVO> list(Map<String, Object> map){
		return teacherDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teacherDao.count(map);
	}
	
	@Override
	public int save(TeacherVO teacher){
		UserDO user = teacher.getUser();
		String roleId = dictService.getName("app_role", "teacher");
		String password = dictService.getName("app_password", "default");
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(Long.parseLong(roleId));
		user.setRoleIds(roleIds);
		user.setGmtCreate(new Date());
		user.setStatus(1);
		user.setPassword(MD5Utils.encrypt(user.getUsername(), password));
		int save = userService.save(user);
		teacher.setTUserid(user.getUserId()+"");
		return teacherDao.save(teacher);
	}
	
	@Override
	public int update(TeacherVO teacher){
		int count = teacherDao.update(teacher);
		TeacherVO teacherVO = this.get(teacher.getTId());
		UserDO user = teacher.getUser();
		user.setUserId(teacherVO.getUser().getUserId());
		user.setGmtModified(new Date());
		userDao.update(user);
		return count;
	}
	
	@Override
	public int remove(String tId){
		TeacherVO teacherVO = this.get(tId);
		if (teacherVO!=null){
			String userId = teacherVO.getTUserid();
			userDao.remove(Long.parseLong(userId));
		}
		return teacherDao.remove(tId);
	}
	
	@Override
	public int batchRemove(String[] tIds){
		return teacherDao.batchRemove(tIds);
	}

	@Override
	public PageUtils findPage(Query query){
		List<TeacherVO> teacherList = this.list(query);
		int total = this.count(query);
		PageUtils pageUtils = new PageUtils(teacherList, total);
		return pageUtils;
	}

	/**
	 * 校验老师是否存在
	 * @param teacherNo
	 * @return
	 */
	public boolean verifyTeacherNo(String teacherNo){
		Map<String, Object> query = new HashMap<>();
		query.put("tJnumber",teacherNo);
		int count = this.count(query);
		if (count>0){
			return true;
		}
		return false;
	}

	
}
