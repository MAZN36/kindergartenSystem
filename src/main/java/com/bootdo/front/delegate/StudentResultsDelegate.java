package com.bootdo.front.delegate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateTimeUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.kinder.entity.CourseVO;
import com.bootdo.kinder.entity.StudentInfoVO;
import com.bootdo.kinder.entity.StudentResultsVO;
import com.bootdo.kinder.service.CourseService;
import com.bootdo.kinder.service.StudentInfoService;
import com.bootdo.kinder.service.StudentResultsService;
import com.bootdo.kinder.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;

@Component
public class StudentResultsDelegate {

    private Logger logger = LoggerFactory.getLogger(AppReqDelegate.class);

    @Autowired
    StudentResultsService studentResultsService;
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private DictService dictService;
    @Autowired
    private CourseService courseService;

    /**
     * 学生成绩信息列表
     * @param jsonObj
     * @return
     */
    public String getStudentResultsList(JSONObject jsonObj){
        logger.info("开始获取学生成绩信息列表...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        Integer pageNo = jsonObj.getInteger("pagNo");
        Integer pageSize = jsonObj.getInteger("pageSize");
        String rClasss = jsonObj.getString("classId");//班级
        String pCourse = jsonObj.getString("pCourse");//课程
        String rStuId = jsonObj.getString("loginId");//学生id
        JSONObject queryJson = new JSONObject();
        queryJson.put("rClasss", rClasss);
        queryJson.put("pCourse", pCourse);
        queryJson.put("rStuId", rStuId);
        Query query = new Query(queryJson,pageNo,pageSize);
        PageUtils studentResultsPage = studentResultsService.findPage(query);
        List<StudentResultsVO> studentResultsList = (List<StudentResultsVO>)studentResultsPage.getRows();
        JSONArray studentResultArray = new JSONArray();
        JSONObject studentResultJson = null;
        if (studentResultsList!=null){
            for (StudentResultsVO studentResults : studentResultsList){
                studentResultJson = new JSONObject();
                studentResultJson.put("rId",studentResults.getRId());
                studentResultJson.put("stuId",studentResults.getRStuId());
                studentResultJson.put("stuNo",studentResults.getRStudentNo());
                studentResultJson.put("stuName","");
                StudentInfoVO studentInfoVO = studentInfoService.get(studentResults.getRStuId());
                if (studentInfoVO!=null){
                    studentResultJson.put("stuName",studentInfoVO.getUser().getName());
                }
                studentResultJson.put("classesId",studentResults.getRClasss());
                String classesName = studentResults.getClassesName();
                studentResultJson.put("classesName",StringUtils.isNotBlank(classesName) ? classesName : "");
                studentResultJson.put("courseId",studentResults.getPCourse());
                String courseName = studentResults.getCourseName();
                studentResultJson.put("courseName",StringUtils.isNotBlank(courseName) ? courseName : "");
                studentResultJson.put("examDate",DateTimeUtils.formatDateTime(studentResults.getRExamDate()));
                studentResultJson.put("score",studentResults.getRScore());
                studentResultJson.put("isPassId",studentResults.getRPass());
                studentResultJson.put("isPass", dictService.getName("is_pass",studentResults.getRPass()));
                studentResultJson.put("remarks",studentResults.getRNote());
                studentResultArray.add(studentResultJson);
            }
        }
        resultJson.putAll(R.appOk());
        resultJson.put("pageNo",pageNo);
        resultJson.put("pageSize",pageSize);
        resultJson.put("pageCount",studentResultsPage.getPageCount(pageSize));
        resultJson.put("studentResultList",studentResultArray);
        logger.info("结束获取学生成绩信息列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 保存/更新学生成绩信息
     * @param jsonObj
     * @return
     */
    public String saveStudentResults(JSONObject jsonObj){
        logger.info("开始保存/更新学生成绩信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        StudentResultsVO studentResults  = new StudentResultsVO();
        studentResults.setRId(jsonObj.getString("rId"));
        studentResults.setRStuId(jsonObj.getString("stuId"));
        studentResults.setRStudentNo( jsonObj.getString("stuNo"));
        studentResults.setRClasss(jsonObj.getString("classesId"));
        studentResults.setPCourse(jsonObj.getString("courseId"));
        studentResults.setRExamDate(DateTimeUtils.parseDate(jsonObj.getString("examDate")));
        studentResults.setRScore(Double.parseDouble(jsonObj.getString("score")));
        studentResults.setRPass(jsonObj.getString("isPassId"));
        studentResults.setRNote(jsonObj.getString("remarks"));
        if (StringUtils.isNotBlank(jsonObj.getString("rId"))){
            //有id则更新
            studentResultsService.update(studentResults);
        }else {
            //无id则增加
            studentResultsService.save(studentResults);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束保存/更新学生成绩信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 删除学生成绩信息
     * @param jsonObj
     * @return
     */
    public String deleteStudentResults(JSONObject jsonObj){
        logger.info("开始删除学生成绩信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(jsonObj.getString("rId"))){
            //有id则更新
            studentResultsService.remove(jsonObj.getString("rId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除学生成绩信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 获取学生成绩列表
     * @param jsonObj
     * @return
     */
    public String getStuResultList(JSONObject jsonObj){
        logger.info("开始获取学生成绩列表...请求参数为："+jsonObj);
        String stuId = jsonObj.getString("stuId");
        String classId = jsonObj.getString("classId");
        JSONObject resultJson = new JSONObject();
        StudentInfoVO studentInfoVO = studentInfoService.get(stuId);
        JSONObject scordList = new JSONObject();
        JSONArray scoreDetail = new JSONArray();
        JSONObject scoreDetailJson = null;
        if (studentInfoVO!=null&&studentInfoVO.getUser()!=null){
            scordList.put("stuId",studentInfoVO.getStuId());
            scordList.put("stuName",studentInfoVO.getUser().getName());
            List<CourseVO> courseList = courseService.list(new HashMap<>());
            for (CourseVO courseVO : courseList){
                scoreDetailJson = new JSONObject();
                JSONObject query = new JSONObject();
                query.put("rStuId",stuId);
                query.put("pCourse",courseVO.getCourseId());
                scoreDetailJson.put("rId","");
                scoreDetailJson.put("courseId",courseVO.getCourseId());
                scoreDetailJson.put("courseName",courseVO.getCourseName());
                scoreDetailJson.put("score","");
                scoreDetailJson.put("isPassId","");
                scoreDetailJson.put("isPassName","");
                List<StudentResultsVO> studentResultsVOList = studentResultsService.list(query);
                if (studentResultsVOList!=null&&studentResultsVOList.size()>0&&studentResultsVOList.get(0)!=null){
                    StudentResultsVO studentResultsVO = studentResultsVOList.get(0);
                    scoreDetailJson.put("rId",studentResultsVO.getRId());
                    scoreDetailJson.put("score",studentResultsVO.getRScore());
                    scoreDetailJson.put("isPassId",studentResultsVO.getRPass());
                    scoreDetailJson.put("isPassName",dictService.getName("is_pass",studentResultsVO.getRPass()));
                }
                scoreDetail.add(scoreDetailJson);
            }
            scordList.put("scoreDetail",scoreDetail);
        }
        resultJson.put("scordList",scordList);
        resultJson.putAll(R.appOk());
        logger.info("结束获取学生成绩列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }
}
