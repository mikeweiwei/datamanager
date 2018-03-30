package com.bonc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.dao.Question;
import com.bonc.util.JsonResult;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("useranalyse")
public class UserAnalyseController {
	
	@Autowired
	Question question;
	
	

	@RequestMapping("qatype")
	public String downPageLog(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime
			,String typeId,@RequestParam(required=true)Integer page,@RequestParam(required=true)Integer size) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		if(page == 1) {
			page = 0;
		}else {
			page = (page - 1) * size;
		}
		JSONObject dataJson = new JSONObject();
		List<Map<String, String>> analyse= question.getQaTypeAnalyse(startTime,endTime);
		List<Map<String, Object>> data= question.getQaTypeAnalyseList(startTime, endTime, size, page,null);
		Map<String, String> totle= question.getQaTypeAnalyseListTotle(startTime,endTime);
		dataJson.put("analyse", analyse);
		dataJson.put("data", data);
		dataJson.put("totle", totle);
		return JsonResult.makeJson(dataJson).toString();
	}
	
	@RequestMapping("issolve")
	public String isslove(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime
			,@RequestParam(required=true)Integer page,@RequestParam(required=true)Integer size) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		if(page == 1) {
			page = 0;
		}else {
			page = (page - 1) * size;
		}
		JSONObject dataJson = new JSONObject();
		List<Map<String, String>> analyse= question.getIsSolveAnalyse(startTime, endTime);
		List<Map<String, Object>> data= question.getIsSolveAnalyseList(startTime,endTime,size,page,null);
		Map<String, String> totle= question.getIsSolveAnalyseListTotle(startTime,endTime);
		dataJson.put("analyse", analyse);
		dataJson.put("data", data);
		dataJson.put("totle", totle);
		return JsonResult.makeJson(dataJson).toString();
		
	}
	
	@RequestMapping("satisified")
	public String SatisifiedAnalyse(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime
			,String isSatisified,@RequestParam(required=true)Integer page,@RequestParam(required=true)Integer size) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		if(page == 1) {
			page = 0;
		}else {
			page = (page - 1) * size;
		}
		JSONObject dataJson = new JSONObject();
		List<Map<String, String>> analyse= question.getSatisifiedAnalyse(startTime, endTime);
		List<Map<String, Object>> data= question.getSatisifiedList(startTime, endTime, size, page,null);
		Map<String, String> totle= question.getSatisifiedListTotle(startTime, endTime);
		dataJson.put("analyse", analyse);
		dataJson.put("data", data);
		dataJson.put("totle", totle);
		return JsonResult.makeJson(dataJson).toString();
		
	}
	
	@RequestMapping("city")
	public String city(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime
			,@RequestParam(required=true)Integer page,@RequestParam(required=true)Integer size) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		if(page == 1) {
			page = 0;
		}else {
			page = (page - 1) * size;
		}
		JSONObject dataJson = new JSONObject();
		List<Map<String, String>> analyse= question.getCityAnalyse(startTime, endTime);
		List<Map<String, Object>> data= question.getCityAnalyseList(startTime, endTime, size,page,null);
		Map<String, String> totle= question.getCityAnalyseListTotle(startTime, endTime);
		dataJson.put("analyse", analyse);
		dataJson.put("data", data);
		dataJson.put("totle", totle);
		return JsonResult.makeJson(dataJson).toString();
		
	}
	
	

}
