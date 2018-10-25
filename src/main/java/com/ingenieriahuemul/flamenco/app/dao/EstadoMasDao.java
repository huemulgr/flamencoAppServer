package com.ingenieriahuemul.flamenco.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamenco.app.model.dto.MasStatusDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.UsuariosAppDTO;

@Component
public class EstadoMasDao extends BaseDao {

	private static final String CONSULTA_ESTADO_ACTUAL_APP = "flaEstadoActualApp";

	private static final String CONSULTA_USUARIOS_HABILITADOS_APP = "flaUsuariosHabilitadosApp";

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
}
