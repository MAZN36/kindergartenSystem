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

import com.bootdo.kinder.entity.StudentResultsVO;
import com.bootdo.kinder.service.StudentResultsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 学生成绩表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/studentResults")
public class StudentResultsController {
	@Autowired
	private StudentResultsService studentResultsService;
	
	@GetMapping()
	@RequiresPermissions("kinder:studentResults:studentResults")
	String StudentResults(){
	    return "kinder/studentResults/studentResults";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:studentResults:studentResults")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentResultsVO> studentResultsList = studentResultsService.list(query);
		int total = studentResultsService.count(query);
		PageUtils pageUtils = new PageUtils(studentResultsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:studentResults:add")
	public String add(){
	    return "kinder/studentResults/studentResults-add";
	}

	@GetMapping("/edit/{rId}")
	@RequiresPermissions("kinder:studentResults:edit")
	public String edit(@PathVariable("rId") String rId,Model model){
		StudentResultsVO studentResults = studentResultsService.get(rId);
		model.addAttribute("studentResults", studentResults);
	    return "kinder/studentResults/studentResults-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:studentResults:add")
	public R save( StudentResultsVO studentResults){
		if(studentResultsService.save(studentResults)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:studentResults:edit")
	public R update( StudentResultsVO studentResults){
		studentResultsService.update(studentResults);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:studentResults:remove")
	public R remove( String rId){
		if(studentResultsService.remove(rId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:studentResults:batchRemove")
	public R remove(@RequestParam("ids[]") String[] rIds){
		studentResultsService.batchRemove(rIds);
		return R.ok();
	}
	
}
