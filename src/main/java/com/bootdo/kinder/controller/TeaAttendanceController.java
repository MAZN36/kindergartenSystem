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

import com.bootdo.kinder.entity.TeaAttendanceVO;
import com.bootdo.kinder.service.TeaAttendanceService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 学生出勤表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/teaAttendance")
public class TeaAttendanceController {
	@Autowired
	private TeaAttendanceService teaAttendanceService;
	
	@GetMapping()
	@RequiresPermissions("kinder:teaAttendance:teaAttendance")
	String TeaAttendance(){
	    return "kinder/teaAttendance/teaAttendance";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:teaAttendance:teaAttendance")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TeaAttendanceVO> teaAttendanceList = teaAttendanceService.list(query);
		int total = teaAttendanceService.count(query);
		PageUtils pageUtils = new PageUtils(teaAttendanceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:teaAttendance:add")
	public String add(){
	    return "kinder/teaAttendance/teaAttendance-add";
	}

	@GetMapping("/edit/{aId}")
	@RequiresPermissions("kinder:teaAttendance:edit")
	public String edit(@PathVariable("aId") String aId,Model model){
		TeaAttendanceVO teaAttendance = teaAttendanceService.get(aId);
		model.addAttribute("teaAttendance", teaAttendance);
	    return "kinder/teaAttendance/teaAttendance-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:teaAttendance:add")
	public R save( TeaAttendanceVO teaAttendance){
		if(teaAttendanceService.save(teaAttendance)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:teaAttendance:edit")
	public R update( TeaAttendanceVO teaAttendance){
		teaAttendanceService.update(teaAttendance);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:teaAttendance:remove")
	public R remove( String aId){
		if(teaAttendanceService.remove(aId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:teaAttendance:batchRemove")
	public R remove(@RequestParam("ids[]") String[] aIds){
		teaAttendanceService.batchRemove(aIds);
		return R.ok();
	}
	
}
