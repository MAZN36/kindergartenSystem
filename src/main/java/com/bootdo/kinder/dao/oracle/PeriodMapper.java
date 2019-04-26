package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.PeriodVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 课时表,课程安排
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface PeriodMapper {

	@Select("select `p_Id`, `p_Classs`, `p_Course`, `p_StartDate`, `p_End_Date`, `p_Note` from s_period where p_Id = #{id}")
	PeriodVO get(String pId);
	
	@Select("<script>" +
	"select * from s_period " + 
			"<where>" + 
		  		  "<if test=\"pId != null and pId != ''\">"+ "and p_Id = #{pId} " + "</if>" + 
		  		  "<if test=\"pClasss != null and pClasss != ''\">"+ "and p_Classs = #{pClasss} " + "</if>" + 
		  		  "<if test=\"pCourse != null and pCourse != ''\">"+ "and p_Course = #{pCourse} " + "</if>" + 
		  		  "<if test=\"pStartdate != null and pStartdate != ''\">"+ "and p_StartDate = #{pStartdate} " + "</if>" + 
		  		  "<if test=\"pEndDate != null and pEndDate != ''\">"+ "and p_End_Date = #{pEndDate} " + "</if>" + 
		  		  "<if test=\"pNote != null and pNote != ''\">"+ "and p_Note = #{pNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by p_Id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<PeriodVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_period " + 
			"<where>" + 
		  		  "<if test=\"pId != null and pId != ''\">"+ "and p_Id = #{pId} " + "</if>" + 
		  		  "<if test=\"pClasss != null and pClasss != ''\">"+ "and p_Classs = #{pClasss} " + "</if>" + 
		  		  "<if test=\"pCourse != null and pCourse != ''\">"+ "and p_Course = #{pCourse} " + "</if>" + 
		  		  "<if test=\"pStartdate != null and pStartdate != ''\">"+ "and p_StartDate = #{pStartdate} " + "</if>" + 
		  		  "<if test=\"pEndDate != null and pEndDate != ''\">"+ "and p_End_Date = #{pEndDate} " + "</if>" + 
		  		  "<if test=\"pNote != null and pNote != ''\">"+ "and p_Note = #{pNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_period (`p_Id`, `p_Classs`, `p_Course`, `p_StartDate`, `p_End_Date`, `p_Note`)"
	+ "values (#{pId}, #{pClasss}, #{pCourse}, #{pStartdate}, #{pEndDate}, #{pNote})")
	int save(PeriodVO period);
	
	@Update("<script>"+ 
			"update s_period " + 
					"<set>" + 
		            "<if test=\"pId != null\">`p_Id` = #{pId}, </if>" + 
                    "<if test=\"pClasss != null\">`p_Classs` = #{pClasss}, </if>" + 
                    "<if test=\"pCourse != null\">`p_Course` = #{pCourse}, </if>" + 
                    "<if test=\"pStartdate != null\">`p_StartDate` = #{pStartdate}, </if>" + 
                    "<if test=\"pEndDate != null\">`p_End_Date` = #{pEndDate}, </if>" + 
                    "<if test=\"pNote != null\">`p_Note` = #{pNote}, </if>" + 
          					"</set>" + 
					"where p_Id = #{pId}"+
			"</script>")
	int update(PeriodVO period);
	
	@Delete("delete from s_period where p_Id =#{pId}")
	int remove(String p_Id);
	
	@Delete("<script>"+ 
			"delete from s_period where p_Id in " + 
					"<foreach item=\"pId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{pId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] pIds);
}
