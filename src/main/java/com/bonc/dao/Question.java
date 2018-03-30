package com.bonc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Question {

	//折线图
	//按日统计累计用户数
	List<Map<String, Object>> getUserTotleByDay(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getQaTotleByDay(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getSolveQaByDay(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//月
	List<Map<String, Object>> getUserTotleByMo(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getQaTotleByMo(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getSolveQaByMo(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//小时
	List<Map<String, Object>> getUserTotleByH(@Param("startTime")String startTime);
	List<Map<String, Object>> getQaTotleByH(@Param("startTime")String startTime);
	List<Map<String, Object>> getNoSolveQaByH(@Param("startTime")String startTime);
	
	//用户分析
	//常见问题分类统计 1.统计 2.展示   参数时间
	//List<Map<String, Object>> getQuestionType(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//累计问答数和用户数
	List<Map<String, Object>> getTotleByUserAndQa(@Param("startTime")String startTime);
	//累计解决问答数
	List<Map<String, Object>> getTotleBySolveQa(@Param("startTime")String startTime);
	
	//常见问题分类统计
	List<Map<String, Object>> getQuestionCount(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//解决未解决统计
	List<Map<String, Object>> getSolveOrNoCount(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//用户满意度分析
	List<Map<String, Object>> getSatisifiedCount(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//地域top10
	List<Map<String, Object>> getCityCount(@Param("startTime")String startTime,@Param("endTime")String endTime);

	//问答记录总数 
	Map<String, Object> getQaListTotle(@Param("startTime")String startTime,@Param("endTime")String endTime
			,@Param("MATCHTYPEID")Integer[] matchTypeId,@Param("ISSOLVED")Integer isSplved,@Param("INPUT")String input
			,@Param("OUTPUT")String output);
	//问答记录
	List<Map<String, Object>> getQaList(@Param("startTime")String startTime,@Param("endTime")String endTime
			,@Param("MATCHTYPEID")Integer[] matchTypeId,@Param("ISSOLVED")Integer isSplved,@Param("INPUT")String input
			,@Param("OUTPUT")String output,@Param("size")Integer size,@Param("page")Integer page,@Param("excel")String excel);
	List<Map<String, Object>>  getmenu();
	

	
	List<Map<String, String>> getDataList(@Param("recardId")Integer[] recardId);
	
	//用户分析
	List<Map<String, String>> getQaTypeAnalyse(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getQaTypeAnalyseList(@Param("startTime")String startTime,@Param("endTime")String endTime
			,@Param("size")Integer size,@Param("page")Integer page,@Param("excel")String excel);
	Map<String, String> getQaTypeAnalyseListTotle(@Param("startTime")String startTime,@Param("endTime")String endTime);

	
	//是否解决分析
	List<Map<String, String>> getIsSolveAnalyse(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getIsSolveAnalyseList(@Param("startTime")String startTime,@Param("endTime")String endTime
			,@Param("size")Integer size,@Param("page")Integer page,@Param("excel")String excel);
	Map<String, String> getIsSolveAnalyseListTotle(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	
	List<Map<String, String>> getSatisifiedAnalyse(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getSatisifiedList(@Param("startTime")String startTime,@Param("endTime")String endTime
			,@Param("size")Integer size,@Param("page")Integer page,@Param("excel")String excel);
	Map<String, String> getSatisifiedListTotle(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	
	//地域分析
	List<Map<String, String>> getCityAnalyse(@Param("startTime")String startTime,@Param("endTime")String endTime);
	List<Map<String, Object>> getCityAnalyseList(@Param("startTime")String startTime,@Param("endTime")String endTime
			,@Param("size")Integer size,@Param("page")Integer page,@Param("excel")String excel);
	Map<String, String> getCityAnalyseListTotle(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	//模拟数据
	int insertData(@Param("INPUT")String INPUT,@Param("OUTPUT")String OUTPUT
			,@Param("MATCH_QUESTID")String MATCH_QUESTID,@Param("MATCH_QUESTION")String MATCH_QUESTION
			,@Param("MATCH_TYPEID")String MATCH_TYPEID,@Param("MATCH_TYPE")String MATCH_TYPE);
	
	
	List<Map<String, String>> getone(@Param("MATCHTYPEID")Integer[] matchTypeId);
	
	
}
