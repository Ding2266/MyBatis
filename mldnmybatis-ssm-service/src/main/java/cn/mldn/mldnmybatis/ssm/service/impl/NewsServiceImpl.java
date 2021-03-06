package cn.mldn.mldnmybatis.ssm.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.mldnmybatis.ssm.dao.INewsDAO;
import cn.mldn.mldnmybatis.ssm.service.INewsService;
import cn.mldn.mldnmybatis.ssm.util.service.abs.AbstractService;
import cn.mldn.mldnmybatis.ssm.vo.News;

@Service
public class NewsServiceImpl extends AbstractService implements INewsService {
	@Resource
	private INewsDAO newsDAO ; 
	@Override
	public boolean add(News vo) {
		return this.newsDAO.doCreate(vo);
	}

	@Override
	public List<News> listByIds(Set<Long> ids) {
		if(ids == null || ids.size() == 0) {
			return null ; 
		}
		return this.newsDAO.findByIds(ids.toArray());
	}

	@Override
	public List<News> list(long currentPage, int lineSize, String column, String keyWord) {
		return this.newsDAO.findSplit(super.paramToMap(currentPage, lineSize, column, keyWord));
	}

}
