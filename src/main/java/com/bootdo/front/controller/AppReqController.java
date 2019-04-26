package com.bootdo.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.front.delegate.AppReqDelegate;
import com.bootdo.front.utils.AppParamUtils;
import com.bootdo.common.utils.R;
import com.bootdo.front.utils.EncryptUtil;
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
                case "login" :
                    returnStr = reqDelegate.login(jsonObj);
                    break;
                default:
                    returnStr = "{\"resultCode\":\"1\",\"resultMessage\":\"未找到对应key值\"}";
                    break;
            }
            logger.info("返回app的出参是returnStr=" + returnStr);
//            returnStr = EncryptUtil.encryptThreeDESECB(returnStr);
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
