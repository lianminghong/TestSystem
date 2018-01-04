package com.cn.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	private static SessionFactory sessionFactory;

	private void testSave() {
		User user = new User();
		user.setName("aaaa");

		// ��ȡhibernate.cfg.xml�ļ�
		Configuration cfg = new Configuration().configure();

		sessionFactory = cfg.buildSessionFactory();

		Session session = null;
		try {
			// ��session�Ự
			session = sessionFactory.openSession();
			// ��������
			session.beginTransaction();
			// �������
			session.save(user);
			// �ύ����
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//�����쳣�ع�
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					// �ر�session
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
