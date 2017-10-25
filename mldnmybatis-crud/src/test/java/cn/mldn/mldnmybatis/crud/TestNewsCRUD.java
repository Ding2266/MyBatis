package cn.mldn.mldnmybatis.crud;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import cn.mldn.mldnmybatis.util.MyBatisSessionFactory;
import cn.mldn.mldnmybatis.vo.News;
import junit.framework.TestCase;

public class TestNewsCRUD {
	private static Random random = new Random() ;
	private static int rand ;
	static {
		rand = random.nextInt(Integer.MAX_VALUE) ; 
	}
	@Test
	public void testSplit() throws Exception{
		Long currentPage = 1L ;  //传递进来处理的数据
		Integer lineSize = 5 ; 	//传递进来处理的数据
		String column = "title" ; 	//传递进来处理的数据
		String keyWord = "标题"  ; 	//传递进来处理的数据
		Map<String,Object> map = new HashMap<String,Object>() ; 
		map.put("column", column) ; 
		map.put("keyWord", "%"+keyWord+"%") ; 
		map.put("startPage", (currentPage - 1) * lineSize) ;
		map.put("lineSize", lineSize) ; 
		List<News> list = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findSplit", map); 
		Long count = MyBatisSessionFactory.getSession().selectOne("cn.mldn.mapping.NewsNS.getSplitCount",map) ;
		Iterator<News> iter = list.iterator() ; 
		while(iter.hasNext()) {
				System.out.println(iter.next());
			}
		System.err.println(count);
	}
	@Test
	public void testNewsMap() throws Exception{
		Map<Long,Map<String,Object>>  map = MyBatisSessionFactory.getSession().selectMap("cn.mldn.mapping.NewsNS.findMap", "nid") ; 
		//System.err.println(map);
		Iterator<Map.Entry<Long,Map<String,Object>>> iter = map.entrySet().iterator() ;
		while(iter.hasNext()) {
			Map.Entry<Long, Map<String,Object>> me = iter.next() ; 
			System.out.println( "key=" + me.getKey() +  "、value = " + me.getValue());
			Map<String,Object> ms = me.getValue() ; 
			Iterator<Map.Entry<String, Object>> it = ms.entrySet().iterator() ; 
			while(it.hasNext()) {
				Map.Entry<String, Object> msme = it.next() ; 
				System.out.println("\t|-" + "NewsKey=" + msme.getKey() + "、NewsValue=" + msme.getValue());
			}
		}
	}
	@Test
	public void testNewsList() throws Exception{
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll") ; 
		//System.out.println(newsList);
		Iterator<News> iter = newsList.iterator() ; 
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	@Test
	public void testNewsRemove() throws Exception{
		long nid = 1 ; 
		int len = MyBatisSessionFactory.getSession().delete("cn.mldn.mapping.NewsNS.doRemove",nid) ; 
		MyBatisSessionFactory.getSession().commit();
		TestCase.assertEquals(len, 1);
	}
	@Test
	public void testFindById() throws Exception{
		//查询数据并且直接以VO的类型返回
		News vo = MyBatisSessionFactory.getSession().selectOne("cn.mldn.mapping.NewsNS.findById",7L) ; 
		TestCase.assertNotNull(vo);
		System.out.println(vo);
	}
	@Test
	public void testNewsEdit() throws Exception{
		News vo = new News () ; 
		vo.setNid(2L);
		vo.setTitle("修改后的标题 - " + rand);
		vo.setPubdate(new Date());
		vo.setNote("修改后的报告 - " + rand );
		int len = MyBatisSessionFactory.getSession().update("cn.mldn.mapping.NewsNS.doEdit",vo) ;
		TestCase.assertEquals(len, 1);
		System.out.println(vo);
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
	}
	@Test
	public void testNewsAdd() throws Exception{
		News vo = new News() ; 
		vo.setTitle("标题 - " + rand);
		vo.setPubdate(new Date());
		vo.setNote("报告 - " + rand);
		int len = MyBatisSessionFactory.getSession().insert("cn.mldn.mapping.NewsNS.doCreate",vo) ;
		TestCase.assertEquals(len, 1);
		System.out.println(vo);
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
	}

}
