package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 午餐表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class SchoolLunchVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String slId;
			//菜品名称
		private String slName;
			//菜品口味
		private String aStudentNo;
			//日期
		private Date aDate;
			//备注
		private String aNote;
	
		/**
	 * 设置：编号
	 */
	public void setSlId(String slId) {
		this.slId = slId;
	}
	/**
	 * 获取：编号
	 */
	public String getSlId() {
		return slId;
	}
	/**
	 * 设置：菜品名称
	 */
	public void setSlName(String slName) {
		this.slName = slName;
	}
	/**
	 * 获取：菜品名称
	 */
	public String getSlName() {
		return slName;
	}
	/**
	 * 设置：菜品口味
	 */
	public void setAStudentNo(String aStudentNo) {
		this.aStudentNo = aStudentNo;
	}
	/**
	 * 获取：菜品口味
	 */
	public String getAStudentNo() {
		return aStudentNo;
	}
	/**
	 * 设置：日期
	 */
	public void setADate(Date aDate) {
		this.aDate = aDate;
	}
	/**
	 * 获取：日期
	 */
	public Date getADate() {
		return aDate;
	}
	/**
	 * 设置：备注
	 */
	public void setANote(String aNote) {
		this.aNote = aNote;
	}
	/**
	 * 获取：备注
	 */
	public String getANote() {
		return aNote;
	}
}
