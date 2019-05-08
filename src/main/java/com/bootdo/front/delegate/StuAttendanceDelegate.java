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

import java.util.List;

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
        Integer stuId = jsonObj.getInteger("loginId");
//        String lunchDate = jsonObj.getString("classId");//
        JSONObject queryJson = new JSONObject();
        queryJson.put("aDate", DateTimeUtils.parseDate(aDate));
        queryJson.put("aPeriodId", periodId);
        queryJson.put("aStuId", stuId);
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
}
