package com.homework;

import com.homework.hibernate.Analyzer;
import com.homework.hibernate.HibernateUtil;
import com.homework.hibernate.SlowSG;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Sasha on 09.04.17.
 */
public class Report1 extends ReportData {

	@Override
	public void FillData()
	{
		//data filling implementation
		Analyzer analyzer = new Analyzer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			/* 5. Find storagegroups that slow processing */
			SlowSG slowSG = analyzer.findSG(session);
			Iterator it = slowSG.GetContent().iterator();
			while(it.hasNext()) {
				Object[] data = (Object [])it.next();
				this.data.put((Integer)data[0], data[2]);
			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		HibernateUtil.shutdown();
	}



}
