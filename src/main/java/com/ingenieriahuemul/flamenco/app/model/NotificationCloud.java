package com.ingenieriahuemul.flamenco.app.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ingenieriahuemul.flamenco.app.model.dto.MasStatusDTO;
import com.ingenieriahuemul.flamenco.app.model.dto.UsuariosAppDTO;

@Service
public class NotificationCloud {

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
}
