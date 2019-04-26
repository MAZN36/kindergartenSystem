package com.bootdo.front.delegate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppReqDelegate {

    @Autowired
    private UserService userService;

    @Autowired
    MenuService menuService;

    /**
     * 登录系统
     * @param username
     * @param password
     * @return
     */
    public String login(JSONObject jsonObj){
        String username = jsonObj.getString("username");
        String password = jsonObj.getString("password");
        JSONObject resultJson = new JSONObject();
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
        resultJson.putAll(R.ok());
        resultJson.put("name",userDo.getName());
        resultJson.put("deptName",userDo.getDeptName());
        resultJson.put("roleIds",userDo.getRoleIds());
        return resultJson.toString();
    }

}
