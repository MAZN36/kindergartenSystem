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

import com.bootdo.kinder.entity.SchoolLunchVO;
import com.bootdo.kinder.service.SchoolLunchService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 午餐表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/schoolLunch")
public class SchoolLunchController {
	@Autowired
	private SchoolLunchService schoolLunchService;
	
	@GetMapping()
	@RequiresPermissions("kinder:schoolLunch:schoolLunch")
	String SchoolLunch(){
	    return "kinder/schoolLunch/schoolLunch";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:schoolLunch:schoolLunch")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolLunchVO> schoolLunchList = schoolLunchService.list(query);
		int total = schoolLunchService.count(query);
		PageUtils pageUtils = new PageUtils(schoolLunchList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:schoolLunch:add")
	public String add(){
	    return "kinder/schoolLunch/schoolLunch-add";
	}

	@GetMapping("/edit/{slId}")
	@RequiresPermissions("kinder:schoolLunch:edit")
	public String edit(@PathVariable("slId") String slId,Model model){
		SchoolLunchVO schoolLunch = schoolLunchService.get(slId);
		model.addAttribute("schoolLunch", schoolLunch);
	    return "kinder/schoolLunch/schoolLunch-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:schoolLunch:add")
	public R save( SchoolLunchVO schoolLunch){
		if(schoolLunchService.save(schoolLunch)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:schoolLunch:edit")
	public R update( SchoolLunchVO schoolLunch){
		schoolLunchService.update(schoolLunch);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:schoolLunch:remove")
	public R remove( String slId){
		if(schoolLunchService.remove(slId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:schoolLunch:batchRemove")
	public R remove(@RequestParam("ids[]") String[] slIds){
		schoolLunchService.batchRemove(slIds);
		return R.ok();
	}
	
}
