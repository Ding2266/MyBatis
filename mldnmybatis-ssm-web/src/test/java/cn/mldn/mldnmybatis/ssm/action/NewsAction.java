package cn.mldn.mldnmybatis.ssm.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mldn.mldnmybatis.ssm.service.INewsService;

@Controller
@RequestMapping("/pages/news/*")
public class NewsAction {
	@Resource
	private INewsService newsService ; 
	@ResponseBody
	@RequestMapping("news_list")
	public Object list() {
		return this.newsService.list(1L, 5, "title", "新闻") ;
	}
}
