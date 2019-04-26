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

import com.bootdo.kinder.entity.CourseVO;
import com.bootdo.kinder.service.CourseService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 课程
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping()
	@RequiresPermissions("kinder:course:course")
	String Course(){
	    return "kinder/course/course";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:course:course")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseVO> courseList = courseService.list(query);
		int total = courseService.count(query);
		PageUtils pageUtils = new PageUtils(courseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:course:add")
	public String add(){
	    return "kinder/course/course-add";
	}

	@GetMapping("/edit/{courseId}")
	@RequiresPermissions("kinder:course:edit")
	public String edit(@PathVariable("courseId") String courseId,Model model){
		CourseVO course = courseService.get(courseId);
		model.addAttribute("course", course);
	    return "kinder/course/course-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:course:add")
	public R save( CourseVO course){
		if(courseService.save(course)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:course:edit")
	public R update( CourseVO course){
		courseService.update(course);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:course:remove")
	public R remove( String courseId){
		if(courseService.remove(courseId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:course:batchRemove")
	public R remove(@RequestParam("ids[]") String[] courseIds){
		courseService.batchRemove(courseIds);
		return R.ok();
	}
	
}
