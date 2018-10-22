package com.ingenieriahuemul.flamenco.app.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamenco.app.service.MonitorService;

@Component
public class ScheduledTasks {

	@Autowired
	private MonitorService monitorService;

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000) /* 5000 = Corre cada 5 min */
	public void reportCurrentTime() {
		logger.info("The time is now {}", dateFormat.format(new Date()));

		monitorService.run();

	}
}
