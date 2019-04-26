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

import com.bootdo.kinder.entity.PeriodVO;
import com.bootdo.kinder.service.PeriodService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 课时表,课程安排
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/period")
public class PeriodController {
	@Autowired
	private PeriodService periodService;
	
	@GetMapping()
	@RequiresPermissions("kinder:period:period")
	String Period(){
	    return "kinder/period/period";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:period:period")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PeriodVO> periodList = periodService.list(query);
		int total = periodService.count(query);
		PageUtils pageUtils = new PageUtils(periodList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:period:add")
	public String add(){
	    return "kinder/period/period-add";
	}

	@GetMapping("/edit/{pId}")
	@RequiresPermissions("kinder:period:edit")
	public String edit(@PathVariable("pId") String pId,Model model){
		PeriodVO period = periodService.get(pId);
		model.addAttribute("period", period);
	    return "kinder/period/period-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:period:add")
	public R save( PeriodVO period){
		if(periodService.save(period)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:period:edit")
	public R update( PeriodVO period){
		periodService.update(period);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:period:remove")
	public R remove( String pId){
		if(periodService.remove(pId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:period:batchRemove")
	public R remove(@RequestParam("ids[]") String[] pIds){
		periodService.batchRemove(pIds);
		return R.ok();
	}
	
}
