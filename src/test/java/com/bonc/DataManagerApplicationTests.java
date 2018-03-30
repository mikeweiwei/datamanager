package com.bonc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bonc.dao.Question;
import com.bonc.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataManagerApplicationTests {
	
	@Autowired
	Question question;
	@Autowired
	JedisPool jedisPool;

	@Test
	public void contextLoads() {
		 Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(date);
		 System.out.println(dateString);
	}
	
	@Test
	public void edistest(){
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set("a","a");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jedis.close();
		}
	}
	
	@Test
	public void insert() {
		List<Map<String, Object>> list = question.getUserTotleByDay("2016-11-30", "2017-01-01");
		System.out.println(list.toString());
	}
	@Test
	public void insert1() {
		List<Map<String, Object>> list = question.getTotleByUserAndQa("2016-11-30");
		System.out.println(list.toString());
	}
	@Test
	public void insert2() {
		
		Integer[] matchTypeId = new Integer[2];
		matchTypeId[0] = 0;
		matchTypeId[1] = 4;
		List<Map<String, String>> list = question.getone(null);
		System.out.println(list.size());
	}
	
	@Test
	public void testcount() {
		List<Map<String, Object>> totle = question.getQaList("2016-01-01", "2017-05-05", null, null, null, null, null, null,"1");
		System.out.println(totle);
	}
	
	
	

}
