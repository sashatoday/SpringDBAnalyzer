package com.homework;

import com.homework.reports_data.*;

/**
 * Created by Sasha on 09.04.17.
 */
public class ReportFactory {

	public static ReportData CreateReport (String reportName) {
		switch (reportName)
		{
			case "AllDirectors":
				return new ReportAllDirectors();
			case "AllStorageGroups":
				return new ReportAllStorageGroups();
			case "ProblemDirectors":
				return new ReportProblemDirectors();
			case "ProblemIntervals":
				return new ReportProblemIntervals();
			case "SlowStorageGroups":
				return new ReportSlowStorageGroups();
			case "QueueLoading":
				return new ReportQueueLoading();
			case "StorageGroupLoading":
				return new ReportStorageGroupLoading();
			default:
				return new ReportData() {
					@Override
					public void FillData() {
						throw new ReportDataFillingException("Specified report name is incorrect: " + reportName);
					}
				};
		}
	}
}
