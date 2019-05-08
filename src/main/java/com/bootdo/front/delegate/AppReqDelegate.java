package com.bootdo.front.delegate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateTimeUtils;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.kinder.entity.CourseVO;
import com.bootdo.kinder.entity.PeriodVO;
import com.bootdo.kinder.entity.SchoolLunchVO;
import com.bootdo.kinder.entity.StudentInfoVO;
import com.bootdo.kinder.entity.TeacherVO;
import com.bootdo.kinder.service.CourseService;
import com.bootdo.kinder.service.PeriodService;
import com.bootdo.kinder.service.SchoolLunchService;
import com.bootdo.kinder.service.StudentInfoService;
import com.bootdo.kinder.service.TeacherService;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.UserService;
import com.bootdo.system.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppReqDelegate {

    private Logger logger = LoggerFactory.getLogger(AppReqDelegate.class);


    @Autowired
    private UserService userService;

    @Autowired
    MenuService menuService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private DictService dictService;

    @Autowired
    private PeriodService periodService;
    @Autowired
    private SchoolLunchService schoolLunchService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private RoleService roleService;

    /**
     * 登录系统
     * @param username
     * @param password
     * @return
     */
    public String login(JSONObject jsonObj){
        logger.info("手机端开始登录...请求参数为："+jsonObj);
        String username = jsonObj.getString("userName");
        String password = jsonObj.getString("password");
        JSONObject resultJson = new JSONObject();
        try{
            UserDO userDo = userService.login(username, password);
            List<Tree<MenuDO>> menus = menuService.listMenuTree(userDo.getUserId());
            JSONArray menuArray = new JSONArray();
            resultJson.put("menus",menuArray);
            if (menus!=null&&menus.size()>0){
                List<Tree<MenuDO>> children = menus.get(0).getChildren();
                JSONObject menuJson = null;
                for (Tree<MenuDO> tree : children){
                    menuJson = new JSONObject();
                    menuJson.put("menuId",tree.getId());
                    menuJson.put("menuName",tree.getText());
                    menuArray.add(menuJson);
                }
                resultJson.put("menus",menuArray);
            }
            String roleId = "";
            String roleName = "";
            List<Long> roleIds = userDo.getRoleIds();
            if (roleIds!=null&&roleIds.size()>0){
                roleId = roleIds.get(0)+"";
                RoleDO roleDO = roleService.get(roleIds.get(0));
                roleName = roleDO==null ? "" : roleDO.getRoleSign();
            }
            String loginId = userDo.getUserId()+"";
            if ("teacher".equals(roleName)){
                TeacherVO teacherVO = teacherService.get(loginId);
                if (teacherVO!=null) {
                    loginId = teacherVO.getTId();
                }
            }else if ("parent".equals(roleName)){
                StudentInfoVO studentInfoVO = studentInfoService.get(loginId);
                if (studentInfoVO!=null){
                    loginId = studentInfoVO.getStuId();
                }
            }

            resultJson.putAll(R.appOk());
            resultJson.put("name",userDo.getName());
            resultJson.put("roleName",roleName);
            resultJson.put("userId",userDo.getUserId()+"");
            resultJson.put("loginId",loginId);
            resultJson.put("deptName",userDo.getDeptName());
            resultJson.put("deptId",userDo.getDeptId());
            resultJson.put("roleId",roleId);
        }catch (Exception e){
            resultJson.putAll(R.appOk("1",e.getMessage()));
        }

        logger.info("手机端结束登录...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 获取老师列表
     * @param jsonObj
     * @return
     */
    public String getTeacherList(JSONObject jsonObj){
        logger.info("开始获取老师列表...请求参数为："+jsonObj);
        Integer pageNo = jsonObj.getInteger("pagNo");
        Integer pageSize = jsonObj.getInteger("pageSize");
        Integer deptId = jsonObj.getInteger("classId");
        JSONObject resultJson = new JSONObject();
        JSONObject queryJson = new JSONObject();
        queryJson.put("state","1");
        queryJson.put("deptId",deptId);
        Query query = new Query(queryJson,pageNo,pageSize);
        PageUtils page = teacherService.findPage(query);
        List<TeacherVO> teacherList = (List<TeacherVO>) page.getRows();
        JSONArray teacherArray = new JSONArray();
        JSONObject teacherJson = null;
        for (TeacherVO teacherVO : teacherList){
            teacherJson = teacherToJson(teacherVO);
            teacherArray.add(teacherJson);
        }
        resultJson.putAll(R.appOk());
        resultJson.put("teacherList",teacherArray);
        resultJson.put("pageNo",pageNo);
        resultJson.put("pageSize",pageSize);
        resultJson.put("pageCount",page.getPageCount(pageSize));
        logger.info("结束获取老师列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 获取学生列表
     * @param jsonObj
     * @return
     */
    public String getStudentList(JSONObject jsonObj){
        logger.info("开始获取学生列表...请求参数为："+jsonObj);
        Integer pageNo = jsonObj.getInteger("pagNo");
        Integer pageSize = jsonObj.getInteger("pageSize");
        Integer classId = jsonObj.getInteger("classId");
        JSONObject resultJson = new JSONObject();
        JSONObject queryJson = new JSONObject();
        queryJson.put("state","1");
        queryJson.put("sClasss",classId);
        Query query = new Query(queryJson,pageNo,pageSize);
        PageUtils studentPage = studentInfoService.findPage(query);
        List<StudentInfoVO> studentInfoList = (List<StudentInfoVO>) studentPage.getRows();
        JSONArray studentArray = new JSONArray();
        JSONObject studentJson = null;
        for (StudentInfoVO studentInfoVO : studentInfoList){
            studentJson = stuToJson(studentInfoVO);
            studentArray.add(studentJson);
        }
        resultJson.putAll(R.appOk());
        resultJson.put("studentList",studentArray);
        resultJson.put("pageNo",pageNo);
        resultJson.put("pageSize",pageSize);
        resultJson.put("pageCount",studentPage.getPageCount(pageSize));
        logger.info("开始获取学生列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 保存/修改老师信息
     * @param jsonObj
     * @return
     */
    public String saveTeacherList(JSONObject jsonObj){
        logger.info("开始保存/修改老师信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        TeacherVO teacherVO = jsonToTeacher(jsonObj);
        resultJson.putAll(R.appOk());
        if (StringUtils.isNotBlank(jsonObj.getString("teaId"))){
            //有id则更新
            teacherService.update(teacherVO);
        }else {
            //无id则增加
            boolean flag = teacherService.verifyTeacherNo(teacherVO.getTJnumber());
            if (flag){
                resultJson.putAll(R.appOk("1","该老师工号已存在！"));
            }else {
                teacherService.save(teacherVO);
            }
        }
        logger.info("结束保存/修改老师信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }
    /**
     * 保存/修改学生信息
     * @param jsonObj
     * @return
     */
    public String saveStudentList(JSONObject jsonObj){
        logger.info("开始保存/修改学生信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        StudentInfoVO studentInfoVO = jsonToStu(jsonObj);
        resultJson.putAll(R.appOk());
        if (StringUtils.isNotBlank(jsonObj.getString("stuId"))){
            //有id则更新
            studentInfoService.update(studentInfoVO);
        }else {
            //无id则增加
            boolean flag = studentInfoService.verifyTeacherNo(studentInfoVO.getSTudentid());
            if (flag){
                resultJson.putAll(R.appOk("1","该学生学号已存在！"));
            }else {
                studentInfoService.save(studentInfoVO);
            }
        }
        logger.info("结束保存/修改学生信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }
    /**
     * 删除学生信息
     * @param jsonObj
     * @return
     */
    public String deleteStudent(JSONObject jsonObj){
        logger.info("开始删除学生信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(jsonObj.getString("stuId"))){
            //有id则更新
            studentInfoService.remove(jsonObj.getString("stuId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除学生信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 删除老师信息
     * @param jsonObj
     * @return
     */
    public String deleteTeacher(JSONObject jsonObj){
        logger.info("开始删除老师信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        TeacherVO teacherVO = jsonToTeacher(jsonObj);
        if (StringUtils.isNotBlank(jsonObj.getString("teaId"))){
            //有id则更新
            teacherService.remove(jsonObj.getString("teaId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除老师信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 午餐列表
     * @param jsonObj
     * @return
     */
    public String getSchoolLunchList(JSONObject jsonObj){
        logger.info("开始获取午餐列表...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        JSONObject queryJson = new JSONObject();
        JSONArray lunchArray = null;
        JSONObject lunchJson = null;
        for (int i=1; i <= 5; i++){
            lunchArray = new JSONArray();
            queryJson.put("aDate",i+"");
            List<SchoolLunchVO> schoolLunchList = schoolLunchService.list(queryJson);
            if (schoolLunchList!=null){
                for (SchoolLunchVO schoolLunchVO : schoolLunchList){
                    lunchJson = new JSONObject();
                    lunchJson.put("slId",schoolLunchVO.getSlId());
                    lunchJson.put("day",schoolLunchVO.getaDate());
                    lunchJson.put("slName",schoolLunchVO.getSlName());
                    lunchJson.put("slTaste", schoolLunchVO.getSlTaste());
                    lunchJson.put("slRemarks",schoolLunchVO.getANote());
                    lunchArray.add(lunchJson);
                }
            }
            for (int j = lunchArray.size(); j < 10; j++){
                lunchJson = new JSONObject();
                lunchJson.put("slId","");
                lunchJson.put("day","");
                lunchJson.put("slName","");
                lunchJson.put("slTaste", "");
                lunchJson.put("slRemarks","");
                lunchArray.add(lunchJson);
            }
            resultJson.put(i+"",lunchArray);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束获取午餐列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 保存/更新午餐
     * @param jsonObj
     * @return
     */
    public String saveSchoolLunchList(JSONObject jsonObj){
        logger.info("开始保存/更新午餐信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        SchoolLunchVO schoolLunchVO = new SchoolLunchVO();
        schoolLunchVO.setSlId(jsonObj.getString("slId"));
        schoolLunchVO.setSlName(jsonObj.getString("slName"));
        schoolLunchVO.setSlTaste( jsonObj.getString("slTaste"));
        schoolLunchVO.setaDate(jsonObj.getString("day"));
        schoolLunchVO.setANote(jsonObj.getString("slRemarks"));
        if (StringUtils.isNotBlank(jsonObj.getString("slId"))){
            //有id则更新
            schoolLunchService.update(schoolLunchVO);
        }else {
            //无id则增加
            schoolLunchService.save(schoolLunchVO);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束保存/更新午餐信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 删除午餐信息
     * @param jsonObj
     * @return
     */
    public String deleteSchoolLunchList(JSONObject jsonObj){
        logger.info("开始删除午餐信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(jsonObj.getString("slId"))){
            //有id则更新
            schoolLunchService.remove(jsonObj.getString("slId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除午餐信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 课程列表
     * @param jsonObj
     * @return
     */
    public String getPeriodList(JSONObject jsonObj){
        logger.info("开始获取课程列表...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        String periodDate = jsonObj.getString("periodDate");//时间
        String classesId = jsonObj.getString("classId");//班级
        JSONObject queryJson = new JSONObject();
        queryJson.put("pStartdate",DateTimeUtils.parseDate(periodDate));
        queryJson.put("pClasss",classesId);
        List<PeriodVO> periodList = periodService.list(queryJson);
        JSONArray periodArray = new JSONArray();
        JSONObject periodJson = null;
        if (periodList!=null){
            for (PeriodVO periodVO : periodList){
                periodJson = new JSONObject();
                periodJson.put("pId",periodVO.getPId());
                periodJson.put("courseId",periodVO.getPCourse());
                periodJson.put("courseName",periodVO.getCourseName());
                periodJson.put("classesId",periodVO.getPClasss());
                periodJson.put("classesName",periodVO.getClassesName());
                periodJson.put("startDate",DateTimeUtils.formatDateTime(periodVO.getPStartdate()));
                periodJson.put("endDate",DateTimeUtils.formatDateTime(periodVO.getPEndDate()));
                periodJson.put("remarks",periodVO.getPNote());
                periodArray.add(periodJson);
            }
        }
        resultJson.putAll(R.appOk());
        resultJson.put("periodList",periodArray);
        logger.info("结束获取课程列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 保存/更新课程
     * @param jsonObj
     * @return
     */
    public String savePeriodList(JSONObject jsonObj){
        logger.info("开始保存/更新课程信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        PeriodVO periodVO = new PeriodVO();
        periodVO.setPId(jsonObj.getString("pId"));
        periodVO.setPCourse(jsonObj.getString("courseId"));
        periodVO.setCourseName( jsonObj.getString("courseName"));
        periodVO.setPClasss(jsonObj.getString("classId"));
        periodVO.setClassesName(jsonObj.getString("classesName"));
        periodVO.setPNote(jsonObj.getString("day"));
        periodVO.setPStartdate(DateTimeUtils.parseDate(jsonObj.getString("startDate")));
        periodVO.setPEndDate(DateTimeUtils.parseDate(jsonObj.getString("endDate")));
        if (StringUtils.isNotBlank(jsonObj.getString("pId"))){
            //有id则更新
            periodService.update(periodVO);
        }else {
            //无id则增加
            periodService.save(periodVO);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束保存/更新课程信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 删除课程信息
     * @param jsonObj
     * @return
     */
    public String deletePeriodList(JSONObject jsonObj){
        logger.info("开始删除课程信息...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        if (StringUtils.isNotBlank(jsonObj.getString("pId"))){
            //有id则更新
            periodService.remove(jsonObj.getString("pId"));
        }
        resultJson.putAll(R.appOk());
        logger.info("结束删除课程信息...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 获取全部课程列表
     * @param jsonObj
     * @return
     */
    public String getCourseList(JSONObject jsonObj){
        logger.info("开始获取全部课程列表...请求参数为："+jsonObj);
        JSONObject resultJson = new JSONObject();
        List<CourseVO> courseList = courseService.list(new HashMap<>());
        JSONArray courseArray = new JSONArray();
        JSONObject courseJson = null;
        if (courseList!=null){
            for (CourseVO course : courseList){
                courseJson = new JSONObject();
                courseJson.put("courseId",course.getCourseId());
                courseJson.put("courseName",course.getCourseName());
                courseArray.add(courseJson);
            }
        }
        resultJson.put("courseList",courseArray);
        resultJson.putAll(R.appOk());
        logger.info("结束获取全部课程列表...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 修改密码
     * @param jsonObj
     * @return
     */
    public String resetPassword(JSONObject jsonObj){
        logger.info("开始修改密码...请求参数为："+jsonObj);
        String loginId = jsonObj.getString("loginId");
        String newPassword = jsonObj.getString("newPassword");
        String oldPassword = jsonObj.getString("oldPassword");
        UserVO newUser = new UserVO();
        UserDO userDO = new UserDO();
        userDO.setUserId(Long.parseLong(loginId));
        newUser.setUserDO(userDO);
        newUser.setPwdNew(newPassword);
        newUser.setPwdOld(oldPassword);
//        UserDO oldUser = ShiroUtils.getUser();
        UserDO oldUser = userService.get(Long.parseLong(loginId));
        JSONObject resultJson = new JSONObject();
        try {
            userService.resetPwd(newUser,oldUser);
            resultJson.putAll(R.appOk("0","密码修改成功！"));
        } catch (Exception e) {
            resultJson.putAll(R.appOk("1",e.getMessage()));
        }
        logger.info("结束修改密码...请求参数为："+jsonObj);
        return resultJson.toString();
    }

    /**
     * 获取全部课表安排
     * @param jsonObj
     * @return
     */
    public String getAllPeriod(JSONObject jsonObj){
        logger.info("开始获取全部课表安排...请求参数为："+jsonObj);
        String classId = jsonObj.getString("classId");
        JSONObject resultJson = new JSONObject();
        String periodTimeStr = dictService.getName("period_time", "array");
        String[] periodTimeArray = periodTimeStr.split(",");
        JSONArray periodArray = null;
        JSONObject periodJson = null;
        for (int i = 1; i <= 5; i++){
            periodArray = new JSONArray();
            Map<String, PeriodVO> stringPeriodVOMap = getperiodVOList(i + "",classId);
            for (String str : periodTimeArray){
                periodJson = new JSONObject();
                periodJson.put("pId","");
                periodJson.put("courseId","");
                periodJson.put("courseName","");
                periodJson.put("classesId","");
                periodJson.put("classesName","");
                periodJson.put("startDate",str);
                periodJson.put("endDate","");
                if (stringPeriodVOMap.containsKey(str)&&stringPeriodVOMap.get(str)!=null){
                    PeriodVO periodVO = stringPeriodVOMap.get(str);
                    periodJson.put("pId",periodVO.getPId());
                    periodJson.put("courseId",periodVO.getPCourse());
                    periodJson.put("courseName",periodVO.getCourseName());
                    periodJson.put("classesId",periodVO.getPClasss());
                    periodJson.put("classesName",periodVO.getClassesName());
                    periodJson.put("startDate",DateTimeUtils.formatDate(periodVO.getPStartdate(),"HH:mm"));
                    periodJson.put("endDate",DateTimeUtils.formatDate(periodVO.getPEndDate(),"HH:mm"));
                }
                periodArray.add(periodJson);
            }
            resultJson.put(i+"",periodArray);
        }
        resultJson.putAll(R.appOk());
        logger.info("结束获取全部课表安排...请求参数为："+jsonObj);
        return resultJson.toString();
    }


    public Map<String,PeriodVO> getperiodVOList(String pNote,String classId){
        Map<String,PeriodVO> result = new HashMap<String,PeriodVO>();
        JSONObject query = new JSONObject();
        query.put("pNote",pNote);
        query.put("pClasss",classId);
        List<PeriodVO> periodVOList = periodService.list(query);
        if (periodVOList!=null){
            for (PeriodVO periodVO : periodVOList){
                result.put(DateTimeUtils.formatDate(periodVO.getPStartdate(),"HH:mm"),periodVO);
            }
        }
        return result;
    }

    /**
     * 老师实体类转json
     * @param teacherVO
     * @return
     */
    public JSONObject teacherToJson(TeacherVO teacherVO){
        JSONObject teacherJson = new JSONObject();
        UserDO user = teacherVO.getUser();
        teacherJson.put("teaId",teacherVO.getTId());//老师id
        teacherJson.put("jNumber",teacherVO.getTJnumber());//工号
        teacherJson.put("name",user.getName());//姓名
        teacherJson.put("userName",teacherVO.getTJnumber());//姓名
        teacherJson.put("mobile",user.getMobile());//手机号
        teacherJson.put("sexId",user.getSex());//性别
        teacherJson.put("sexName",dictService.getName("sex",user.getSex()+""));//性别
        teacherJson.put("age",user.getAge());//年龄
        teacherJson.put("birth",user.getBirth());//出生日期
        teacherJson.put("classId",user.getDeptId());//班级id
        teacherJson.put("className",user.getDeptName());//班级name
        teacherJson.put("nation",teacherVO.getTNation());//民族
        teacherJson.put("positionName",teacherVO.gettPositionName());//职位
        teacherJson.put("position",teacherVO.getTPosition());//职位id
        teacherJson.put("political",teacherVO.getTPolitical());//政治面貌
        teacherJson.put("education",teacherVO.getTEducation());//学历
        teacherJson.put("state",teacherVO.getTState());//状态
        teacherJson.put("address",teacherVO.getTHome());//家庭住址
        teacherJson.put("remarks",teacherVO.getTNote());//备注
        return teacherJson;
    }

    /**
     * json转老师实体类
     * @param teaJson
     * @return
     */
    public TeacherVO jsonToTeacher(JSONObject teaJson){
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setTId(teaJson.getString("teaId"));//老师id
        teacherVO.setTJnumber(teaJson.getString("jNumber"));//工号
        UserDO user = new UserDO();
        user.setName(teaJson.getString("name"));//姓名
        user.setUsername(teaJson.getString("jNumber"));//登录名
        user.setMobile(teaJson.getString("mobile"));//手机号
        user.setSex(teaJson.getLong("sexId"));
        user.setAge(teaJson.getInteger("age"));
        user.setDeptId(teaJson.getLong("classId"));
        user.setBirth(DateTimeUtils.parseDate(teaJson.getString("birth")));
        user.setStatus(teaJson.getInteger("state"));
        teacherVO.setTNation(teaJson.getString("nation"));
        teacherVO.setTPosition(teaJson.getString("position"));//职位id
        teacherVO.setTPolitical(teaJson.getString("political"));//政治面貌
        teacherVO.setTEducation(teaJson.getString("education"));
        teacherVO.setTHome(teaJson.getString("address"));
        teacherVO.setTNote(teaJson.getString("remarks"));//备注
        teacherVO.setUser(user);
        return teacherVO;
    }

    /**
     * 学生实体类转json
     * @param teacherVO
     * @return
     */
    public JSONObject stuToJson(StudentInfoVO studentInfoVO){
        JSONObject studentJson = new JSONObject();
        UserDO user = studentInfoVO.getUser();
        studentJson.put("stuId",studentInfoVO.getStuId());//学生id
        studentJson.put("name",user.getName());//学生姓名
        studentJson.put("UserName",user.getUsername());//学生登录名
        studentJson.put("sexId",user.getSex()); //学生性别
        studentJson.put("sexName",dictService.getName("sex",user.getSex()+"")); //学生性别
        studentJson.put("age",user.getAge());//年龄
        studentJson.put("parentTele",user.getMobile());//电话号
        studentJson.put("birth", user.getBirth());
        studentJson.put("state", user.getStatus());
        studentJson.put("studentNo",studentInfoVO.getSTudentid());//学号
        studentJson.put("entranceDate",DateTimeUtils.formatDate(studentInfoVO.getSEntranceDate(),"yyyy-MM-dd"));//入学时间
        studentJson.put("classId",studentInfoVO.getSClasss());//班级id
        studentJson.put("className",studentInfoVO.getClassName());//班级名称
        studentJson.put("parentMobile",studentInfoVO.getSNumber());//家长电话号
        studentJson.put("nativePlace",studentInfoVO.getSNativePlace());//学生籍贯
        studentJson.put("parentsName",studentInfoVO.getSParentsName());//家长姓名
        studentJson.put("national",studentInfoVO.getSNational());//学生民族
        return studentJson;
    }

    /**
     * json转学生实体类
     * @param stuJson
     * @return
     */
    public StudentInfoVO jsonToStu(JSONObject stuJson){
        StudentInfoVO studentInfoVO = new StudentInfoVO();
        UserDO user = new UserDO();
        user.setStatus(stuJson.getInteger("state"));
        user.setName(stuJson.getString("name"));
        user.setUsername(stuJson.getString("studentNo"));
        user.setMobile(stuJson.getString("parentTele"));
        user.setSex(stuJson.getLong("sexId"));
        user.setAge(stuJson.getInteger("age"));
        user.setBirth(DateTimeUtils.parseDate(stuJson.getString("birth")));
        studentInfoVO.setStuId(stuJson.getString("stuId"));
        studentInfoVO.setSTudentid(stuJson.getString("studentNo"));
        studentInfoVO.setSEntranceDate(DateTimeUtils.parseDate(stuJson.getString("entranceDate")));
        studentInfoVO.setSClasss(stuJson.getString("classId"));
        studentInfoVO.setSNumber(stuJson.getString("parentTele"));
        studentInfoVO.setSParentsName(stuJson.getString("parentsName"));
        studentInfoVO.setSParentsNumber(stuJson.getString("parentMobile"));
        studentInfoVO.setSNativePlace(stuJson.getString("nativePlace"));
        studentInfoVO.setSNational(stuJson.getString("national"));
        studentInfoVO.setUser(user);
        return studentInfoVO;
    }

}
