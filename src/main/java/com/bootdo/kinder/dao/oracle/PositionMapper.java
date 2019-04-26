package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.PositionVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 教师职位
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface PositionMapper {

	@Select("select `pid`, `pName`, `pNote` from s_position where pid = #{id}")
	PositionVO get(String pid);
	
	@Select("<script>" +
	"select * from s_position " + 
			"<where>" + 
		  		  "<if test=\"pid != null and pid != ''\">"+ "and pid = #{pid} " + "</if>" + 
		  		  "<if test=\"pname != null and pname != ''\">"+ "and pName = #{pname} " + "</if>" + 
		  		  "<if test=\"pnote != null and pnote != ''\">"+ "and pNote = #{pnote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by pid desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<PositionVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_position " + 
			"<where>" + 
		  		  "<if test=\"pid != null and pid != ''\">"+ "and pid = #{pid} " + "</if>" + 
		  		  "<if test=\"pname != null and pname != ''\">"+ "and pName = #{pname} " + "</if>" + 
		  		  "<if test=\"pnote != null and pnote != ''\">"+ "and pNote = #{pnote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_position (`pid`, `pName`, `pNote`)"
	+ "values (#{pid}, #{pname}, #{pnote})")
	int save(PositionVO position);
	
	@Update("<script>"+ 
			"update s_position " + 
					"<set>" + 
		            "<if test=\"pid != null\">`pid` = #{pid}, </if>" + 
                    "<if test=\"pname != null\">`pName` = #{pname}, </if>" + 
                    "<if test=\"pnote != null\">`pNote` = #{pnote}, </if>" + 
          					"</set>" + 
					"where pid = #{pid}"+
			"</script>")
	int update(PositionVO position);
	
	@Delete("delete from s_position where pid =#{pid}")
	int remove(String pid);
	
	@Delete("<script>"+ 
			"delete from s_position where pid in " + 
					"<foreach item=\"pid\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{pid}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] pids);
}
