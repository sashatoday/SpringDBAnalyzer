package com.homework;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sasha on 09.04.17.
 */
@RestController
public class ReportingController {

	@ModelAttribute
	public void setResponseHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	@RequestMapping(value = "/reporting", method = RequestMethod.GET, produces = "application/json")
	public ReportData reporting(@RequestParam(value="report") String reportName,
								@RequestParam(value="dirId", defaultValue = "") String directorId,
								@RequestParam(value="sgId", defaultValue = "") String storageGroupId) {
		return ReportFactory.CreateReport(reportName, directorId, storageGroupId);

	}
}
