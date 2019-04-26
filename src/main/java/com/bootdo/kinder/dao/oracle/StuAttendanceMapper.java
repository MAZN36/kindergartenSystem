package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.StuAttendanceVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 学生出勤表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface StuAttendanceMapper {

	@Select("select `a_Id`, `a_Classs`, `a_Student_No`, `a_Stu_Id`, `a_Date`, `a_Course_Name`, `a_period_Id`, `a_Operator`, `a_Attendance_Type`, `a_Note` from s_stu_attendance where a_Id = #{id}")
	StuAttendanceVO get(String aId);
	
	@Select("<script>" +
	"select * from s_stu_attendance " + 
			"<where>" + 
		  		  "<if test=\"aId != null and aId != ''\">"+ "and a_Id = #{aId} " + "</if>" + 
		  		  "<if test=\"aClasss != null and aClasss != ''\">"+ "and a_Classs = #{aClasss} " + "</if>" + 
		  		  "<if test=\"aStudentNo != null and aStudentNo != ''\">"+ "and a_Student_No = #{aStudentNo} " + "</if>" + 
		  		  "<if test=\"aStuId != null and aStuId != ''\">"+ "and a_Stu_Id = #{aStuId} " + "</if>" + 
		  		  "<if test=\"aDate != null and aDate != ''\">"+ "and a_Date = #{aDate} " + "</if>" + 
		  		  "<if test=\"aCourseName != null and aCourseName != ''\">"+ "and a_Course_Name = #{aCourseName} " + "</if>" + 
		  		  "<if test=\"aPeriodId != null and aPeriodId != ''\">"+ "and a_period_Id = #{aPeriodId} " + "</if>" + 
		  		  "<if test=\"aOperator != null and aOperator != ''\">"+ "and a_Operator = #{aOperator} " + "</if>" + 
		  		  "<if test=\"aAttendanceType != null and aAttendanceType != ''\">"+ "and a_Attendance_Type = #{aAttendanceType} " + "</if>" + 
		  		  "<if test=\"aNote != null and aNote != ''\">"+ "and a_Note = #{aNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by a_Id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<StuAttendanceVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_stu_attendance " + 
			"<where>" + 
		  		  "<if test=\"aId != null and aId != ''\">"+ "and a_Id = #{aId} " + "</if>" + 
		  		  "<if test=\"aClasss != null and aClasss != ''\">"+ "and a_Classs = #{aClasss} " + "</if>" + 
		  		  "<if test=\"aStudentNo != null and aStudentNo != ''\">"+ "and a_Student_No = #{aStudentNo} " + "</if>" + 
		  		  "<if test=\"aStuId != null and aStuId != ''\">"+ "and a_Stu_Id = #{aStuId} " + "</if>" + 
		  		  "<if test=\"aDate != null and aDate != ''\">"+ "and a_Date = #{aDate} " + "</if>" + 
		  		  "<if test=\"aCourseName != null and aCourseName != ''\">"+ "and a_Course_Name = #{aCourseName} " + "</if>" + 
		  		  "<if test=\"aPeriodId != null and aPeriodId != ''\">"+ "and a_period_Id = #{aPeriodId} " + "</if>" + 
		  		  "<if test=\"aOperator != null and aOperator != ''\">"+ "and a_Operator = #{aOperator} " + "</if>" + 
		  		  "<if test=\"aAttendanceType != null and aAttendanceType != ''\">"+ "and a_Attendance_Type = #{aAttendanceType} " + "</if>" + 
		  		  "<if test=\"aNote != null and aNote != ''\">"+ "and a_Note = #{aNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_stu_attendance (`a_Id`, `a_Classs`, `a_Student_No`, `a_Stu_Id`, `a_Date`, `a_Course_Name`, `a_period_Id`, `a_Operator`, `a_Attendance_Type`, `a_Note`)"
	+ "values (#{aId}, #{aClasss}, #{aStudentNo}, #{aStuId}, #{aDate}, #{aCourseName}, #{aPeriodId}, #{aOperator}, #{aAttendanceType}, #{aNote})")
	int save(StuAttendanceVO stuAttendance);
	
	@Update("<script>"+ 
			"update s_stu_attendance " + 
					"<set>" + 
		            "<if test=\"aId != null\">`a_Id` = #{aId}, </if>" + 
                    "<if test=\"aClasss != null\">`a_Classs` = #{aClasss}, </if>" + 
                    "<if test=\"aStudentNo != null\">`a_Student_No` = #{aStudentNo}, </if>" + 
                    "<if test=\"aStuId != null\">`a_Stu_Id` = #{aStuId}, </if>" + 
                    "<if test=\"aDate != null\">`a_Date` = #{aDate}, </if>" + 
                    "<if test=\"aCourseName != null\">`a_Course_Name` = #{aCourseName}, </if>" + 
                    "<if test=\"aPeriodId != null\">`a_period_Id` = #{aPeriodId}, </if>" + 
                    "<if test=\"aOperator != null\">`a_Operator` = #{aOperator}, </if>" + 
                    "<if test=\"aAttendanceType != null\">`a_Attendance_Type` = #{aAttendanceType}, </if>" + 
                    "<if test=\"aNote != null\">`a_Note` = #{aNote}, </if>" + 
          					"</set>" + 
					"where a_Id = #{aId}"+
			"</script>")
	int update(StuAttendanceVO stuAttendance);
	
	@Delete("delete from s_stu_attendance where a_Id =#{aId}")
	int remove(String a_Id);
	
	@Delete("<script>"+ 
			"delete from s_stu_attendance where a_Id in " + 
					"<foreach item=\"aId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{aId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] aIds);
}
