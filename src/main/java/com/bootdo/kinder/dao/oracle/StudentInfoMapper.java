package com.bootdo.kinder.dao.oracle;

import com.bootdo.kinder.entity.StudentInfoVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 家长+学生信息
 * 
 * @author mazhen
 * @email mazhen@datang.com
 * @date 2019-04-26 11:34:18
 */
@Mapper
public interface StudentInfoMapper {

	@Select("select `stu_Id`, `s_userId`, `s_tudentid`, `s_classs`, `s_name`, `s_sex`, `s_national`, `s_date`, `s_native_place`, `s_address`, `s_parents_name`, `s_parents_number`, `s_number`, `s_entrance_date`, `s_qq`, `s_state`, `s_note` from s_student_info where stu_Id = #{id}")
	StudentInfoVO get(String stuId);
	
	@Select("<script>" +
	"select * from s_student_info " + 
			"<where>" + 
		  		  "<if test=\"stuId != null and stuId != ''\">"+ "and stu_Id = #{stuId} " + "</if>" + 
		  		  "<if test=\"sUserid != null and sUserid != ''\">"+ "and s_userId = #{sUserid} " + "</if>" + 
		  		  "<if test=\"sTudentid != null and sTudentid != ''\">"+ "and s_tudentid = #{sTudentid} " + "</if>" + 
		  		  "<if test=\"sClasss != null and sClasss != ''\">"+ "and s_classs = #{sClasss} " + "</if>" + 
		  		  "<if test=\"sName != null and sName != ''\">"+ "and s_name = #{sName} " + "</if>" + 
		  		  "<if test=\"sSex != null and sSex != ''\">"+ "and s_sex = #{sSex} " + "</if>" + 
		  		  "<if test=\"sNational != null and sNational != ''\">"+ "and s_national = #{sNational} " + "</if>" + 
		  		  "<if test=\"sDate != null and sDate != ''\">"+ "and s_date = #{sDate} " + "</if>" + 
		  		  "<if test=\"sNativePlace != null and sNativePlace != ''\">"+ "and s_native_place = #{sNativePlace} " + "</if>" + 
		  		  "<if test=\"sAddress != null and sAddress != ''\">"+ "and s_address = #{sAddress} " + "</if>" + 
		  		  "<if test=\"sParentsName != null and sParentsName != ''\">"+ "and s_parents_name = #{sParentsName} " + "</if>" + 
		  		  "<if test=\"sParentsNumber != null and sParentsNumber != ''\">"+ "and s_parents_number = #{sParentsNumber} " + "</if>" + 
		  		  "<if test=\"sNumber != null and sNumber != ''\">"+ "and s_number = #{sNumber} " + "</if>" + 
		  		  "<if test=\"sEntranceDate != null and sEntranceDate != ''\">"+ "and s_entrance_date = #{sEntranceDate} " + "</if>" + 
		  		  "<if test=\"sQq != null and sQq != ''\">"+ "and s_qq = #{sQq} " + "</if>" + 
		  		  "<if test=\"sState != null and sState != ''\">"+ "and s_state = #{sState} " + "</if>" + 
		  		  "<if test=\"sNote != null and sNote != ''\">"+ "and s_note = #{sNote} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by stu_Id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<StudentInfoVO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from s_student_info " + 
			"<where>" + 
		  		  "<if test=\"stuId != null and stuId != ''\">"+ "and stu_Id = #{stuId} " + "</if>" + 
		  		  "<if test=\"sUserid != null and sUserid != ''\">"+ "and s_userId = #{sUserid} " + "</if>" + 
		  		  "<if test=\"sTudentid != null and sTudentid != ''\">"+ "and s_tudentid = #{sTudentid} " + "</if>" + 
		  		  "<if test=\"sClasss != null and sClasss != ''\">"+ "and s_classs = #{sClasss} " + "</if>" + 
		  		  "<if test=\"sName != null and sName != ''\">"+ "and s_name = #{sName} " + "</if>" + 
		  		  "<if test=\"sSex != null and sSex != ''\">"+ "and s_sex = #{sSex} " + "</if>" + 
		  		  "<if test=\"sNational != null and sNational != ''\">"+ "and s_national = #{sNational} " + "</if>" + 
		  		  "<if test=\"sDate != null and sDate != ''\">"+ "and s_date = #{sDate} " + "</if>" + 
		  		  "<if test=\"sNativePlace != null and sNativePlace != ''\">"+ "and s_native_place = #{sNativePlace} " + "</if>" + 
		  		  "<if test=\"sAddress != null and sAddress != ''\">"+ "and s_address = #{sAddress} " + "</if>" + 
		  		  "<if test=\"sParentsName != null and sParentsName != ''\">"+ "and s_parents_name = #{sParentsName} " + "</if>" + 
		  		  "<if test=\"sParentsNumber != null and sParentsNumber != ''\">"+ "and s_parents_number = #{sParentsNumber} " + "</if>" + 
		  		  "<if test=\"sNumber != null and sNumber != ''\">"+ "and s_number = #{sNumber} " + "</if>" + 
		  		  "<if test=\"sEntranceDate != null and sEntranceDate != ''\">"+ "and s_entrance_date = #{sEntranceDate} " + "</if>" + 
		  		  "<if test=\"sQq != null and sQq != ''\">"+ "and s_qq = #{sQq} " + "</if>" + 
		  		  "<if test=\"sState != null and sState != ''\">"+ "and s_state = #{sState} " + "</if>" + 
		  		  "<if test=\"sNote != null and sNote != ''\">"+ "and s_note = #{sNote} " + "</if>" + 
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into s_student_info (`stu_Id`, `s_userId`, `s_tudentid`, `s_classs`, `s_name`, `s_sex`, `s_national`, `s_date`, `s_native_place`, `s_address`, `s_parents_name`, `s_parents_number`, `s_number`, `s_entrance_date`, `s_qq`, `s_state`, `s_note`)"
	+ "values (#{stuId}, #{sUserid}, #{sTudentid}, #{sClasss}, #{sName}, #{sSex}, #{sNational}, #{sDate}, #{sNativePlace}, #{sAddress}, #{sParentsName}, #{sParentsNumber}, #{sNumber}, #{sEntranceDate}, #{sQq}, #{sState}, #{sNote})")
	int save(StudentInfoVO studentInfo);
	
	@Update("<script>"+ 
			"update s_student_info " + 
					"<set>" + 
		            "<if test=\"stuId != null\">`stu_Id` = #{stuId}, </if>" + 
                    "<if test=\"sUserid != null\">`s_userId` = #{sUserid}, </if>" + 
                    "<if test=\"sTudentid != null\">`s_tudentid` = #{sTudentid}, </if>" + 
                    "<if test=\"sClasss != null\">`s_classs` = #{sClasss}, </if>" + 
                    "<if test=\"sName != null\">`s_name` = #{sName}, </if>" + 
                    "<if test=\"sSex != null\">`s_sex` = #{sSex}, </if>" + 
                    "<if test=\"sNational != null\">`s_national` = #{sNational}, </if>" + 
                    "<if test=\"sDate != null\">`s_date` = #{sDate}, </if>" + 
                    "<if test=\"sNativePlace != null\">`s_native_place` = #{sNativePlace}, </if>" + 
                    "<if test=\"sAddress != null\">`s_address` = #{sAddress}, </if>" + 
                    "<if test=\"sParentsName != null\">`s_parents_name` = #{sParentsName}, </if>" + 
                    "<if test=\"sParentsNumber != null\">`s_parents_number` = #{sParentsNumber}, </if>" + 
                    "<if test=\"sNumber != null\">`s_number` = #{sNumber}, </if>" + 
                    "<if test=\"sEntranceDate != null\">`s_entrance_date` = #{sEntranceDate}, </if>" + 
                    "<if test=\"sQq != null\">`s_qq` = #{sQq}, </if>" + 
                    "<if test=\"sState != null\">`s_state` = #{sState}, </if>" + 
                    "<if test=\"sNote != null\">`s_note` = #{sNote}, </if>" + 
          					"</set>" + 
					"where stu_Id = #{stuId}"+
			"</script>")
	int update(StudentInfoVO studentInfo);
	
	@Delete("delete from s_student_info where stu_Id =#{stuId}")
	int remove(String stu_Id);
	
	@Delete("<script>"+ 
			"delete from s_student_info where stu_Id in " + 
					"<foreach item=\"stuId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{stuId}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(String[] stuIds);
}
