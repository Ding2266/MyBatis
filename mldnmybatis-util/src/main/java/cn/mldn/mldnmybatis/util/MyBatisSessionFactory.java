package cn.mldn.mldnmybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSessionFactory {
	private static final String MYBATIS_CONFIG_FILE = "mybatis/mybatis.cfg.xml" ; //mybatis配置文件路径 
	private static final ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<SqlSession>() ; 
	private static SqlSessionFactory sessionFactory = null ; 
	private static InputStream inputStream = null ; //保存资源内容
	static { //编写静态代码块加载资源
		try {
			inputStream  = Resources.getResourceAsStream(MYBATIS_CONFIG_FILE) ;
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 获取SqlSessionFactory接口对象
	 * @return sqlSessionFactory实例化对象
	 */
	public static SqlSessionFactory getSessionFactory() {
		if(sessionFactory == null) {//现在没有连接对象
			createSessionFactory() ;  //创建连接对象
		}
		return sessionFactory ; 
	}
	/**
	 * 创建一个新的SqlSessionFactory接口对象
	 */
	private static void createSessionFactory() {
		sessionFactory  = new SqlSessionFactoryBuilder().build(inputStream) ; 
	}
	/**
	 * 获得一个SqlSession接口对象，用于进行书库库的操作
	 * @return SqlSession对象，不同的Session可以获得自己的SqlSession的对象
	 */
	public static SqlSession getSession() {
		SqlSession session = sqlSessionThreadLocal.get() ; //通过ThreadLocal获取session
		if(session == null) { //没有session
			if(sessionFactory == null) { //没有连接对象
				createSessionFactory() ; //创建连接对象
			}
			session = sessionFactory.openSession() ; //打开新的session
			sqlSessionThreadLocal.set(session);
		}
		return session ; 
	}
	public static void close() {
		SqlSession session = sqlSessionThreadLocal.get() ; 
		if(session != null) {
			session.close();
			sqlSessionThreadLocal.remove(); 
		}
	}
}
