package com.bootdo.kinder.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 课程
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
public class CourseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
			//编号
		private String courseId;
			//课程名称
		private String courseName;
			//总学分
		private Integer courseSum;
			//课程时长
		private Integer courseDuration;
			//备注
		private String courseNote;
	
		/**
	 * 设置：编号
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：编号
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 获取：课程名称
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 设置：总学分
	 */
	public void setCourseSum(Integer courseSum) {
		this.courseSum = courseSum;
	}
	/**
	 * 获取：总学分
	 */
	public Integer getCourseSum() {
		return courseSum;
	}
	/**
	 * 设置：课程时长
	 */
	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}
	/**
	 * 获取：课程时长
	 */
	public Integer getCourseDuration() {
		return courseDuration;
	}
	/**
	 * 设置：备注
	 */
	public void setCourseNote(String courseNote) {
		this.courseNote = courseNote;
	}
	/**
	 * 获取：备注
	 */
	public String getCourseNote() {
		return courseNote;
	}
}
