package com.common.config;

public class Config {
	public static final String OVERALLURL = "http://139.196.168.248:8080/iccs_apps";
	/** 2016/01/06 ems快递查询接口 **/
	public static final String QUERY_EXPRESS_TRACK = "http://api.kuaidi100.com/api";
	/** 查询房屋认证信息列表 */
	public static final String QUERY_APPLY = OVERALLURL + "/useApply.html?method=queryUseApplys";
	/** 百度API KEY */
	public static final String BAIDU_KEY = "dafb3deb8c8044f7596d538cbe3afa32";

	/** 笑话 **/
	public static final String JOKE = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
}
