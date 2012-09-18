package com.genscript.gsscm.common.jobs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.purchase.service.ReadReceiveXmlService;

public class LoadReveiceXMLJob {

	@Autowired
	private ReadReceiveXmlService readReceiveXmlService;
	@Autowired
	private FileService fileService;
	public void execute() throws Exception{
		System.out.println("c:/tmp/ship/");
		try {
			readReceiveXmlService.download(fileService.getUploadPath()+"ship/", "shipt/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
