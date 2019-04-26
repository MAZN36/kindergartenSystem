package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.CourseVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 课程
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface CourseMapper {

	@Select("select `course_id`, `course_name`, `course_sum`, `course_duration`, `course_note` from s_course where course_id = #{id}")
	CourseVO get(String courseId);
	
	@Select("<script>" +
	"select * from s_course " + 
			"<where>" + 
		  		  "<if test=\"courseId != null and courseId != ''\">"+ "and course_id = #{courseId} " + "</if>" + 
		  		  "<if test=\"courseName != null and courseName != ''\">"+ "and course_name = #{courseName} " + "</if>" + 
		  		  "<if test=\"courseSum != null and courseSum != ''\">"+ "and course_sum = #{courseSum} " + "</if>" + 
		  		  "<if test=\"courseDuration != null and courseDuration != ''\">"+ "and course_duration = #{courseDuration} " + "</if>" + 
		  		  "<if test=\"courseNote != null and courseNote != ''\">"+ "and course_note = #{courseNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by course_id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<CourseVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_course " + 
			"<where>" + 
		  		  "<if test=\"courseId != null and courseId != ''\">"+ "and course_id = #{courseId} " + "</if>" + 
		  		  "<if test=\"courseName != null and courseName != ''\">"+ "and course_name = #{courseName} " + "</if>" + 
		  		  "<if test=\"courseSum != null and courseSum != ''\">"+ "and course_sum = #{courseSum} " + "</if>" + 
		  		  "<if test=\"courseDuration != null and courseDuration != ''\">"+ "and course_duration = #{courseDuration} " + "</if>" + 
		  		  "<if test=\"courseNote != null and courseNote != ''\">"+ "and course_note = #{courseNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_course (`course_id`, `course_name`, `course_sum`, `course_duration`, `course_note`)"
	+ "values (#{courseId}, #{courseName}, #{courseSum}, #{courseDuration}, #{courseNote})")
	int save(CourseVO course);
	
	@Update("<script>"+ 
			"update s_course " + 
					"<set>" + 
		            "<if test=\"courseId != null\">`course_id` = #{courseId}, </if>" + 
                    "<if test=\"courseName != null\">`course_name` = #{courseName}, </if>" + 
                    "<if test=\"courseSum != null\">`course_sum` = #{courseSum}, </if>" + 
                    "<if test=\"courseDuration != null\">`course_duration` = #{courseDuration}, </if>" + 
                    "<if test=\"courseNote != null\">`course_note` = #{courseNote}, </if>" + 
          					"</set>" + 
					"where course_id = #{courseId}"+
			"</script>")
	int update(CourseVO course);
	
	@Delete("delete from s_course where course_id =#{courseId}")
	int remove(String course_id);
	
	@Delete("<script>"+ 
			"delete from s_course where course_id in " + 
					"<foreach item=\"courseId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{courseId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] courseIds);
}
