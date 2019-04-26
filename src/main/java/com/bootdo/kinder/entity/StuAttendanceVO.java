package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 学生出勤表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class StuAttendanceVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String aId;
			//班级，引用外键
		private String aClasss;
			//学号
		private String aStudentNo;
			//学生编号
		private String aStuId;
			//日期
		private Date aDate;
			//课程名称
		private String aCourseName;
			//哪节课
		private String aPeriodId;
			//操作员/老师id
		private String aOperator;
			//出勤类型
		private String aAttendanceType;
			//备注
		private String aNote;
	
		/**
	 * 设置：编号
	 */
	public void setAId(String aId) {
		this.aId = aId;
	}
	/**
	 * 获取：编号
	 */
	public String getAId() {
		return aId;
	}
	/**
	 * 设置：班级，引用外键
	 */
	public void setAClasss(String aClasss) {
		this.aClasss = aClasss;
	}
	/**
	 * 获取：班级，引用外键
	 */
	public String getAClasss() {
		return aClasss;
	}
	/**
	 * 设置：学号
	 */
	public void setAStudentNo(String aStudentNo) {
		this.aStudentNo = aStudentNo;
	}
	/**
	 * 获取：学号
	 */
	public String getAStudentNo() {
		return aStudentNo;
	}
	/**
	 * 设置：学生编号
	 */
	public void setAStuId(String aStuId) {
		this.aStuId = aStuId;
	}
	/**
	 * 获取：学生编号
	 */
	public String getAStuId() {
		return aStuId;
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
	 * 设置：课程名称
	 */
	public void setACourseName(String aCourseName) {
		this.aCourseName = aCourseName;
	}
	/**
	 * 获取：课程名称
	 */
	public String getACourseName() {
		return aCourseName;
	}
	/**
	 * 设置：哪节课
	 */
	public void setAPeriodId(String aPeriodId) {
		this.aPeriodId = aPeriodId;
	}
	/**
	 * 获取：哪节课
	 */
	public String getAPeriodId() {
		return aPeriodId;
	}
	/**
	 * 设置：操作员/老师id
	 */
	public void setAOperator(String aOperator) {
		this.aOperator = aOperator;
	}
	/**
	 * 获取：操作员/老师id
	 */
	public String getAOperator() {
		return aOperator;
	}
	/**
	 * 设置：出勤类型
	 */
	public void setAAttendanceType(String aAttendanceType) {
		this.aAttendanceType = aAttendanceType;
	}
	/**
	 * 获取：出勤类型
	 */
	public String getAAttendanceType() {
		return aAttendanceType;
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
