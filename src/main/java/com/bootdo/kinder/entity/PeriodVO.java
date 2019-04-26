package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 课时表,课程安排
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class PeriodVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String pId;
			//班级，引用外键
		private String pClasss;
			//课程名称，引用外键
		private String pCourse;
			//开始时间
		private Date pStartdate;
			//结束时间
		private Date pEndDate;
			//备注
		private String pNote;
	
		/**
	 * 设置：编号
	 */
	public void setPId(String pId) {
		this.pId = pId;
	}
	/**
	 * 获取：编号
	 */
	public String getPId() {
		return pId;
	}
	/**
	 * 设置：班级，引用外键
	 */
	public void setPClasss(String pClasss) {
		this.pClasss = pClasss;
	}
	/**
	 * 获取：班级，引用外键
	 */
	public String getPClasss() {
		return pClasss;
	}
	/**
	 * 设置：课程名称，引用外键
	 */
	public void setPCourse(String pCourse) {
		this.pCourse = pCourse;
	}
	/**
	 * 获取：课程名称，引用外键
	 */
	public String getPCourse() {
		return pCourse;
	}
	/**
	 * 设置：开始时间
	 */
	public void setPStartdate(Date pStartdate) {
		this.pStartdate = pStartdate;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getPStartdate() {
		return pStartdate;
	}
	/**
	 * 设置：结束时间
	 */
	public void setPEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getPEndDate() {
		return pEndDate;
	}
	/**
	 * 设置：备注
	 */
	public void setPNote(String pNote) {
		this.pNote = pNote;
	}
	/**
	 * 获取：备注
	 */
	public String getPNote() {
		return pNote;
	}
}
