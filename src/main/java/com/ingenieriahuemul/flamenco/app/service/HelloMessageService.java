package com.ingenieriahuemul.flamenco.app.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloMessageService {

	private static final Logger logger = Logger.getLogger(HelloMessageService.class);

	@Value("${name:unknown}")
	private String name;

	public String getMessage() {
		return getMessage(name);
	}

	public String getMessage(String name) {
		
		logger.info("Ejecuto el servicio de monitoreo");
		
		int i = 0;
		
		while (i<10) {
				i++;
		}

		return "Hello " + name;

	}
}
