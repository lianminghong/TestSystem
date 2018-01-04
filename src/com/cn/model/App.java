package com.cn.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	private static SessionFactory sessionFactory;

	private void testSave() {
		User user = new User();
		user.setName("aaaa");

		// 读取hibernate.cfg.xml文件
		Configuration cfg = new Configuration().configure();

		sessionFactory = cfg.buildSessionFactory();

		Session session = null;
		try {
			// 打开session会话
			session = sessionFactory.openSession();
			// 开启事物
			session.beginTransaction();
			// 保存对象
			session.save(user);
			// 提交事物
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//发生异常回滚
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					// 关闭session
					session.close();
				}
			}
		}

	}

	public static void main(String[] args) {
		App app = new App();
		app.testSave();
		
		User user = new User();
		System.out.println(user.getName());
	}
}
