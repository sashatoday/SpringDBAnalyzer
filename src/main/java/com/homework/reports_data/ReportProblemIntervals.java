package com.homework.reports_data;

import com.homework.ReportData;
import com.homework.hibernate.Analyzer;
import com.homework.hibernate.HibernateUtil;
import com.homework.hibernate.query_results.ProblemIntervals;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.security.timestamp.TimestampToken;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by Sasha on 09.04.17.
 */
public class ReportProblemIntervals extends ReportData {

	@Override
	public void FillData() {
		Analyzer analyzer = new Analyzer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			columnNames = new Object[] {"start time"};
			ProblemIntervals intervals = analyzer.FindProblemIntervals(session);
			Iterator it = intervals.GetContent().iterator();
			while(it.hasNext()) {
				String data = new SimpleDateFormat("MM/dd HH:mm").format((Timestamp)it.next());
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
