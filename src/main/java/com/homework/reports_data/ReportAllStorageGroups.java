package com.homework.reports_data;

import com.homework.ReportData;
import com.homework.hibernate.Analyzer;
import com.homework.hibernate.HibernateUtil;
import com.homework.hibernate.db_model.DwdStoragegroup;
import com.homework.hibernate.query_results.AllStorageGroups;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;

/**
 * Created by Sasha on 09.04.17.
 */
public class ReportAllStorageGroups extends ReportData {

	@Override
	public void FillData() {
		Analyzer analyzer = new Analyzer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			columnNames = new Object[] {"key", "createdate", "id", "importkey"};
			AllStorageGroups storagegroups = analyzer.GetAllStorageGroups(session);
			Iterator it = storagegroups.GetContent().iterator();
			while(it.hasNext()) {
				this.data.add(it.next());
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
