package com.bonc.util;


import net.sf.json.JSONObject;

/**
 * 返回对象封装
 * @author gyw
 *
 */
public class JsonResult {
	
	private static JSONObject resultJson = new JSONObject();
	private static JSONObject errorJson = new JSONObject();

    /**
     * 成功
     * @param data
     * @return
     */
    public static JSONObject makeJson(JSONObject data){
        resultJson.put("status", true);
        resultJson.put("result", data);
        resultJson.put("error", "null");
        return resultJson;
    }
    
    /**
     * 失败
     * @param errorCode
     * @param errorMes
     * @return
     */
    public static JSONObject makeErrorJson(int errorCode,String errorMes){
    	JSONObject resultJson = new JSONObject();
        errorJson.put("errCode",errorCode);
        errorJson.put("errMsg",errorMes);
        resultJson.put("status", false);
        resultJson.put("result", "null");
        resultJson.put("error", errorJson);
        return resultJson;
    }
}
