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
public class TeaAttendanceVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String aId;
			//工号
		private String aStudentNo;
			//教师编号
		private String aTeaId;
			//上班时间
		private Date aStartDate;
			//下班时间
		private Date aEndDate;
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
	 * 设置：工号
	 */
	public void setAStudentNo(String aStudentNo) {
		this.aStudentNo = aStudentNo;
	}
	/**
	 * 获取：工号
	 */
	public String getAStudentNo() {
		return aStudentNo;
	}
	/**
	 * 设置：教师编号
	 */
	public void setATeaId(String aTeaId) {
		this.aTeaId = aTeaId;
	}
	/**
	 * 获取：教师编号
	 */
	public String getATeaId() {
		return aTeaId;
	}
	/**
	 * 设置：上班时间
	 */
	public void setAStartDate(Date aStartDate) {
		this.aStartDate = aStartDate;
	}
	/**
	 * 获取：上班时间
	 */
	public Date getAStartDate() {
		return aStartDate;
	}
	/**
	 * 设置：下班时间
	 */
	public void setAEndDate(Date aEndDate) {
		this.aEndDate = aEndDate;
	}
	/**
	 * 获取：下班时间
	 */
	public Date getAEndDate() {
		return aEndDate;
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
