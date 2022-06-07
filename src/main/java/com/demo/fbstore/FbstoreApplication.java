package com.demo.fbstore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@SpringBootApplication
public class FbstoreApplication {

	public static void main(String[] args) throws IOException {
		File f=new File(Objects.requireNonNull(FbstoreApplication.class.getClassLoader().getResource("firestoreServiceAccountKey.json")).getFile());
		FileInputStream serviceAccount=new FileInputStream(f.getAbsolutePath());
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://workinprogress-925b5.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);
		SpringApplication.run(FbstoreApplication.class, args);
	}

}
