package com.bonc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.dao.Question;
import com.bonc.util.ExcelUtil;
import com.bonc.util.JsonResult;

import net.sf.json.JSONObject;


@RestController
public class ExcelController {
	
	@Autowired
	Question question;
	
	@RequestMapping("downloadexcel")
	public Object downPageLog(@RequestParam(required=true)String startTime,@RequestParam(required=true)String endTime,
			HttpServletRequest req, HttpServletResponse res,String type) {
		
		List<Map<String, Object>> data = null;
		
		if(type.equals("qalist")) {
			data = question.getQaList(startTime, endTime, null, null, null, null, null, null, "1");
		}else if(type.equals("qatype")){
			data= question.getQaTypeAnalyseList(startTime, endTime, null, null,"1");
		}else if(type.equals("issolve")){
			data= question.getIsSolveAnalyseList(startTime,endTime,null,null,"1");
		}else if(type.equals("satisified")){
			data= question.getSatisifiedList(startTime, endTime, null, null,"1");
		}else if(type.equals("city")){
			data= question.getCityAnalyseList(startTime, endTime, null, null,"1");
		}
		
		if(data == null) {
			return JsonResult.makeErrorJson(500, "无数据！");
		}
		ServletContext context = req.getServletContext();
		String path = context.getRealPath(File.separator);
		long fileName = currentTime();
		//生成表格
		String excelPath = ExcelUtil.produceExcel(path,data,"xls",String.valueOf(fileName));
		InputStream inputStream = null;
		OutputStream os = null;
		Date nowTime = new Date();
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime) + ".xls";
//		if (type.toLowerCase().equals("csv")){
//			retStrFormatNowDate = sdFormatter.format(nowTime) + ".csv";
//		}
		res.setCharacterEncoding("utf-8");
		res.setContentType("multipart/form-data");
		res.setHeader("Content-Disposition", "attachment;filename=\"" + retStrFormatNowDate + "\"");
		try {
			// 下载文件
			inputStream = new FileInputStream(new File(excelPath));
			os = res.getOutputStream();
			byte[] b = new byte[4096];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				os.flush();
				os.close();
				inputStream.close();
				if (new File(excelPath).exists()) {
					new File(excelPath).delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	//获取当前long时间
	private static long currentTime()  
	{  
	    return new Date().getTime()/1000;   //获取当前时间的秒数值      
	}  

}
