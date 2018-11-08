package com.ingenieriahuemul.flamenco.app.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ingenieriahuemul.flamenco.app.model.dto.EmpresaDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.MasStatusDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.MessagePush;
import com.ingenieriahuemul.flamenco.app.model.dto.UsuariosAppDTO;

@Service
public class NotificationCloud {
	private static final Logger logger = LoggerFactory.getLogger(NotificationCloud.class);
	
	private FileInputStream serviceAccount;
	private FirebaseOptions options;
	
	public NotificationCloud() {
		try {

			serviceAccount = new FileInputStream("/clave/serviceAccountKey-prod.json");

			options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://flamenco-1410a.firebaseio.com/").build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(MasStatusDTO masStatus, String codEmpresa) {

		// As an admin, the app has access to read and write all data, regardless of
		// Security Rules
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

		DatabaseReference usersRef = ref.child(codEmpresa);

		Map<String, Object> listMas = new HashMap<String, Object>();

		listMas.put("registro_" + masStatus.getOrden(), masStatus);

		// usersRef.setValueAsync(listMas);
		usersRef.updateChildrenAsync(listMas);

	}

	public void sendMessageUserValid(UsuariosAppDTO usuariosAppDTO) {
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("valid_users");

		DatabaseReference usersRef = ref.child(usuariosAppDTO.getEmpresa());

		Map<String, Object> listMas = new HashMap<String, Object>();

		listMas.put("mail_id_usuario_" + usuariosAppDTO.getIdUsuario(), usuariosAppDTO.getEmail());

		// usersRef.setValueAsync(listMas);
		usersRef.updateChildrenAsync(listMas);
	}

	public void sendNotificationPush(MessagePush msjPush, int orden) {

		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("notificacion");
	
		DatabaseReference usersRef = ref.child(msjPush.getIdEmpresa());

		Map<String, Object> listMas = new HashMap<String, Object>();
		
		msjPush.setTitulo("Notificacion Flamenco");
		
		listMas.put("mensaje_" + orden, msjPush);

		// usersRef.setValueAsync(listMas);
		usersRef.updateChildrenAsync(listMas);
	}

	public void sendCodeQRForCompany(EmpresaDTO informacionEmpresa) {
		logger.info(informacionEmpresa.toString());
		
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("lista_codigo_qr");
		
		DatabaseReference usersRef = ref.child(informacionEmpresa.getId());
		
		Map<String, Object> listQR = new HashMap<String, Object>();
		
		listQR.put("codigo_qr" , informacionEmpresa.getQr());
		
		// usersRef.setValueAsync(listMas);
		usersRef.updateChildrenAsync(listQR);
	}
}
