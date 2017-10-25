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
