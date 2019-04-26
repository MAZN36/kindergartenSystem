package com.bootdo.kinder.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 学生成绩表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class StudentResultsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String rId;
			//学号
		private String rStudentNo;
			//学生编号
		private String rStuId;
			//考试时间
		private Date rExamDate;
			//课程名称，引用外键
		private String pCourse;
			//班级，引用外键
		private String rClasss;
			//分数
		private Double rScore;
			//是否通过
		private String rPass;
			//备注
		private String rNote;
	
		/**
	 * 设置：编号
	 */
	public void setRId(String rId) {
		this.rId = rId;
	}
	/**
	 * 获取：编号
	 */
	public String getRId() {
		return rId;
	}
	/**
	 * 设置：学号
	 */
	public void setRStudentNo(String rStudentNo) {
		this.rStudentNo = rStudentNo;
	}
	/**
	 * 获取：学号
	 */
	public String getRStudentNo() {
		return rStudentNo;
	}
	/**
	 * 设置：学生编号
	 */
	public void setRStuId(String rStuId) {
		this.rStuId = rStuId;
	}
	/**
	 * 获取：学生编号
	 */
	public String getRStuId() {
		return rStuId;
	}
	/**
	 * 设置：考试时间
	 */
	public void setRExamDate(Date rExamDate) {
		this.rExamDate = rExamDate;
	}
	/**
	 * 获取：考试时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRExamDate() {
		return rExamDate;
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
	 * 设置：班级，引用外键
	 */
	public void setRClasss(String rClasss) {
		this.rClasss = rClasss;
	}
	/**
	 * 获取：班级，引用外键
	 */
	public String getRClasss() {
		return rClasss;
	}
	/**
	 * 设置：分数
	 */
	public void setRScore(Double rScore) {
		this.rScore = rScore;
	}
	/**
	 * 获取：分数
	 */
	public Double getRScore() {
		return rScore;
	}
	/**
	 * 设置：是否通过
	 */
	public void setRPass(String rPass) {
		this.rPass = rPass;
	}
	/**
	 * 获取：是否通过
	 */
	public String getRPass() {
		return rPass;
	}
	/**
	 * 设置：备注
	 */
	public void setRNote(String rNote) {
		this.rNote = rNote;
	}
	/**
	 * 获取：备注
	 */
	public String getRNote() {
		return rNote;
	}
}
