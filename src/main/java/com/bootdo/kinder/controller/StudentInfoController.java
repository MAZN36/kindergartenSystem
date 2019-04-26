package com.bootdo.kinder.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.kinder.entity.StudentInfoVO;
import com.bootdo.kinder.service.StudentInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 家长+学生信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/studentInfo")
public class StudentInfoController {
	@Autowired
	private StudentInfoService studentInfoService;
	
	@GetMapping()
	@RequiresPermissions("kinder:studentInfo:studentInfo")
	String StudentInfo(){
	    return "kinder/studentInfo/studentInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:studentInfo:studentInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentInfoVO> studentInfoList = studentInfoService.list(query);
		int total = studentInfoService.count(query);
		PageUtils pageUtils = new PageUtils(studentInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:studentInfo:add")
	public String add(){
	    return "kinder/studentInfo/studentInfo-add";
	}

	@GetMapping("/edit/{stuId}")
	@RequiresPermissions("kinder:studentInfo:edit")
	public String edit(@PathVariable("stuId") String stuId,Model model){
		StudentInfoVO studentInfo = studentInfoService.get(stuId);
		model.addAttribute("studentInfo", studentInfo);
	    return "kinder/studentInfo/studentInfo-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:studentInfo:add")
	public R save( StudentInfoVO studentInfo){
		if(studentInfoService.save(studentInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:studentInfo:edit")
	public R update( StudentInfoVO studentInfo){
		studentInfoService.update(studentInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:studentInfo:remove")
	public R remove( String stuId){
		if(studentInfoService.remove(stuId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:studentInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] stuIds){
		studentInfoService.batchRemove(stuIds);
		return R.ok();
	}
	
}
