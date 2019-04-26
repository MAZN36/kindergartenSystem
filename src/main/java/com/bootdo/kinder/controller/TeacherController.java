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

import com.bootdo.kinder.entity.TeacherVO;
import com.bootdo.kinder.service.TeacherService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 教师信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping()
	@RequiresPermissions("kinder:teacher:teacher")
	String Teacher(){
	    return "kinder/teacher/teacher";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:teacher:teacher")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TeacherVO> teacherList = teacherService.list(query);
		int total = teacherService.count(query);
		PageUtils pageUtils = new PageUtils(teacherList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:teacher:add")
	public String add(){
	    return "kinder/teacher/teacher-add";
	}

	@GetMapping("/edit/{tId}")
	@RequiresPermissions("kinder:teacher:edit")
	public String edit(@PathVariable("tId") String tId,Model model){
		TeacherVO teacher = teacherService.get(tId);
		model.addAttribute("teacher", teacher);
	    return "kinder/teacher/teacher-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:teacher:add")
	public R save( TeacherVO teacher){
		if(teacherService.save(teacher)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:teacher:edit")
	public R update( TeacherVO teacher){
		teacherService.update(teacher);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:teacher:remove")
	public R remove( String tId){
		if(teacherService.remove(tId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:teacher:batchRemove")
	public R remove(@RequestParam("ids[]") String[] tIds){
		teacherService.batchRemove(tIds);
		return R.ok();
	}
	
}
