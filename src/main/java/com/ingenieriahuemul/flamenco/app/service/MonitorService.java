package com.ingenieriahuemul.flamenco.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamenco.app.dao.EstadoMasDao;
import com.ingenieriahuemul.flamenco.app.model.NotificationCloud;
import com.ingenieriahuemul.flamenco.app.model.dto.MasStatusDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.UsuariosAppDTO;

@Service
public class MonitorService {
	private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);

	@Autowired
	private EstadoMasDao estadoMasDao;
	
	@Autowired
	private NotificationCloud notificationCloud;
	
	public void run() {
		logger.info("Ejecuto procesamiento monitoreo de obtencion de estados de MAS");

		List<UsuariosAppDTO> listaUsuarios = estadoMasDao.obtenerUsuariosHabilitadosApp();
		
		enviosUsuariosValidosALaNube(listaUsuarios);
		
		enviosEstadosMasALaNube(listaUsuarios);
		
	}

	private void enviosUsuariosValidosALaNube(List<UsuariosAppDTO> listaUsuarios) {
		for (UsuariosAppDTO usuariosAppDTO : listaUsuarios) {
			logger.info(usuariosAppDTO.toString());
			notificationCloud.sendMessageUserValid(usuariosAppDTO);
		}
	}

	private void enviosEstadosMasALaNube(List<UsuariosAppDTO> listaUsuarios) {
		for (UsuariosAppDTO usuariosAppDTO : listaUsuarios) {
			
			List<MasStatusDTO> lista = estadoMasDao.obtenerEstadoActual();
			
			for (MasStatusDTO masStatus : lista) {
				logger.info(masStatus.toString());
				notificationCloud.sendMessage(masStatus, usuariosAppDTO.getEmpresa());
			}
		}
	}

}
