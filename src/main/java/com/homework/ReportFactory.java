package com.homework;

import com.homework.reports_data.*;

/**
 * Created by Sasha on 09.04.17.
 */
public class ReportFactory {

	public static ReportData CreateReport (String reportName, String dirId, String sgId) {
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
				return new ReportQueueLoading(dirId);
			case "StorageGroupLoading":
				return new ReportStorageGroupLoading(sgId);
			default:
				return new ReportData() {
					@Override
					public void FillData(String degenerateParam) {
						throw new ReportDataFillingException(
								"Specified report name is incorrect: " + reportName);
					}
				};
		}
	}
}
