package com.homework;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sasha on 09.04.17.
 */
public abstract class ReportData {
	private String responseMessage;
	protected final Map<Integer, Object> data;

	public ReportData()
	{
		this.data = new HashMap<>();
		try { FillData(); responseMessage = "Data collecting succeed."; }
		catch (ReportDataFillingException exc) { responseMessage = "Data collecting failed with message: " + exc.getMessage(); }
		catch (Exception exc) { System.err.println(exc.getMessage()); exc.printStackTrace(); }
	}

	//required implementation in subclasses (each report type)
	public abstract void FillData() throws ReportDataFillingException;

	public Map<Integer, Object> getData()
	{
		return this.data;
	}

	public String getResponseMessage() {return this.responseMessage; }
}

