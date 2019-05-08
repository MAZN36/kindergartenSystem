package com.bootdo.kinder.service.impl;

import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.StringUtils;
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

import com.bootdo.kinder.dao.mysql.StudentInfoDao;
import com.bootdo.kinder.entity.StudentInfoVO;
import com.bootdo.kinder.service.StudentInfoService;



@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	@Autowired
	private StudentInfoDao studentInfoDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;
	
	@Override
	public StudentInfoVO get(String stuId){
		return studentInfoDao.get(stuId);
	}
	
	@Override
	public List<StudentInfoVO> list(Map<String, Object> map){
		return studentInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoVO studentInfo){
		UserDO user = studentInfo.getUser();
		String roleId = dictService.getName("app_role", "student");
		String password = dictService.getName("app_password", "default");
		List<Long> roleIds = new ArrayList<>();
		roleIds.add(Long.parseLong(roleId));
		user.setRoleIds(roleIds);
		if (StringUtils.isNotBlank(studentInfo.getSClasss())){
			user.setDeptId(Long.parseLong(studentInfo.getSClasss()));
		}
		user.setStatus(1);
		user.setGmtCreate(new Date());
		user.setPassword(MD5Utils.encrypt(user.getUsername(), password));
		int save = userService.save(user);
		studentInfo.setSUserid(user.getUserId()+"");
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoVO studentInfo){
		int count = studentInfoDao.update(studentInfo);
		StudentInfoVO studentInfoVO = this.get(studentInfo.getStuId());
		UserDO user = studentInfo.getUser();
		user.setUserId(studentInfoVO.getUser().getUserId());
		if (StringUtils.isNotBlank(studentInfo.getSClasss())){
			user.setDeptId(Long.parseLong(studentInfo.getSClasss()));
		}
		user.setGmtModified(new Date());
		userDao.update(user);
		return count;
	}
	
	@Override
	public int remove(String stuId){
		StudentInfoVO studentInfoVO = this.get(stuId);
		if (studentInfoVO!=null){
			String userId = studentInfoVO.getSUserid();
			userDao.remove(Long.parseLong(userId));
		}
		return studentInfoDao.remove(stuId);
	}
	
	@Override
	public int batchRemove(String[] stuIds){
		return studentInfoDao.batchRemove(stuIds);
	}

	@Override
	public PageUtils findPage(Query query){
		List<StudentInfoVO> studentList = this.list(query);
		int total = this.count(query);
		PageUtils pageUtils = new PageUtils(studentList, total);
		return pageUtils;
	}

	/**
	 * 校验学生是否存在
	 * @param studentNo
	 * @return
	 */
	@Override
	public boolean verifyTeacherNo(String studentNo){
		Map<String, Object> query = new HashMap<>();
		query.put("sTudentid",studentNo);
		int count = this.count(query);
		if (count>0){
			return true;
		}
		return false;
	}
	
}
