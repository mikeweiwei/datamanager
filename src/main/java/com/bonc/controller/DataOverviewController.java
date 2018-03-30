package com.bonc.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.bonc.util.SerializeUtil;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@RestController
@RequestMapping("dataoverview")
public class DataOverviewController {
	
	
	@Autowired
	Question question;
	@Autowired
	JedisPool jedisPool;
	

	@RequestMapping("getoverview")
	public String getoverview(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		Jedis jedis = jedisPool.getResource();
		res.setHeader("Access-Control-Allow-Origin", "*");
		try {
			String method = "getoverview()";
			String key = method + startTime + endTime;
			Boolean exists = jedis.exists(key.getBytes());
			if(exists) {
				byte[] data = jedis.get(key.getBytes());
				JSONObject object = JSONObject.fromObject(SerializeUtil.unserialize(data));
				return JsonResult.makeJson(object).toString();
			}else {
				
				JSONObject dataJson = new JSONObject();
				List<Map<String, Object>> totleByDay = question.getUserTotleByDay(startTime, endTime);
				dataJson.put("foldLine", totleByDay);
				List<Map<String, Object>> userAndQa = question.getTotleByUserAndQa(null);
				dataJson.put("historyUserAndQa", userAndQa);
				List<Map<String, Object>> solveQa = question.getTotleBySolveQa(null);
				dataJson.put("historySolveQa", solveQa);
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String todayString = formatter.format(date);
				List<Map<String, Object>> todayUserAndQa = question.getTotleByUserAndQa(todayString);
				dataJson.put("todayUserAndQa", todayUserAndQa);
				List<Map<String, Object>> todaySolveQa = question.getTotleBySolveQa(todayString);
				dataJson.put("todaySolveQa", todaySolveQa);
				List<Map<String, Object>> questionCount= question.getQuestionCount(startTime, endTime);
				dataJson.put("questionCount", questionCount);
				List<Map<String, Object>> SolveOrNoCount= question.getSolveOrNoCount(startTime, endTime);
				dataJson.put("SolveOrNoCount", SolveOrNoCount);
				List<Map<String, Object>> SatisifiedCount= question.getSatisifiedCount(startTime, endTime);
				dataJson.put("SatisifiedCount", SatisifiedCount);
				List<Map<String, Object>> CityCount= question.getCityCount(startTime, endTime);
				dataJson.put("CityCount", CityCount);
				jedis.setex(key.getBytes(),3600,SerializeUtil.serialize(dataJson));
				return JsonResult.makeJson(dataJson).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			jedis.close();
		}
	}
	@RequestMapping("gettotleuserbyday")
	public String gettotleuserbyday(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getUserTotleByDay(startTime, endTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotleqabyday")
	public String gettotleqabyday(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getQaTotleByDay(startTime, endTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotlesolvebyday")
	public String gettotlesolvebyday(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getSolveQaByDay(startTime, endTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotleuserbym")
	public String gettotleuserbymo(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getUserTotleByMo(startTime, endTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotleqabym")
	public String gettotleqabyh(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getQaTotleByMo(startTime, endTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotlesolvebym")
	public String gettotlesolvebyh(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getSolveQaByMo(startTime, endTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotleuserbyh")
	public String gettotleuserbyh(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getUserTotleByH(startTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("gettotleqabyh")
	public String gettotleqabymo(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getQaTotleByH(startTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}	
	@RequestMapping("gettotlenosolvebyh")
	public String gettotlesolvebymo(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> totleByDay = question.getNoSolveQaByH(startTime);
		dataJson.put("foldLine", totleByDay);
		return JsonResult.makeJson(dataJson).toString();
	}	
	@RequestMapping("getquestioncountbyday")
	public String questionCount(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> questionCount= question.getQuestionCount(startTime, endTime);
		dataJson.put("questionCount", questionCount);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("getSolveorcountbyday")
	public String SolveOrNoCount(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> SolveOrNoCount= question.getSolveOrNoCount(startTime, endTime);
		dataJson.put("SolveOrNoCount", SolveOrNoCount);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("getsatisifiedcountbyday")
	public String SatisifiedCount(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");//3-1,3-5
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> SatisifiedCount= question.getSatisifiedCount(startTime, endTime);
		dataJson.put("SatisifiedCount", SatisifiedCount);
		return JsonResult.makeJson(dataJson).toString();
	}
	@RequestMapping("getcitycountbyday")
	public String CityCount(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> CityCount= question.getCityCount(startTime, endTime);
		dataJson.put("CityCount", CityCount);
		return JsonResult.makeJson(dataJson).toString();
	}
	
	@RequestMapping("test")
	public String totle(HttpServletRequest req, HttpServletResponse res,@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime
			,int matchTypeId,int isSplved,String input,String output) {
		
		res.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject dataJson = new JSONObject();
		List<Map<String, Object>> CityCount= question.getCityCount(startTime, endTime);
		dataJson.put("CityCount", CityCount);
		return JsonResult.makeJson(dataJson).toString();
	}
	
}
