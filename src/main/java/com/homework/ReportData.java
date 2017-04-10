package com.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sasha on 09.04.17.
 */
public abstract class ReportData {
	private String responseMessage;
	protected Object[] columnNames;
	protected final List<Object> data;

	public ReportData() {
		this.data = new ArrayList<>();
		try {
			FillData();
			responseMessage = "Data collecting succeed.";
		}
		catch (ReportDataFillingException exc) {
			responseMessage = "Data collecting failed with message: " + exc.getMessage();
		}
		catch (Exception exc) {
			System.err.println(exc.getMessage()); exc.printStackTrace();
		}
	}

	//required implementation in subclasses (each report type)
	public abstract void FillData() throws ReportDataFillingException;

	public List<Object> getData() {
		return this.data;
	}
	public Object[] getColumnNames() {
		return this.columnNames;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}
}

