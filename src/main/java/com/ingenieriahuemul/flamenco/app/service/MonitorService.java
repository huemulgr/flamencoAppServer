package com.ingenieriahuemul.flamenco.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamenco.app.dao.EstadoMasDao;
import com.ingenieriahuemul.flamenco.app.model.MasStatus;

@Service
public class MonitorService {
	private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);

	@Autowired
	private EstadoMasDao estadoMasDao;

	public void run() {
		logger.info("Ejecuto procesamiento monitoreo de obtencion de estados de MAS");

		List<MasStatus> lista = estadoMasDao.obtenerEstadoActual();

		for (MasStatus masStatus : lista) {
			logger.info(masStatus.toString());
		}
	}

}
