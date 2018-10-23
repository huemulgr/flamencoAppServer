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

	public void sendMessage(MasStatusDTO masStatus) {

		// As an admin, the app has access to read and write all data, regardless of
		// Security Rules
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ROOT");

		DatabaseReference usersRef = ref.child("WlQ5D2xt5URTTSVcgCSNzzG5IMj2");

		Map<String, Object> listMas = new HashMap<String, Object>();

		listMas.put("registro_" + masStatus.getOrden(), masStatus);

		// usersRef.setValueAsync(listMas);
		usersRef.updateChildrenAsync(listMas);

	}
}
