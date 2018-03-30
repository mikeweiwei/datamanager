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
@RequestMapping("questionrecord")
public class QuestionRecordController {
	
	@Autowired
	Question question;
	
	@RequestMapping("getqa")
	public String totle(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime
			,Integer[] matchTypeId,Integer isSolved,String input,String output,
			@RequestParam(required=true)Integer page,@RequestParam(required=true)Integer size) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		
		if(page == 1) {
			page = 0;
		}else {
			page = (page - 1) * size;
		}
		Map<String, Object> totle = question.getQaListTotle(startTime, endTime, matchTypeId, isSolved, input, output);
		List<Map<String, Object>> list = question.getQaList(startTime, endTime, matchTypeId, isSolved, input, output, size, page,null);
		JSONObject dataJson = new JSONObject();
		dataJson.put("totle", totle);
		dataJson.put("data", list);
		return JsonResult.makeJson(dataJson).toString();
		
	}
	
	@RequestMapping("getmenu")
	public String menu(HttpServletRequest req, HttpServletResponse res) {
	
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> getmenu = question.getmenu();
		dataJson.put("data", getmenu);
		return JsonResult.makeJson(dataJson).toString();
	}
	
	
	
	
	
	
	

}
