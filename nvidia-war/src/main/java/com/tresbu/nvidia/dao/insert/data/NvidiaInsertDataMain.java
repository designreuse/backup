package com.tresbu.nvidia.dao.insert.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NvidiaInsertDataMain {

	private static ApplicationContext context = null;

	public static void main(String[] args) {

		try {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");

			NvidiaInsertDataWrapper mNvidiaInsertDataWrapper = (NvidiaInsertDataWrapper) context.getBean("mNvidiaInsertDataWrapper");

			System.out.println(mNvidiaInsertDataWrapper);
			
			mNvidiaInsertDataWrapper.insertNvidiaData();
		} finally {
			context = null;
		}
	}
}
