package com.bootdo.front.delegate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateTimeUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.kinder.entity.TeaAttendanceVO;
import com.bootdo.kinder.entity.TeacherVO;
import com.bootdo.kinder.service.StudentInfoService;
import com.bootdo.kinder.service.TeaAttendanceService;
import com.bootdo.kinder.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TeaAttendanceDelegate {
    private Logger logger = LoggerFactory.getLogger(AppReqDelegate.class);

    @Autowired
    private TeaAttendanceService teaAttendanceService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DictService dictService;


    /**
     * 老师签到信息列表
     * @param jsonObj
     * @return
     */
    public String getTeaAttendanceList(JSONObject jsonObj){
        logger.info("开始获取老师签到信息列表...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        String aDate = jsonObj.getString("selectDate");//时间
        Integer pageNo = jsonObj.getInteger("pagNo");
        Integer pageSize = jsonObj.getInteger("pageSize");
        JSONObject queryJson = new JSONObject();
        queryJson.put("aStartDate", DateTimeUtils.parseDate(aDate));
        Query query = new Query(queryJson,pageNo,pageSize);
        PageUtils teaAttendancePage = teaAttendanceService.findPage(query);
        List<TeaAttendanceVO> teaAttendanceList = (List<TeaAttendanceVO>)teaAttendancePage.getRows();
        JSONArray teaAttendanceArray = new JSONArray();
        JSONObject teaAttendanceJson = null;
        if (teaAttendanceList!=null){
            for (TeaAttendanceVO teaAttendanceVO : teaAttendanceList){
                teaAttendanceJson = new JSONObject();
                teaAttendanceJson.put("teaAttendanceId",teaAttendanceVO.getAId());
                teaAttendanceJson.put("teaId",teaAttendanceVO.getATeaId());
                teaAttendanceJson.put("teaNo",teaAttendanceVO.getATeaNo());
                TeacherVO teacherVO = teacherService.get(teaAttendanceVO.getATeaId());
                teaAttendanceJson.put("teaName","");
                if (teacherVO!=null){
                    teaAttendanceJson.put("teaName",teacherVO.getUser().getName());
                }
                teaAttendanceJson.put("startDate",DateTimeUtils.formatDateTime(teaAttendanceVO.getAStartDate()));
                teaAttendanceJson.put("endDate",DateTimeUtils.formatDateTime(teaAttendanceVO.getAEndDate()));
                teaAttendanceJson.put("attendanceTypeId",teaAttendanceVO.getAAttendanceType());//签到情况
                teaAttendanceJson.put("attendanceType", dictService.getName("attendance_type",teaAttendanceVO.getAAttendanceType()));//签到情况
                teaAttendanceJson.put("remarks",teaAttendanceVO.getANote());
                teaAttendanceArray.add(teaAttendanceJson);
            }
        }
        resultJson.putAll(R.appOk());
        resultJson.put("pageNo",pageNo);
        resultJson.put("pageSize",pageSize);
        resultJson.put("pageCount",teaAttendancePage.getPageCount(pageSize));
        resultJson.put("teaAttendanceList",teaAttendanceArray);
        logger.info("结束获取老师签到信息列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 保存/更新老师签到信息
     * @param jsonObj
     * @return
     */
    public String saveTeaAttendance(JSONObject jsonObj){
        logger.info("开始保存/更新老师签到信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        TeaAttendanceVO teaAttendanceVO = new TeaAttendanceVO();
        teaAttendanceVO.setAId(jsonObj.getString("teaAttendanceId"));
        teaAttendanceVO.setATeaId(jsonObj.getString("teaId"));
        teaAttendanceVO.setATeaNo( jsonObj.getString("teaNo"));
//        teaAttendanceVO.setATeaName(jsonObj.getString("teaName"));
        teaAttendanceVO.setAStartDate(DateTimeUtils.parseDate(jsonObj.getString("startDate")));
        teaAttendanceVO.setAEndDate(DateTimeUtils.parseDate(jsonObj.getString("endDate")));
        teaAttendanceVO.setAAttendanceType(jsonObj.getString("attendanceType"));
        teaAttendanceVO.setANote(jsonObj.getString("remarks"));
        if (StringUtils.isNotBlank(jsonObj.getString("teaAttendanceId"))){
            //有id则更新
            teaAttendanceService.update(teaAttendanceVO);
        }else {
            //无id则增加
            teaAttendanceService.save(teaAttendanceVO);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束保存/更新老师签到信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 删除老师签到信息
     * @param jsonObj
     * @return
     */
    public String deleteTeaAttendance(JSONObject jsonObj){
        logger.info("开始删除老师签到信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(jsonObj.getString("teaAttendanceId"))){
            //有id则更新
            teaAttendanceService.remove(jsonObj.getString("teaAttendanceId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除老师签到信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /*查询教师今日是否签到：{
        "funCode": "isSign",
                "loginId": "9",
                "roleName": "teacher"
    }
    {
        "resultCode": "0",
            "isSignStart": "Y 已签到 / N 未签到  上班",
            "isSignEnd": "Y 已签到 / N 未签到   下班",
            "resultMessage": "操作成功!"
    }
    眸  11:14:37
    教师签到：入参{
        "funCode": "sign",
                "loginId": "9",
                "roleName": "teacher",
                "signTime": "S 上班/ E 下班"
    }
    {
        "resultCode": "0",
            "resultMessage": "签到成功!"
    }
*/

    /**
     * 查询教师今日是否签到
     * @param jsonObj
     * @return
     */
    public String isTeaSign(JSONObject jsonObj){
        logger.info("开始查询教师今日是否签到...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        String loginId = jsonObj.getString("loginId");
        String date = jsonObj.getString("date");
        JSONObject query = new JSONObject();
        query.put("aTeaId",loginId);
        query.put("aDate",date);
        List<TeaAttendanceVO> list = teaAttendanceService.list(query);
        resultJson.put("isSignStart","N");
        resultJson.put("isSignEnd","N");
        if (list!=null&&list.size()>0&&list.get(0)!=null){
            TeaAttendanceVO teaAttendanceVO = list.get(0);
            if (teaAttendanceVO.getAStartDate()!=null){
                resultJson.put("isSignStart","Y");
            }
            if (teaAttendanceVO.getAEndDate()!=null){
                resultJson.put("isSignEnd","Y");
            }
        }
        resultJson.putAll(R.appOk());
        logger.info("结束查询教师今日是否签到...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /*教师签到：入参{
        "funCode": "sign",
                "loginId": "9",
                "roleName": "teacher",
                "signTime": "S 上班/ E 下班"
    }
    {
        "resultCode": "0",
            "resultMessage": "签到成功!"
    }*/

    /**
     * 教师签到
     * @param jsonObj
     * @return
     */
    public String teaSign(JSONObject jsonObj){
        logger.info("开始教师签到...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        String loginId = jsonObj.getString("userId");
        String signTime = jsonObj.getString("signTime");
        JSONObject query = new JSONObject();
        query.put("aTeaId",loginId);
        query.put("aDate",DateTimeUtils.formatDate(new Date()));
        List<TeaAttendanceVO> list = teaAttendanceService.list(query);
        TeacherVO teacherVO = teacherService.get(loginId);
        if (teacherVO!=null){
            Date now = new Date();
            if (list!=null&&list.size()>0&&list.get(0)!=null){
                //更新之前的
                TeaAttendanceVO teaAttendanceVO = list.get(0);
                if ("S".equals(signTime)){ //上班
                    teaAttendanceVO.setAStartDate(now);
                }else if ("E".equals(signTime)){ //下班
                    teaAttendanceVO.setAEndDate(now);
                }
                teaAttendanceService.update(teaAttendanceVO);
            }else {
                //插入一条新的
                TeaAttendanceVO teaAttendanceVO = new TeaAttendanceVO();
                teaAttendanceVO.setATeaId(loginId);
                teaAttendanceVO.setATeaNo(teacherVO.getTJnumber());
                teaAttendanceVO.setAAttendanceType("Y");
                if ("S".equals(signTime)){ //上班
                    teaAttendanceVO.setAStartDate(now);
                }else if ("E".equals(signTime)){ //下班
                    teaAttendanceVO.setAEndDate(now);
                }
                teaAttendanceService.save(teaAttendanceVO);
            }
            resultJson.putAll(R.appOk("0","签到成功!"));
        }else {
            resultJson.putAll(R.appOk("1","老师信息不存在！"));
        }
        logger.info("结束教师签到...请求参数为："+jsonObj);
        return resultJson.toString();
    }



}
