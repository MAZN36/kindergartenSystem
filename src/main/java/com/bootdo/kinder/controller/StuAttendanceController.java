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

import com.bootdo.kinder.entity.StuAttendanceVO;
import com.bootdo.kinder.service.StuAttendanceService;
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
@RequestMapping("/kinder/stuAttendance")
public class StuAttendanceController {
	@Autowired
	private StuAttendanceService stuAttendanceService;
	
	@GetMapping()
	@RequiresPermissions("kinder:stuAttendance:stuAttendance")
	String StuAttendance(){
	    return "kinder/stuAttendance/stuAttendance";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:stuAttendance:stuAttendance")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StuAttendanceVO> stuAttendanceList = stuAttendanceService.list(query);
		int total = stuAttendanceService.count(query);
		PageUtils pageUtils = new PageUtils(stuAttendanceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:stuAttendance:add")
	public String add(){
	    return "kinder/stuAttendance/stuAttendance-add";
	}

	@GetMapping("/edit/{aId}")
	@RequiresPermissions("kinder:stuAttendance:edit")
	public String edit(@PathVariable("aId") String aId,Model model){
		StuAttendanceVO stuAttendance = stuAttendanceService.get(aId);
		model.addAttribute("stuAttendance", stuAttendance);
	    return "kinder/stuAttendance/stuAttendance-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:stuAttendance:add")
	public R save( StuAttendanceVO stuAttendance){
		if(stuAttendanceService.save(stuAttendance)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:stuAttendance:edit")
	public R update( StuAttendanceVO stuAttendance){
		stuAttendanceService.update(stuAttendance);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:stuAttendance:remove")
	public R remove( String aId){
		if(stuAttendanceService.remove(aId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:stuAttendance:batchRemove")
	public R remove(@RequestParam("ids[]") String[] aIds){
		stuAttendanceService.batchRemove(aIds);
		return R.ok();
	}
	
}
