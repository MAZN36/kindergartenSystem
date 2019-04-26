package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 家长+学生信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class StudentInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String stuId;
			//关联用户表
		private String sUserid;
			//学号
		private String sTudentid;
			//班级
		private String sClasss;
			//学生姓名
		private String sName;
			//学生性别 1:男 0:女
		private String sSex;
			//民族
		private String sNational;
			//学生出生日期
		private Date sDate;
			//学生籍贯
		private String sNativePlace;
			//家庭住址
		private String sAddress;
			//家长姓名
		private String sParentsName;
			//家长号码
		private String sParentsNumber;
			//手机号码
		private String sNumber;
			//入学时间
		private Date sEntranceDate;
			//QQ号码
		private String sQq;
			//状态
		private String sState;
			//备注
		private String sNote;
	
		/**
	 * 设置：编号
	 */
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	/**
	 * 获取：编号
	 */
	public String getStuId() {
		return stuId;
	}
	/**
	 * 设置：关联用户表
	 */
	public void setSUserid(String sUserid) {
		this.sUserid = sUserid;
	}
	/**
	 * 获取：关联用户表
	 */
	public String getSUserid() {
		return sUserid;
	}
	/**
	 * 设置：学号
	 */
	public void setSTudentid(String sTudentid) {
		this.sTudentid = sTudentid;
	}
	/**
	 * 获取：学号
	 */
	public String getSTudentid() {
		return sTudentid;
	}
	/**
	 * 设置：班级
	 */
	public void setSClasss(String sClasss) {
		this.sClasss = sClasss;
	}
	/**
	 * 获取：班级
	 */
	public String getSClasss() {
		return sClasss;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setSName(String sName) {
		this.sName = sName;
	}
	/**
	 * 获取：学生姓名
	 */
	public String getSName() {
		return sName;
	}
	/**
	 * 设置：学生性别 1:男 0:女
	 */
	public void setSSex(String sSex) {
		this.sSex = sSex;
	}
	/**
	 * 获取：学生性别 1:男 0:女
	 */
	public String getSSex() {
		return sSex;
	}
	/**
	 * 设置：民族
	 */
	public void setSNational(String sNational) {
		this.sNational = sNational;
	}
	/**
	 * 获取：民族
	 */
	public String getSNational() {
		return sNational;
	}
	/**
	 * 设置：学生出生日期
	 */
	public void setSDate(Date sDate) {
		this.sDate = sDate;
	}
	/**
	 * 获取：学生出生日期
	 */
	public Date getSDate() {
		return sDate;
	}
	/**
	 * 设置：学生籍贯
	 */
	public void setSNativePlace(String sNativePlace) {
		this.sNativePlace = sNativePlace;
	}
	/**
	 * 获取：学生籍贯
	 */
	public String getSNativePlace() {
		return sNativePlace;
	}
	/**
	 * 设置：家庭住址
	 */
	public void setSAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	/**
	 * 获取：家庭住址
	 */
	public String getSAddress() {
		return sAddress;
	}
	/**
	 * 设置：家长姓名
	 */
	public void setSParentsName(String sParentsName) {
		this.sParentsName = sParentsName;
	}
	/**
	 * 获取：家长姓名
	 */
	public String getSParentsName() {
		return sParentsName;
	}
	/**
	 * 设置：家长号码
	 */
	public void setSParentsNumber(String sParentsNumber) {
		this.sParentsNumber = sParentsNumber;
	}
	/**
	 * 获取：家长号码
	 */
	public String getSParentsNumber() {
		return sParentsNumber;
	}
	/**
	 * 设置：手机号码
	 */
	public void setSNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	/**
	 * 获取：手机号码
	 */
	public String getSNumber() {
		return sNumber;
	}
	/**
	 * 设置：入学时间
	 */
	public void setSEntranceDate(Date sEntranceDate) {
		this.sEntranceDate = sEntranceDate;
	}
	/**
	 * 获取：入学时间
	 */
	public Date getSEntranceDate() {
		return sEntranceDate;
	}
	/**
	 * 设置：QQ号码
	 */
	public void setSQq(String sQq) {
		this.sQq = sQq;
	}
	/**
	 * 获取：QQ号码
	 */
	public String getSQq() {
		return sQq;
	}
	/**
	 * 设置：状态
	 */
	public void setSState(String sState) {
		this.sState = sState;
	}
	/**
	 * 获取：状态
	 */
	public String getSState() {
		return sState;
	}
	/**
	 * 设置：备注
	 */
	public void setSNote(String sNote) {
		this.sNote = sNote;
	}
	/**
	 * 获取：备注
	 */
	public String getSNote() {
		return sNote;
	}
}
