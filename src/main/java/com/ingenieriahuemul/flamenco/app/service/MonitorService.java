package com.ingenieriahuemul.flamenco.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamenco.app.dao.EstadoMasDao;
import com.ingenieriahuemul.flamenco.app.model.NotificationCloud;
import com.ingenieriahuemul.flamenco.app.model.dto.EmpresaDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.MasStatusDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.MessagePush;
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

		enviosUsuariosValidosALaNube();
		
		enviosEstadosMasALaNube();
		
		enviarNotificacionesPush();
		
		enviarValoresQR();
	}


	private void enviarValoresQR() {
		List<EmpresaDTO> listaEmpresas = obtenerListaDeEmpresas();
		
		for (EmpresaDTO empresaDTO : listaEmpresas) {
			logger.info(empresaDTO.toString());
			EmpresaDTO informacionEmpresa = estadoMasDao.obtenerInformacionEmpresa(empresaDTO.getId());
			notificationCloud.sendCodeQRForCompany(informacionEmpresa);
		}
	}


	private List<EmpresaDTO> obtenerListaDeEmpresas() {
		return estadoMasDao.traerTodasLasEmpresas();
	}


	private void enviosUsuariosValidosALaNube() {
		List<UsuariosAppDTO> listaUsuarios = estadoMasDao.obtenerUsuariosHabilitadosApp();
		
		for (UsuariosAppDTO usuariosAppDTO : listaUsuarios) {
			logger.info(usuariosAppDTO.toString());
			notificationCloud.sendMessageUserValid(usuariosAppDTO);
		}
	}

	private void enviosEstadosMasALaNube() {
		List<MasStatusDTO> lista = estadoMasDao.obtenerEstadoActual();
			
		for (MasStatusDTO masStatus : lista) {
			logger.info(masStatus.toString());
			
			notificationCloud.sendMessage(masStatus, masStatus.getIdEmpresa());
		}
		
	}
	
	private void enviarNotificacionesPush() {
		List<MessagePush> lista = estadoMasDao.obtenerMensajesPush();
		
		int orden = 0;
		
		for (MessagePush msjPush : lista) {
			logger.info(msjPush.toString());
			notificationCloud.sendNotificationPush(msjPush, orden);
			orden++;
		}
	}

}
