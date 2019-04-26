package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 教师信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class TeacherVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//id
		private String tId;
			//用户id关联用户表
		private String tUserid;
			//工号
		private String tJnumber;
			//职位
		private String tPosition;
			//民族
		private String tNation;
			//政治面貌
		private String tPolitical;
			//身份证
		private String tCard;
			//学历
		private String tEducation;
			//家庭住址
		private String tHome;
			//状态
		private String tState;
			//备注
		private String tNote;
	
		/**
	 * 设置：id
	 */
	public void setTId(String tId) {
		this.tId = tId;
	}
	/**
	 * 获取：id
	 */
	public String getTId() {
		return tId;
	}
	/**
	 * 设置：用户id关联用户表
	 */
	public void setTUserid(String tUserid) {
		this.tUserid = tUserid;
	}
	/**
	 * 获取：用户id关联用户表
	 */
	public String getTUserid() {
		return tUserid;
	}
	/**
	 * 设置：工号
	 */
	public void setTJnumber(String tJnumber) {
		this.tJnumber = tJnumber;
	}
	/**
	 * 获取：工号
	 */
	public String getTJnumber() {
		return tJnumber;
	}
	/**
	 * 设置：职位
	 */
	public void setTPosition(String tPosition) {
		this.tPosition = tPosition;
	}
	/**
	 * 获取：职位
	 */
	public String getTPosition() {
		return tPosition;
	}
	/**
	 * 设置：民族
	 */
	public void setTNation(String tNation) {
		this.tNation = tNation;
	}
	/**
	 * 获取：民族
	 */
	public String getTNation() {
		return tNation;
	}
	/**
	 * 设置：政治面貌
	 */
	public void setTPolitical(String tPolitical) {
		this.tPolitical = tPolitical;
	}
	/**
	 * 获取：政治面貌
	 */
	public String getTPolitical() {
		return tPolitical;
	}
	/**
	 * 设置：身份证
	 */
	public void setTCard(String tCard) {
		this.tCard = tCard;
	}
	/**
	 * 获取：身份证
	 */
	public String getTCard() {
		return tCard;
	}
	/**
	 * 设置：学历
	 */
	public void setTEducation(String tEducation) {
		this.tEducation = tEducation;
	}
	/**
	 * 获取：学历
	 */
	public String getTEducation() {
		return tEducation;
	}
	/**
	 * 设置：家庭住址
	 */
	public void setTHome(String tHome) {
		this.tHome = tHome;
	}
	/**
	 * 获取：家庭住址
	 */
	public String getTHome() {
		return tHome;
	}
	/**
	 * 设置：状态
	 */
	public void setTState(String tState) {
		this.tState = tState;
	}
	/**
	 * 获取：状态
	 */
	public String getTState() {
		return tState;
	}
	/**
	 * 设置：备注
	 */
	public void setTNote(String tNote) {
		this.tNote = tNote;
	}
	/**
	 * 获取：备注
	 */
	public String getTNote() {
		return tNote;
	}
}
