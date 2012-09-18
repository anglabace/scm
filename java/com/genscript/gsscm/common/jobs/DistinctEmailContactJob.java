package com.genscript.gsscm.common.jobs;
 

import org.springframework.beans.factory.annotation.Autowired;
 
import com.genscript.gsscm.contact.service.ContactService;

/*
 * author zhou gang
 * 2011 07 20
 * 定时任务：删除customer里面businessEmail 与contact表中email 相同的 数据 即 将status 置为 
 */
public class DistinctEmailContactJob {
	@Autowired
	private ContactService contactService;

	public void execute() throws Exception{
		System.out.println("start this job..........");
		contactService.delContactByduplicateEmail2customer();
	}
	 
	 
}
