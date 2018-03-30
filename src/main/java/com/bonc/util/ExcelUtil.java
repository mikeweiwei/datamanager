package com.bonc.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	
	/**
	 * 生成表格
	 * @param path
	 * @param menu
	 * @param data
	 * @param type
	 * @return filePath
	 */
	public static String produceExcel(String path,List<Map<String, Object>> data,String type,String fileName){
		
		File file;
		WritableWorkbook wwb;	
		if (type.toLowerCase().equals("csv")){
			fileName = fileName + ".csv";
		}else{
			fileName = fileName + ".xls";
		}
		String filePath = path + File.separator  + fileName;
		try {
			file = new File(filePath);
			wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet("页1", 0);
			Map<String, Object> menu = data.get(0);
			Set<String> keySet1 = menu.keySet();
			//添加表头
			int column1 = 0;
			for (String key : keySet1) {
				ws.addCell(new Label(column1, 0,key));
				column1++;
			}
			//添加正文
			for (int i = 0;i < data.size();i++){
				Map<String, Object> map = data.get(i);
				Set<String> keySet = map.keySet();
				int column = 0;
				for (String key : keySet) {
					ws.addCell(new Label(column, i + 1,String.valueOf(map.get(key))));
					column++;
				}
			}
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return filePath;
	}
 
	

}
