package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.SchoolLunchVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 午餐表
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface SchoolLunchMapper {

	@Select("select `sl_id`, `sl_name`, `a_Student_No`, `a_Date`, `a_Note` from s_school_lunch where sl_id = #{id}")
	SchoolLunchVO get(String slId);
	
	@Select("<script>" +
	"select * from s_school_lunch " + 
			"<where>" + 
		  		  "<if test=\"slId != null and slId != ''\">"+ "and sl_id = #{slId} " + "</if>" + 
		  		  "<if test=\"slName != null and slName != ''\">"+ "and sl_name = #{slName} " + "</if>" + 
		  		  "<if test=\"aStudentNo != null and aStudentNo != ''\">"+ "and a_Student_No = #{aStudentNo} " + "</if>" + 
		  		  "<if test=\"aDate != null and aDate != ''\">"+ "and a_Date = #{aDate} " + "</if>" + 
		  		  "<if test=\"aNote != null and aNote != ''\">"+ "and a_Note = #{aNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by sl_id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<SchoolLunchVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_school_lunch " + 
			"<where>" + 
		  		  "<if test=\"slId != null and slId != ''\">"+ "and sl_id = #{slId} " + "</if>" + 
		  		  "<if test=\"slName != null and slName != ''\">"+ "and sl_name = #{slName} " + "</if>" + 
		  		  "<if test=\"aStudentNo != null and aStudentNo != ''\">"+ "and a_Student_No = #{aStudentNo} " + "</if>" + 
		  		  "<if test=\"aDate != null and aDate != ''\">"+ "and a_Date = #{aDate} " + "</if>" + 
		  		  "<if test=\"aNote != null and aNote != ''\">"+ "and a_Note = #{aNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_school_lunch (`sl_id`, `sl_name`, `a_Student_No`, `a_Date`, `a_Note`)"
	+ "values (#{slId}, #{slName}, #{aStudentNo}, #{aDate}, #{aNote})")
	int save(SchoolLunchVO schoolLunch);
	
	@Update("<script>"+ 
			"update s_school_lunch " + 
					"<set>" + 
		            "<if test=\"slId != null\">`sl_id` = #{slId}, </if>" + 
                    "<if test=\"slName != null\">`sl_name` = #{slName}, </if>" + 
                    "<if test=\"aStudentNo != null\">`a_Student_No` = #{aStudentNo}, </if>" + 
                    "<if test=\"aDate != null\">`a_Date` = #{aDate}, </if>" + 
                    "<if test=\"aNote != null\">`a_Note` = #{aNote}, </if>" + 
          					"</set>" + 
					"where sl_id = #{slId}"+
			"</script>")
	int update(SchoolLunchVO schoolLunch);
	
	@Delete("delete from s_school_lunch where sl_id =#{slId}")
	int remove(String sl_id);
	
	@Delete("<script>"+ 
			"delete from s_school_lunch where sl_id in " + 
					"<foreach item=\"slId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{slId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] slIds);
}
