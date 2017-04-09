package com.homework;

/**
 * Created by Sasha on 09.04.17.
 */
public class ReportFactory {

	public static ReportData CreateReport (String reportName)
	{
		switch (reportName)
		{
			case "Report1":
				return new Report1();
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
