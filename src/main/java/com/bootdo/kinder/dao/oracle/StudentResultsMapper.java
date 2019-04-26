package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.StudentResultsVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 学生成绩表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface StudentResultsMapper {

	@Select("select `r_Id`, `r_Student_No`, `r_Stu_Id`, `r_Exam_Date`, `p_Course`, `r_Classs`, `r_Score`, `r_Pass`, `r_Note` from s_student_results where r_Id = #{id}")
	StudentResultsVO get(String rId);
	
	@Select("<script>" +
	"select * from s_student_results " + 
			"<where>" + 
		  		  "<if test=\"rId != null and rId != ''\">"+ "and r_Id = #{rId} " + "</if>" + 
		  		  "<if test=\"rStudentNo != null and rStudentNo != ''\">"+ "and r_Student_No = #{rStudentNo} " + "</if>" + 
		  		  "<if test=\"rStuId != null and rStuId != ''\">"+ "and r_Stu_Id = #{rStuId} " + "</if>" + 
		  		  "<if test=\"rExamDate != null and rExamDate != ''\">"+ "and r_Exam_Date = #{rExamDate} " + "</if>" + 
		  		  "<if test=\"pCourse != null and pCourse != ''\">"+ "and p_Course = #{pCourse} " + "</if>" + 
		  		  "<if test=\"rClasss != null and rClasss != ''\">"+ "and r_Classs = #{rClasss} " + "</if>" + 
		  		  "<if test=\"rScore != null and rScore != ''\">"+ "and r_Score = #{rScore} " + "</if>" + 
		  		  "<if test=\"rPass != null and rPass != ''\">"+ "and r_Pass = #{rPass} " + "</if>" + 
		  		  "<if test=\"rNote != null and rNote != ''\">"+ "and r_Note = #{rNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by r_Id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<StudentResultsVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_student_results " + 
			"<where>" + 
		  		  "<if test=\"rId != null and rId != ''\">"+ "and r_Id = #{rId} " + "</if>" + 
		  		  "<if test=\"rStudentNo != null and rStudentNo != ''\">"+ "and r_Student_No = #{rStudentNo} " + "</if>" + 
		  		  "<if test=\"rStuId != null and rStuId != ''\">"+ "and r_Stu_Id = #{rStuId} " + "</if>" + 
		  		  "<if test=\"rExamDate != null and rExamDate != ''\">"+ "and r_Exam_Date = #{rExamDate} " + "</if>" + 
		  		  "<if test=\"pCourse != null and pCourse != ''\">"+ "and p_Course = #{pCourse} " + "</if>" + 
		  		  "<if test=\"rClasss != null and rClasss != ''\">"+ "and r_Classs = #{rClasss} " + "</if>" + 
		  		  "<if test=\"rScore != null and rScore != ''\">"+ "and r_Score = #{rScore} " + "</if>" + 
		  		  "<if test=\"rPass != null and rPass != ''\">"+ "and r_Pass = #{rPass} " + "</if>" + 
		  		  "<if test=\"rNote != null and rNote != ''\">"+ "and r_Note = #{rNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_student_results (`r_Id`, `r_Student_No`, `r_Stu_Id`, `r_Exam_Date`, `p_Course`, `r_Classs`, `r_Score`, `r_Pass`, `r_Note`)"
	+ "values (#{rId}, #{rStudentNo}, #{rStuId}, #{rExamDate}, #{pCourse}, #{rClasss}, #{rScore}, #{rPass}, #{rNote})")
	int save(StudentResultsVO studentResults);
	
	@Update("<script>"+ 
			"update s_student_results " + 
					"<set>" + 
		            "<if test=\"rId != null\">`r_Id` = #{rId}, </if>" + 
                    "<if test=\"rStudentNo != null\">`r_Student_No` = #{rStudentNo}, </if>" + 
                    "<if test=\"rStuId != null\">`r_Stu_Id` = #{rStuId}, </if>" + 
                    "<if test=\"rExamDate != null\">`r_Exam_Date` = #{rExamDate}, </if>" + 
                    "<if test=\"pCourse != null\">`p_Course` = #{pCourse}, </if>" + 
                    "<if test=\"rClasss != null\">`r_Classs` = #{rClasss}, </if>" + 
                    "<if test=\"rScore != null\">`r_Score` = #{rScore}, </if>" + 
                    "<if test=\"rPass != null\">`r_Pass` = #{rPass}, </if>" + 
                    "<if test=\"rNote != null\">`r_Note` = #{rNote}, </if>" + 
          					"</set>" + 
					"where r_Id = #{rId}"+
			"</script>")
	int update(StudentResultsVO studentResults);
	
	@Delete("delete from s_student_results where r_Id =#{rId}")
	int remove(String r_Id);
	
	@Delete("<script>"+ 
			"delete from s_student_results where r_Id in " + 
					"<foreach item=\"rId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{rId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] rIds);
}
