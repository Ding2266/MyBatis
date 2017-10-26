package cn.mldn.mldnmybatis.ssm.service.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnmybatis.ssm.service.INewsService;
import cn.mldn.mldnmybatis.ssm.vo.News;
import junit.framework.TestCase;

@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestNewsService {
	@Resource
	private INewsService newsService ;
	@Test
	public void testAdd() {
		News vo = new News() ; 
		vo.setTitle("新闻标题");
		vo.setNote("新闻内容");
		vo.setPubdate(new Date());
		TestCase.assertTrue(this.newsService.add(vo));
	}
	@Test
	public void testListFindByIds() {
		Set<Long> set = new HashSet<Long>() ;
		set.add(1L) ;
		set.add(2L) ; 
		TestCase.assertNotNull(this.newsService.listByIds(set));
	}
	@Test
	public void testList() {
		TestCase.assertNotNull(newsService.list(1L, 3, "note", "新闻"));
	}
}
