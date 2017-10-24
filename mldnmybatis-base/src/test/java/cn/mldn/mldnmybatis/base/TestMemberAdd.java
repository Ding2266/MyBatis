package cn.mldn.mldnmybatis.base;


import java.util.Date;
import java.util.Random;

import org.junit.Test;

import cn.mldn.mldnmybatis.util.MyBatisSessionFactory;
import cn.mldn.mldnmybatis.vo.Member;

public class TestMemberAdd {
	private static Random random = new Random() ; 
	private static int rand ; 
	static {
		rand = random.nextInt(Integer.MAX_VALUE) ; 
	}
	
	@Test
	public void testAddMember() throws Exception{
		Member vo = new Member() ; 
		vo.setMid("mldn - " + rand);
		vo.setName("你好 - " + rand);
		vo.setBirthday(new Date());
		vo.setSalary(8000.0);
		vo.setNote("是一个好人 - " + rand);
		//找到命名空间之中定义的具体SQL语句，而后执行追加
		System.out.println(MyBatisSessionFactory.getSession().insert("cn.mldn.mapping.MemberNS.doCreate",vo));
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
	}
}
