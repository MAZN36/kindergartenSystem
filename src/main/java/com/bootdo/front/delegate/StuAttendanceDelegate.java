package com.bootdo.front.delegate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateTimeUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.kinder.entity.StuAttendanceVO;
import com.bootdo.kinder.entity.StudentInfoVO;
import com.bootdo.kinder.entity.TeacherVO;
import com.bootdo.kinder.service.StuAttendanceService;
import com.bootdo.kinder.service.StudentInfoService;
import com.bootdo.kinder.service.TeacherService;
import com.bootdo.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生签到信息
 */
@Component
public class StuAttendanceDelegate {
    private Logger logger = LoggerFactory.getLogger(AppReqDelegate.class);

    @Autowired
    StuAttendanceService stuAttendanceService;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DictService dictService;

    /**
     * 学生签到信息列表
     * @param jsonObj
     * @return
     */
    public String getStuAttendanceList(JSONObject jsonObj){
        logger.info("开始获取学生签到信息列表...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        String aDate = jsonObj.getString("periodDate");//时间
        String periodId = jsonObj.getString("periodId");//课程表id
        Integer pageNo = jsonObj.getInteger("pagNo");
        Integer pageSize = jsonObj.getInteger("pageSize");
        Object stuId = jsonObj.get("userId");//学生id
        String roleName = jsonObj.getString("roleName");//用户标识
//        String lunchDate = jsonObj.getString("classId");//
        JSONObject queryJson = new JSONObject();
        queryJson.put("aDate", DateTimeUtils.parseDate(aDate));
        queryJson.put("aPeriodId", periodId);
        if ("parent".equals(roleName)){
            queryJson.put("aStuId", stuId);
        }
        Query query = new Query(queryJson,pageNo,pageSize);
        PageUtils stuAttendancePage = stuAttendanceService.findPage(query);
        List<StuAttendanceVO> stuAttendanceList = (List<StuAttendanceVO>)stuAttendancePage.getRows();
        JSONArray stuAttendanceArray = new JSONArray();
        JSONObject stuAttendanceJson = null;
        if (stuAttendanceList!=null){
            for (StuAttendanceVO stuAttendanceVO : stuAttendanceList){
                stuAttendanceJson = new JSONObject();
                stuAttendanceJson.put("stuAttendanceId",stuAttendanceVO.getAId());
                stuAttendanceJson.put("classesId",stuAttendanceVO.getAClasss());
                stuAttendanceJson.put("classesName", stuAttendanceVO.getClassesName());
                stuAttendanceJson.put("periodId",stuAttendanceVO.getAPeriodId());
                stuAttendanceJson.put("classesDate",DateTimeUtils.formatDateTime(stuAttendanceVO.getADate()));
                stuAttendanceJson.put("teacherId",stuAttendanceVO.getAOperator());
                stuAttendanceJson.put("teacherName","");
                TeacherVO teacherVO = teacherService.get(stuAttendanceVO.getAOperator());
                if (teacherVO!=null){
                    stuAttendanceJson.put("teacherName",teacherVO.getUser().getName());
                }
                stuAttendanceJson.put("stuNo",stuAttendanceVO.getAStudentNo());
                stuAttendanceJson.put("stuId",stuAttendanceVO.getAStuId());
                StudentInfoVO studentInfoVO = studentInfoService.get(stuAttendanceVO.getAStuId());
                stuAttendanceJson.put("stuName","");
                if (studentInfoVO!=null){
                    stuAttendanceJson.put("stuName",studentInfoVO.getUser().getName());
                }
                stuAttendanceJson.put("courseName",stuAttendanceVO.getCourseName());
                stuAttendanceJson.put("attendanceTypeId",stuAttendanceVO.getAAttendanceType());//签到情况
                stuAttendanceJson.put("attendanceType", dictService.getName("attendance_type",stuAttendanceVO.getAAttendanceType()));//签到情况
                stuAttendanceJson.put("remarks",stuAttendanceVO.getANote());
                stuAttendanceArray.add(stuAttendanceJson);
            }
        }
        resultJson.putAll(R.appOk());
        resultJson.put("pageNo",pageNo);
        resultJson.put("pageSize",pageSize);
        resultJson.put("pageCount",stuAttendancePage.getPageCount(pageSize));
        resultJson.put("stuAttendanceList",stuAttendanceArray);
        logger.info("结束获取学生签到信息列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 保存/更新学生签到信息
     * @param jsonObj
     * @return
     */
    public String saveStuAttendance(JSONObject jsonObj){
        logger.info("开始保存/更新学生签到信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        StuAttendanceVO stuAttendanceVO = new StuAttendanceVO();
        stuAttendanceVO.setAId(jsonObj.getString("stuAttendanceId"));
        stuAttendanceVO.setAClasss(jsonObj.getString("classesId"));
        stuAttendanceVO.setAPeriodId( jsonObj.getString("periodId"));
        stuAttendanceVO.setADate(DateTimeUtils.parseDate(jsonObj.getString("classesDate")));
        stuAttendanceVO.setAOperator(jsonObj.getString("teacherId"));
        stuAttendanceVO.setAStuId(jsonObj.getString("stuId"));
        stuAttendanceVO.setACourseName(jsonObj.getString("courseName"));
        stuAttendanceVO.setAAttendanceType(jsonObj.getString("attendanceType"));
        stuAttendanceVO.setANote(jsonObj.getString("remarks"));
        if (StringUtils.isNotBlank(jsonObj.getString("stuAttendanceId"))){
            //有id则更新
            stuAttendanceService.update(stuAttendanceVO);
        }else {
            //无id则增加
            stuAttendanceService.save(stuAttendanceVO);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束保存/更新学生签到信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 删除学生签到信息
     * @param jsonObj
     * @return
     */
    public String deleteStuAttendance(JSONObject jsonObj){
        logger.info("开始删除学生签到信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(jsonObj.getString("stuAttendanceId"))){
            //有id则更新
            stuAttendanceService.remove(jsonObj.getString("stuAttendanceId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除学生签到信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 获取当前课程学生的点名情况
     * @param jsonObj
     * @return
     */
    public String queryIsCallList(JSONObject jsonObj){
        logger.info("开始获取当前课程学生的点名情况...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        JSONObject query = new JSONObject();
        query.put("aClasss",jsonObj.getString("classId"));
        query.put("aDate",jsonObj.getString("date"));
        query.put("aCourseName",jsonObj.getString("courseId"));
        query.put("aPeriodId",jsonObj.getString("pId"));
        query.put("aOperator",jsonObj.getString("loginId"));
        List<StuAttendanceVO> stuAttendanceVOList = stuAttendanceService.list(query);
        Map<String, StuAttendanceVO> stuAttendanceMap = new HashMap<String, StuAttendanceVO>();
        if (stuAttendanceVOList!=null){
            for (StuAttendanceVO stuAttendanceVO : stuAttendanceVOList){
                stuAttendanceMap.put(stuAttendanceVO.getAStuId(), stuAttendanceVO);
            }
        }
        JSONObject queryStudent = new JSONObject();
        query.put("status","1");
        query.put("sClasss",jsonObj.getString("classId"));
        List<StudentInfoVO> studentInfoVOList = studentInfoService.list(queryStudent);
        JSONArray studentList = new JSONArray();
        JSONObject studentJson = null;
        if (studentInfoVOList!=null){
            for (StudentInfoVO studentInfoVO : studentInfoVOList){
                studentJson = new JSONObject();
                studentJson.put("stuId",studentInfoVO.getStuId());
                studentJson.put("stuNo",studentInfoVO.getSTudentid());
                studentJson.put("stuName",studentInfoVO.getUser().getName());
                studentJson.put("classId",jsonObj.getString("classId"));
                studentJson.put("className",jsonObj.getString("className"));
                studentJson.put("courseId",jsonObj.getString("courseId"));
                studentJson.put("courseName",jsonObj.getString("courseName"));
                studentJson.put("pId",jsonObj.getString("pId"));
                if (stuAttendanceMap.containsKey(studentInfoVO.getStuId())&&stuAttendanceMap.get(studentInfoVO.getStuId())!=null){
                    //学生已经签到
                    StuAttendanceVO stuAttendanceVO = stuAttendanceMap.get(studentInfoVO.getStuId());
                    studentJson.put("isCall","Y");
                    studentJson.put("isSign",stuAttendanceVO.getAAttendanceType());
                }else {
                    studentJson.put("isCall","N");
                    studentJson.put("isSign","");
                }
                studentList.add(studentJson);
            }
        }
        resultJson.put("studentList",studentList);
        resultJson.putAll(R.appOk());
        logger.info("结束获取当前课程学生的点名情况...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 老师保存学生点名信息
     * @param jsonObj
     * @return
     */
    public String studentSign(JSONObject jsonObj){
        logger.info("开始老师保存/更新学生点名信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        StuAttendanceVO stuAttendanceVO = new StuAttendanceVO();
        stuAttendanceVO.setAClasss(jsonObj.getString("classId"));
        stuAttendanceVO.setAPeriodId( jsonObj.getString("pId"));
        stuAttendanceVO.setADate(DateTimeUtils.parseDate(jsonObj.getString("date")));
        stuAttendanceVO.setAOperator(jsonObj.getString("loginId"));
        stuAttendanceVO.setAStuId(jsonObj.getString("stuId"));
        stuAttendanceVO.setACourseName(jsonObj.getString("courseId"));
        stuAttendanceVO.setAAttendanceType(jsonObj.getString("isSign"));
        StudentInfoVO studentInfoVO = studentInfoService.get(jsonObj.getString("stuId"));
        stuAttendanceVO.setAStudentNo(jsonObj.getString("stuNo"));
        if (studentInfoVO==null||studentInfoVO.getUser()==null){
            resultJson.putAll(R.appOk("1","该学生信息不存在，请联系管理员！"));
        }else {
            //无id则增加
            stuAttendanceService.save(stuAttendanceVO);
            resultJson.putAll(R.appOk("0",studentInfoVO.getUser().getName()+"点名成功"));
        }
        logger.info("结束老师保存/更新学生点名信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }
}
