package cn.ne.jwt.service;

import java.util.Map;

public interface IRoleAndActionService {

    /**
     * 根据指定的用户获取用户的角色与权限
     * @param mid   用户编号
     * @return      用户对应的权限与角色
     *  1、key = allRoles, value = 用户对应的角色id
     *  2、key = allActions, value = 用户对应的权限id
     */
    Map<String, Object> get(String mid);

}
