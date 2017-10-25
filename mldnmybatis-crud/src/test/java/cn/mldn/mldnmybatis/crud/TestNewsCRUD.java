package cn.mldn.mldnmybatis.crud;

import java.util.Date;
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
