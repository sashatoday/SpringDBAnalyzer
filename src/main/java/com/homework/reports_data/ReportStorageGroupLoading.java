package com.homework.reports_data;

import com.homework.ReportData;
import com.homework.ReportDataFillingException;
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

	public ReportStorageGroupLoading (String storageGroupId) {
		super(storageGroupId);
	}

	@Override
	public void FillData(String storageGroupId) {
		//check param
		if (storageGroupId == null || storageGroupId.equals(""))
			throw new ReportDataFillingException(
				"ReportStorageGroupLoading requires storageGroupId to be specified in the URL.");

		Analyzer analyzer = new Analyzer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			columnNames = new Object[] {"time, count6, count7"};
			StorageGroupLoading sgLoading = analyzer.GetSGLoading(session, storageGroupId);
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
