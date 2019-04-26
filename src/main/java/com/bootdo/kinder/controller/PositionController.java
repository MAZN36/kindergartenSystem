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

import com.bootdo.kinder.entity.PositionVO;
import com.bootdo.kinder.service.PositionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 教师职位
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
 
@Controller
@RequestMapping("/kinder/position")
public class PositionController {
	@Autowired
	private PositionService positionService;
	
	@GetMapping()
	@RequiresPermissions("kinder:position:position")
	String Position(){
	    return "kinder/position/position";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("kinder:position:position")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PositionVO> positionList = positionService.list(query);
		int total = positionService.count(query);
		PageUtils pageUtils = new PageUtils(positionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("kinder:position:add")
	public String add(){
	    return "kinder/position/position-add";
	}

	@GetMapping("/edit/{pid}")
	@RequiresPermissions("kinder:position:edit")
	public String edit(@PathVariable("pid") String pid,Model model){
		PositionVO position = positionService.get(pid);
		model.addAttribute("position", position);
	    return "kinder/position/position-edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("kinder:position:add")
	public R save( PositionVO position){
		if(positionService.save(position)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("kinder:position:edit")
	public R update( PositionVO position){
		positionService.update(position);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("kinder:position:remove")
	public R remove( String pid){
		if(positionService.remove(pid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("kinder:position:batchRemove")
	public R remove(@RequestParam("ids[]") String[] pids){
		positionService.batchRemove(pids);
		return R.ok();
	}
	
}
