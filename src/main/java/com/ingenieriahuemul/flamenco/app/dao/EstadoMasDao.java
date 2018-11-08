package com.ingenieriahuemul.flamenco.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamenco.app.model.dto.EmpresaDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.MasStatusDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.MessagePush;
import com.ingenieriahuemul.flamenco.app.model.dto.UsuariosAppDTO;

@Component
public class EstadoMasDao extends BaseDao {

	private static final String CONSULTA_ESTADO_ACTUAL_APP = "flaEstadoActualApp";
	private static final String CONSULTA_USUARIOS_HABILITADOS_APP = "flaUsuariosHabilitadosApp";
	private static final String CONSULTA_OBTENER_NOTIFICACIONES = "flaObtenerNotificaciones";

	private static final String CONSULTA = "flaEmpresaSele";
	private static final String P_ID_EMPRESA = "PidEmpresa";
	
	public List<MasStatusDTO> obtenerEstadoActual() {

		Map<String, Object> in = new HashMap<String, Object>();
		List<MasStatusDTO> lista = (List<MasStatusDTO>) super.ejecutarStoredProcedure(CONSULTA_ESTADO_ACTUAL_APP, in,
				null, MasStatusDTO.class);

		return lista;
	}

	public List<UsuariosAppDTO> obtenerUsuariosHabilitadosApp() {

		Map<String, Object> in = new HashMap<String, Object>();
		List<UsuariosAppDTO> lista = (List<UsuariosAppDTO>) super.ejecutarStoredProcedure(
				CONSULTA_USUARIOS_HABILITADOS_APP, in, null, UsuariosAppDTO.class);

		return lista;
	}

	public List<EmpresaDTO> traerTodasLasEmpresas() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, 0);
		return (List<EmpresaDTO>) super.ejecutarStoredProcedure(CONSULTA, in, null, EmpresaDTO.class);
	}

	public EmpresaDTO obtenerInformacionEmpresa(String idEmpresa) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, idEmpresa);
		return ((List<EmpresaDTO>) super.ejecutarStoredProcedure(CONSULTA, in, null, EmpresaDTO.class)).get(0);
	}

	public List<MessagePush> obtenerMensajesPush() {
		Map<String, Object> in = new HashMap<String, Object>();
		List<MessagePush> lista = (List<MessagePush>) super.ejecutarStoredProcedure(CONSULTA_OBTENER_NOTIFICACIONES, in,
				null, MessagePush.class);

		return lista;
	}
}
