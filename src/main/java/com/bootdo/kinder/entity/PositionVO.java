package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 教师职位
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class PositionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//id
		private String pid;
			//类型名称
		private String pname;
			//备注
		private String pnote;
	
		/**
	 * 设置：id
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取：id
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置：类型名称
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * 获取：类型名称
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * 设置：备注
	 */
	public void setPnote(String pnote) {
		this.pnote = pnote;
	}
	/**
	 * 获取：备注
	 */
	public String getPnote() {
		return pnote;
	}
}
