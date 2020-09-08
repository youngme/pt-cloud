package com.bin.cloud.system.core.api;

/**
 * @Description 接口
 * @Author hubin
 * @Date 2019-10-21 17:15
 * @Version 1.0
 **/
public class ApiConstant {

    /******************** oauth2.0 认证接口*********************/
    // 根据username查询用户
    public static final String OAUTH2_USER_BY_USERNAME = "/user";
    // 根据mobile查询用户
    public static final String OAUTH2_USER_BY_MOBILE = "/mobile";
    // 根据第三方openid获取用户信息
    public static final String OAUTH2_USER_BY_OPENID = "/openid";
    // 保存或更新用户信息
    public static final String OAUTH2_USER_SAVE_OR_UPDATE = "/user/saveOrUpdate";

    // 根据用户ID查询角色列表
    public static final String OAUTH2_ROLE_BY_USERID = "/role/user/{userId}";

    // 根据用户ID授权
    public static final String OAUTH2_ROLE_SAVE_BY_USER = "/role/save/byUser";
}
