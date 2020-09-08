package com.bin.cloud.business.material.api;

/**
 * @Description 接口
 * @Author hubin
 * @Date 2019-09-11 13:38
 * @Version 1.0
 **/
public class ApiConstant {

    /**
     * 图片服务
     */
    // 图片上传
    public static final String IMG_SERVER_UPLOAD = "/server/img/upload";


    /**
     * 建材信息
     */
    private static final String BASE_MAPPING = "/jcc/";
    // 保存
    public static final String JCC_MATERIAL_SAVE = BASE_MAPPING + "material/save";
    // 更新
    public static final String JCC_MATERIAL_UPDATE = BASE_MAPPING + "material/update";
    // 根据关系ID查询详细信息
    public static final String JCC_MATERIAL_GET = BASE_MAPPING+"material/free/queryInfo";
    // 分页
    public static final String JCC_MATERIAL_PAGE = BASE_MAPPING + "material/free/page";
    // 公众号根据类型获取建材列表
    public static final String JCC_MATERIAL_LIST_BY_TYPE = BASE_MAPPING + "material/free/home/jcList";
    // 根据用户ID获取数据列表
    public static final String JCC_MATERIAL_LIST_BY_USERID = BASE_MAPPING + "material/list/userId";
    // 根据用户ID获取数据发布总数
    public static final String QUERY_JCC_MATERIAL_PUSH_TOTAL_BY_USER_ID = BASE_MAPPING + "material/count/userId";
    // 查询实时资讯列表
    public static final String INFORMATION_MESSAGE_LIST = BASE_MAPPING+"information/page";
    // 查询实时资讯信息根据ID
    public static final String INFORMATION_MESSAGE_INFO = BASE_MAPPING+"information/info";
    // 保存实时资讯信息
    public static final String INFORMATION_MESSAGE_SAVE = BASE_MAPPING+"information/save";
    // 保存实时资讯信息阅读量
    public static final String INFORMATION_MESSAGE_ADD_READER = BASE_MAPPING+"information/addCount";

    // 保存楼盘信息
    public static final String BUILDING_INFO_SAVE = BASE_MAPPING + "building/save";
    // 查询楼盘分页列表
    public static final String BUILDING_PAGE_LIST = BASE_MAPPING + "building/free/page";
    // 获取楼盘详细信息
    public static final String BUILDING_QUERY_INFO = BASE_MAPPING+ "building/free/query/info";
    // 获取用户 发布楼盘列表
    public static final String BUILDING_QUERY_PUSH_LIST_BY_USER = BASE_MAPPING + "building/user/push/list";
    // 根据用户ID获取数据发布总数
    public static final String QUERY_BUILDING_PUSH_TOTAL_BY_USER_ID = BASE_MAPPING + "building/count/userId";
    // 信息收藏
    public static final String COLLECT_SAVE = BASE_MAPPING+ "collect/save";
    // 收藏信息分页
    public static final String COLLECT_PAGE_QUERY = BASE_MAPPING + "collect/page";

    public static final String COLLECT_TOTAL_BY_USER_ID = BASE_MAPPING + "collect/total";
    // 楼盘信息删除
    public static final String JCC_BUILDING_REMOVE_BY_ID = BASE_MAPPING + "building/remove";
    // 建材信息删除
    public static final String JCC_MATERIAL_REMOVE_BY_ID = BASE_MAPPING + "material/remove";

    public static final String JCC_UPDATE_READING_COUNT = BASE_MAPPING + "reading/update";

    public static final String JCC_GET_READING_COUNT = BASE_MAPPING + "reading/count";

}
