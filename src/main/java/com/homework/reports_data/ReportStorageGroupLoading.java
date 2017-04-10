package com.homework.reports_data;

import com.homework.ReportData;
import com.homework.hibernate.Analyzer;
import com.homework.hibernate.HibernateUtil;
import com.homework.hibernate.query_results.StorageGroupLoading;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by Sasha on 10.04.17.
 */
public class ReportStorageGroupLoading extends ReportData {

	@Override
	public void FillData() {
		Analyzer analyzer = new Analyzer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			columnNames = new Object[] {"time, count6, count7"};
			String sgId = "VHDC2DBESX_SG";
			StorageGroupLoading sgLoading = analyzer.GetSGLoading(session, sgId);
			Iterator it = sgLoading.GetContent().iterator();
			while(it.hasNext()) {
				Object[] data = (Object [])it.next();
				data[0] = new SimpleDateFormat("MM/dd HH:mm").format((Timestamp)data[0]);
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
