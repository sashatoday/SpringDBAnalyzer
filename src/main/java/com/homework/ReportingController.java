package com.homework;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sasha on 09.04.17.
 */
@RestController
public class ReportingController {

	@RequestMapping("/reporting")
	public ReportData reporting(@RequestParam(value="report") String reportName)
	{
		return ReportFactory.CreateReport(reportName);
	}
}
