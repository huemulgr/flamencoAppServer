package com.ingenieriahuemul.flamenco.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamenco.app.model.MasStatus;

@Component
public class EstadoMasDao extends BaseDao {

	private static final String CONSULTA_ESTADO_ACTUAL_APP = "flaEstadoActualApp";

	public List<MasStatus> obtenerEstadoActual() {

		Map<String, Object> in = new HashMap<String, Object>();
		List<MasStatus> lista = (List<MasStatus>) super.ejecutarStoredProcedure(CONSULTA_ESTADO_ACTUAL_APP, in, null,
				MasStatus.class);

		return lista;
	}

}
