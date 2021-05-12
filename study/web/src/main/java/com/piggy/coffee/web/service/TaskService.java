package com.piggy.coffee.web.service;

import java.io.File;

import org.springframework.stereotype.Component;

import com.piggy.coffee.common.util.io.FileUtils;
import com.piggy.coffee.web.cst.Csts;
import com.piggy.coffee.web.model.Task;

@Component
public class TaskService {

	public void createTask(Task task) {
		// 生成文件
		int id = 1;

		String configDir = Csts.TASK_DATA_ROOT + File.separator + id + File.separator + "config" + File.separator
				+ "fuzzing.yml";
		File file = new File(configDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileUtils.writeFile(file, task.getConfig());

		//写 fireware文件
		String firewareDir = Csts.TASK_DATA_ROOT + File.separator + id + File.separator + "config" + File.separator
				+ "fireware";
		
		
		

	}
	
	
	
}
