package com.ingenieriahuemul.flamenco.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

//esta clase se va a usar de base para el acceso a datos, todas las clases de acceso a base con los SP la heredan y tienen este esquema:
//contienen constantes con los nombres de los SP y sus parametros, para cambiarlos en un solo lugar si es necesario
//contienen metodos de acceso que se encargan de llamar los SP con los parametros recibidos
//cargan los parametros en maps para llamar al metodo ejecutarStoredProcedure(), este se encarga de llamar el procedimiento 
//con los parametros cargados y mapear la salida a objetos

public class BaseDao {

	@Autowired
    protected JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
//  Se va a mapear clases a los resultados de los SP de select. Deben cumplir con tener constructor vacio y getter setters para 
//  cada parametro recibido, pero pueden tener otros atributos y metodos adicionalmente
//  recordatorio: las clases a mapear deben tener sus parametros con los nombres de las columnas retornadas x el SELECT, no las de la tabla. Deberia ser caseinsensitiv	
    
  /**
   * Utilitario para llamar stored procedure y mapear su salida a objetos. En caso de error se muestra la excepcion por log y por consola, 
   * incluido el singal y su mensaje
   * @param	storedProcedure nombre de SP a ejecutar
   * @param	inParams 		parametros de entrada IN e INOUT indistintamente. Podria venir un null o map vacio para SP sin IN. 
   * @param	outParams 		map donde guardar los parametros de salida OUT e INOUT producidos una vez ejecutado el SP. Su valor 
   * se obtiene a partir de su nombre en el SP pero lowercase (deberia ser posible hacerlo case insensitive en el futuro)
   * @param	clase			clase de las filas retornadas por el SP en caso que lo haga, si no sera null. La clase debe tener un 
   * constructor vacio y los get y set para columnas, asi hace mapeo automatico.
   * 
   * @returns	List<Object> con los resultados del SP, castear a List<clase>. Si no hubo resultados o el SP no los devolvia queda vacio.  
   * 
   */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List ejecutarStoredProcedure (String storedProcedure, 
  		Map<String, Object> inParams, Map<String, Object> outParams, 
  		Class clase) {
  	
	  	try {
	//  		jdbcTemplate.setResultsMapCaseInsensitive(true);
		    	SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
		    	call.withProcedureName(storedProcedure);
		    	
		    	
		    	if(clase != null) {			//validacion para utilizar el metodo cuando no se devuelven filas (abm u otros)
		    		call.returningResultSet(clase.toString(), new BeanPropertyRowMapper(clase));
		    	}
		    	
		    	Map<String, Object> out;
		    	if(inParams != null) {		//validacion para sp sin parametros de entrada (no hay ninguno por ahora)
		    		out = call.execute(inParams);    		
		    	} else {
		    		out = call.execute();
		    	}
		    	
		    	if(outParams != null) {		//validacion para sp sin parametros de salida
		    		outParams.clear();
		    	} else {
		    		outParams = new HashMap<String, Object>();
		    	}
		    	outParams.putAll(out);
		    	
		    	if(clase != null) {
		    		return (List<Object>) outParams.get(clase.toString());
		    	} else {
		    		return new ArrayList<Object>();
		    	}
	  	} catch (ArrayIndexOutOfBoundsException e) {
	  		logger.error("Faltan parametros al ejecutar el SP <" + storedProcedure + ">: " + e.getMessage() + "\n");
	  		e.printStackTrace();
	  	} catch (Exception e) {
	  		logger.error("Hubo un error al ejecutar el SP <" + storedProcedure + ">: " + e.getMessage() + "\n");
	  		e.printStackTrace();
	  	}
	  	return new ArrayList<Object>();
	}
}
