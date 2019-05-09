package com.bootdo.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.front.delegate.AppReqDelegate;
import com.bootdo.front.delegate.StuAttendanceDelegate;
import com.bootdo.front.delegate.StudentResultsDelegate;
import com.bootdo.front.delegate.TeaAttendanceDelegate;
import com.bootdo.front.utils.AppParamUtils;
import com.bootdo.common.utils.R;
import com.bootdo.front.utils.EncryptUtil;
import com.bootdo.kinder.service.StudentResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app")
public class AppReqController {

    private Logger logger = LoggerFactory.getLogger(AppReqController.class);

    @Autowired
    private AppReqDelegate reqDelegate;
    @Autowired
    private StuAttendanceDelegate stuAttendanceDelegate;
    @Autowired
    private TeaAttendanceDelegate teaAttendanceDelegate;
    @Autowired
    private StudentResultsDelegate studentResultsDelegate;

    @RequestMapping(value = "getServerReq", method = RequestMethod.POST)
    @ResponseBody
    public String getServerReq(HttpServletRequest request) {
        AppParamUtils appReq = new AppParamUtils();
        String req = appReq.getRequestStr(request);
        JSONObject jsonObj = null;
        JSONObject retWrongObj = null;
        String returnStr = "";
        String funCode = "";// 功能标识
        try {
            jsonObj = new JSONObject();
            jsonObj = JSONObject.parseObject(req);
            if (jsonObj.containsKey("funCode")) {
                funCode = (String) jsonObj.get("funCode");
            } else {
                retWrongObj = new JSONObject();
                retWrongObj.put("resultCode", "1");
                retWrongObj.put("resultMessage", "funCode为空！！");
                return retWrongObj.toJSONString();
            }
            logger.info("手机请求参数funCode=" + funCode + ";jsonObj=" + jsonObj );
            returnStr = this.disPatchFunc(funCode, jsonObj,"app");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("手机请求参数funCode=" + funCode + ";jsonObj=" + jsonObj + "异常，请检查！！");
        }
        logger.info("----req=" + req);
        return returnStr;
    }

    /**
     * 手机请求功能分发
     *
     * @param funCode
     * @param reqStr
     * @param jsonObj
     * @throws Exception
     */
    private String disPatchFunc(String funCode, JSONObject jsonObj,String type) {
        JSONObject retObj = null;
        JSONObject retWrongObj = null;
        String returnStr = "";
        try {
            retObj = new JSONObject();
            retWrongObj = new JSONObject();
            switch (funCode){
                case "login" : //登录
                    returnStr = reqDelegate.login(jsonObj);
                    break;
                case "getTeacherList" : //获取老师列表
                    returnStr = reqDelegate.getTeacherList(jsonObj);
                    break;
                case "getStudentList" : //获取学生列表
                    returnStr = reqDelegate.getStudentList(jsonObj);
                    break;
                case "saveTeacherList" : //保存老师信息
                    returnStr = reqDelegate.saveTeacherList(jsonObj);
                    break;
                case "saveStudentList" : //保存学生信息
                    returnStr = reqDelegate.saveStudentList(jsonObj);
                    break;
                case "deleteStudent" : //删除学生信息
                    returnStr = reqDelegate.deleteStudent(jsonObj);
                    break;
                case "deleteTeacher" : //删除老师信息
                    returnStr = reqDelegate.deleteTeacher(jsonObj);
                    break;
                case "getSchoolLunchList" : //获取午餐列表
                    returnStr = reqDelegate.getSchoolLunchList(jsonObj);
                    break;
                case "saveSchoolLunchList" : //保存/更新午餐
                    returnStr = reqDelegate.saveSchoolLunchList(jsonObj);
                    break;
                case "deleteSchoolLunchList" : //删除午餐信息
                    returnStr = reqDelegate.deleteSchoolLunchList(jsonObj);
                    break;
                case "getPeriodList" : //获取课程列表
                    returnStr = reqDelegate.getPeriodList(jsonObj);
                    break;
                case "savePeriodList" : //保存/更新课程
                    returnStr = reqDelegate.savePeriodList(jsonObj);
                    break;
                case "deletePeriodList" : //删除课程信息
                    returnStr = reqDelegate.deletePeriodList(jsonObj);
                    break;
                case "getStuAttendanceList" : //获取学生签到信息列表
                    returnStr = stuAttendanceDelegate.getStuAttendanceList(jsonObj);
                    break;
                case "saveStuAttendance" : //保存/更新学生签到信息
                    returnStr = stuAttendanceDelegate.saveStuAttendance(jsonObj);
                    break;
                case "deleteStuAttendance" : //删除学生签到信息
                    returnStr = stuAttendanceDelegate.deleteStuAttendance(jsonObj);
                    break;
                case "getTeaAttendanceList" : //获取老师签到信息列表
                    returnStr = teaAttendanceDelegate.getTeaAttendanceList(jsonObj);
                    break;
                case "saveTeaAttendance" : //保存/更新老师签到信息
                    returnStr = teaAttendanceDelegate.saveTeaAttendance(jsonObj);
                    break;
                case "deleteTeaAttendance" : //删除老师签到信息
                    returnStr = teaAttendanceDelegate.deleteTeaAttendance(jsonObj);
                    break;
                case "getCourseList" : //获取全部课程列表
                    returnStr = reqDelegate.getCourseList(jsonObj);
                    break;
                case "getStudentResultsList" : //学生成绩信息列表
                    returnStr = studentResultsDelegate.getStudentResultsList(jsonObj);
                    break;
                case "saveStudentResults" : //保存/更新老师签到信息
                    returnStr = studentResultsDelegate.saveStudentResults(jsonObj);
                    break;
                case "deleteStudentResults" : //删除学生成绩信息
                    returnStr = studentResultsDelegate.deleteStudentResults(jsonObj);
                    break;
                case "resetPassword" : //修改密码
                    returnStr = reqDelegate.resetPassword(jsonObj);
                    break;
                case "isSign" : //查询教师今日是否签到
                    returnStr = teaAttendanceDelegate.isTeaSign(jsonObj);
                    break;
                case "sign" : //教师签到
                    returnStr = teaAttendanceDelegate.teaSign(jsonObj);
                    break;
                case "getAllPeriod" : //获取全部课表安排
                    returnStr = reqDelegate.getAllPeriod(jsonObj);
                    break;
                case "getStuResultList" : //获取学生成绩列表
                    returnStr = studentResultsDelegate.getStuResultList(jsonObj);
                    break;
                case "studentSign" : //老师保存学生点名信息
                    returnStr = stuAttendanceDelegate.studentSign(jsonObj);
                    break;
                case "queryIsCallList" : //老师保存学生点名信息
                    returnStr = stuAttendanceDelegate.queryIsCallList(jsonObj);
                    break;
                case "saveMessage" : //保存老师和家长的消息
                    returnStr = reqDelegate.saveMessage(jsonObj);
                    break;
                case "getMessageList" : //获取所有消息列表
                    returnStr = reqDelegate.getMessageList(jsonObj);
                    break;
                default:
                    returnStr = "{\"resultCode\":\"1\",\"resultMessage\":\"未找到对应key值\"}";
                    break;
            }
            logger.info("返回app的出参是returnStr=" + returnStr);
            returnStr = EncryptUtil.encryptThreeDESECB(returnStr);
            logger.info("加密出参是returnStr=" + returnStr);
        } catch (Exception e) {
            e.printStackTrace();
            retWrongObj.put("resultCode", "1");
            retWrongObj.put("resultMessage", "系统异常！！");
            return retObj.toJSONString();
        }
        return returnStr;
    }
}
