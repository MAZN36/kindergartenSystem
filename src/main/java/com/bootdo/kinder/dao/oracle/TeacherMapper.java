package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.TeacherVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 教师信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface TeacherMapper {

	@Select("select `t_id`, `t_userId`, `t_JNumber`, `t_Position`, `t_Nation`, `t_Political`, `t_card`, `t_Education`, `t_Home`, `t_State`, `t_Note` from s_teacher where t_id = #{id}")
	TeacherVO get(String tId);
	
	@Select("<script>" +
	"select * from s_teacher " + 
			"<where>" + 
		  		  "<if test=\"tId != null and tId != ''\">"+ "and t_id = #{tId} " + "</if>" + 
		  		  "<if test=\"tUserid != null and tUserid != ''\">"+ "and t_userId = #{tUserid} " + "</if>" + 
		  		  "<if test=\"tJnumber != null and tJnumber != ''\">"+ "and t_JNumber = #{tJnumber} " + "</if>" + 
		  		  "<if test=\"tPosition != null and tPosition != ''\">"+ "and t_Position = #{tPosition} " + "</if>" + 
		  		  "<if test=\"tNation != null and tNation != ''\">"+ "and t_Nation = #{tNation} " + "</if>" + 
		  		  "<if test=\"tPolitical != null and tPolitical != ''\">"+ "and t_Political = #{tPolitical} " + "</if>" + 
		  		  "<if test=\"tCard != null and tCard != ''\">"+ "and t_card = #{tCard} " + "</if>" + 
		  		  "<if test=\"tEducation != null and tEducation != ''\">"+ "and t_Education = #{tEducation} " + "</if>" + 
		  		  "<if test=\"tHome != null and tHome != ''\">"+ "and t_Home = #{tHome} " + "</if>" + 
		  		  "<if test=\"tState != null and tState != ''\">"+ "and t_State = #{tState} " + "</if>" + 
		  		  "<if test=\"tNote != null and tNote != ''\">"+ "and t_Note = #{tNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by t_id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<TeacherVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_teacher " + 
			"<where>" + 
		  		  "<if test=\"tId != null and tId != ''\">"+ "and t_id = #{tId} " + "</if>" + 
		  		  "<if test=\"tUserid != null and tUserid != ''\">"+ "and t_userId = #{tUserid} " + "</if>" + 
		  		  "<if test=\"tJnumber != null and tJnumber != ''\">"+ "and t_JNumber = #{tJnumber} " + "</if>" + 
		  		  "<if test=\"tPosition != null and tPosition != ''\">"+ "and t_Position = #{tPosition} " + "</if>" + 
		  		  "<if test=\"tNation != null and tNation != ''\">"+ "and t_Nation = #{tNation} " + "</if>" + 
		  		  "<if test=\"tPolitical != null and tPolitical != ''\">"+ "and t_Political = #{tPolitical} " + "</if>" + 
		  		  "<if test=\"tCard != null and tCard != ''\">"+ "and t_card = #{tCard} " + "</if>" + 
		  		  "<if test=\"tEducation != null and tEducation != ''\">"+ "and t_Education = #{tEducation} " + "</if>" + 
		  		  "<if test=\"tHome != null and tHome != ''\">"+ "and t_Home = #{tHome} " + "</if>" + 
		  		  "<if test=\"tState != null and tState != ''\">"+ "and t_State = #{tState} " + "</if>" + 
		  		  "<if test=\"tNote != null and tNote != ''\">"+ "and t_Note = #{tNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_teacher (`t_id`, `t_userId`, `t_JNumber`, `t_Position`, `t_Nation`, `t_Political`, `t_card`, `t_Education`, `t_Home`, `t_State`, `t_Note`)"
	+ "values (#{tId}, #{tUserid}, #{tJnumber}, #{tPosition}, #{tNation}, #{tPolitical}, #{tCard}, #{tEducation}, #{tHome}, #{tState}, #{tNote})")
	int save(TeacherVO teacher);
	
	@Update("<script>"+ 
			"update s_teacher " + 
					"<set>" + 
		            "<if test=\"tId != null\">`t_id` = #{tId}, </if>" + 
                    "<if test=\"tUserid != null\">`t_userId` = #{tUserid}, </if>" + 
                    "<if test=\"tJnumber != null\">`t_JNumber` = #{tJnumber}, </if>" + 
                    "<if test=\"tPosition != null\">`t_Position` = #{tPosition}, </if>" + 
                    "<if test=\"tNation != null\">`t_Nation` = #{tNation}, </if>" + 
                    "<if test=\"tPolitical != null\">`t_Political` = #{tPolitical}, </if>" + 
                    "<if test=\"tCard != null\">`t_card` = #{tCard}, </if>" + 
                    "<if test=\"tEducation != null\">`t_Education` = #{tEducation}, </if>" + 
                    "<if test=\"tHome != null\">`t_Home` = #{tHome}, </if>" + 
                    "<if test=\"tState != null\">`t_State` = #{tState}, </if>" + 
                    "<if test=\"tNote != null\">`t_Note` = #{tNote}, </if>" + 
          					"</set>" + 
					"where t_id = #{tId}"+
			"</script>")
	int update(TeacherVO teacher);
	
	@Delete("delete from s_teacher where t_id =#{tId}")
	int remove(String t_id);
	
	@Delete("<script>"+ 
			"delete from s_teacher where t_id in " + 
					"<foreach item=\"tId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{tId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] tIds);
}
