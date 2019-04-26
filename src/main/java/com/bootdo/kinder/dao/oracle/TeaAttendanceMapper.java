package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.TeaAttendanceVO;

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
public interface TeaAttendanceMapper {

	@Select("select `a_Id`, `a_Student_No`, `a_Tea_Id`, `a_Start_Date`, `a_End_Date`, `a_Attendance_Type`, `a_Note` from s_tea_attendance where a_Id = #{id}")
	TeaAttendanceVO get(String aId);
	
	@Select("<script>" +
	"select * from s_tea_attendance " + 
			"<where>" + 
		  		  "<if test=\"aId != null and aId != ''\">"+ "and a_Id = #{aId} " + "</if>" + 
		  		  "<if test=\"aStudentNo != null and aStudentNo != ''\">"+ "and a_Student_No = #{aStudentNo} " + "</if>" + 
		  		  "<if test=\"aTeaId != null and aTeaId != ''\">"+ "and a_Tea_Id = #{aTeaId} " + "</if>" + 
		  		  "<if test=\"aStartDate != null and aStartDate != ''\">"+ "and a_Start_Date = #{aStartDate} " + "</if>" + 
		  		  "<if test=\"aEndDate != null and aEndDate != ''\">"+ "and a_End_Date = #{aEndDate} " + "</if>" + 
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
	List<TeaAttendanceVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_tea_attendance " + 
			"<where>" + 
		  		  "<if test=\"aId != null and aId != ''\">"+ "and a_Id = #{aId} " + "</if>" + 
		  		  "<if test=\"aStudentNo != null and aStudentNo != ''\">"+ "and a_Student_No = #{aStudentNo} " + "</if>" + 
		  		  "<if test=\"aTeaId != null and aTeaId != ''\">"+ "and a_Tea_Id = #{aTeaId} " + "</if>" + 
		  		  "<if test=\"aStartDate != null and aStartDate != ''\">"+ "and a_Start_Date = #{aStartDate} " + "</if>" + 
		  		  "<if test=\"aEndDate != null and aEndDate != ''\">"+ "and a_End_Date = #{aEndDate} " + "</if>" + 
		  		  "<if test=\"aAttendanceType != null and aAttendanceType != ''\">"+ "and a_Attendance_Type = #{aAttendanceType} " + "</if>" + 
		  		  "<if test=\"aNote != null and aNote != ''\">"+ "and a_Note = #{aNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_tea_attendance (`a_Id`, `a_Student_No`, `a_Tea_Id`, `a_Start_Date`, `a_End_Date`, `a_Attendance_Type`, `a_Note`)"
	+ "values (#{aId}, #{aStudentNo}, #{aTeaId}, #{aStartDate}, #{aEndDate}, #{aAttendanceType}, #{aNote})")
	int save(TeaAttendanceVO teaAttendance);
	
	@Update("<script>"+ 
			"update s_tea_attendance " + 
					"<set>" + 
		            "<if test=\"aId != null\">`a_Id` = #{aId}, </if>" + 
                    "<if test=\"aStudentNo != null\">`a_Student_No` = #{aStudentNo}, </if>" + 
                    "<if test=\"aTeaId != null\">`a_Tea_Id` = #{aTeaId}, </if>" + 
                    "<if test=\"aStartDate != null\">`a_Start_Date` = #{aStartDate}, </if>" + 
                    "<if test=\"aEndDate != null\">`a_End_Date` = #{aEndDate}, </if>" + 
                    "<if test=\"aAttendanceType != null\">`a_Attendance_Type` = #{aAttendanceType}, </if>" + 
                    "<if test=\"aNote != null\">`a_Note` = #{aNote}, </if>" + 
          					"</set>" + 
					"where a_Id = #{aId}"+
			"</script>")
	int update(TeaAttendanceVO teaAttendance);
	
	@Delete("delete from s_tea_attendance where a_Id =#{aId}")
	int remove(String a_Id);
	
	@Delete("<script>"+ 
			"delete from s_tea_attendance where a_Id in " + 
					"<foreach item=\"aId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{aId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] aIds);
}
