package com.homework.reports_data;

import com.homework.ReportData;
import com.homework.hibernate.Analyzer;
import com.homework.hibernate.HibernateUtil;
import com.homework.hibernate.query_results.SlowStorageGroups;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;

/**
 * Created by Sasha on 09.04.17.
 */
public class ReportSlowStorageGroups extends ReportData {

	@Override
	public void FillData(String degenerateParam) {
		Analyzer analyzer = new Analyzer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			columnNames = new Object[] {"key", "id", "count failed intervals"};
			SlowStorageGroups slowSGs = analyzer.FindSlowStorageGroups(session);
			Iterator it = slowSGs.GetContent().iterator();
			while(it.hasNext()) {
				Object[] data = (Object [])it.next();
				this.data.add(data);
			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
