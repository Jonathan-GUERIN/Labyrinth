package tests;
import java.net.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;

//Classe du serveur
public class server {
	public server(int port) throws IOException {
		
		//Connection au serveur
		ServerSocket ss = new ServerSocket(port);
		System.out.println("Test unitaire 1 - Entrée - En attente de connexion au serveur...");
		Socket sSocket = ss.accept();
		System.out.println("Test unitaire 1 - Sortie - Client connecté");
		
		//Variable permettant l'envoi de données au client
		OutputStream outputStream = sSocket.getOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		//Variable permettant la réception de données du client
		InputStream inputStream = sSocket.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		try {
	
			while (true) {
				// Réception de la photo par le serveur
				System.out.println("Test unitaire 3 - Entrée 1 - En attente de la réception d'une image...");
				
				//Réception de l'image
				BufferedImage plantImageBuffered = ImageIO.read(bufferedInputStream);
				bufferedInputStream.read(new byte[bufferedInputStream.available()], 0, bufferedInputStream.available());
				
				//Envoi de confirmation de réception de l'image
				bufferedWriter.write("Test unitaire 3 - Sortie 1 - Image reçue par le serveur. Envoi du nom...");
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				//Réception du nom de l'image
				String plantName = bufferedReader.readLine();
				
				//Envoi de confirmation de réception du nom de l'image
				bufferedWriter.write("Test unitaire 4 - Sortie - Nom de l'image reçue par le serveur.");
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				//Enregistrement de l'image
				System.out.println("Test unitaire 5 - Entrée - Enregistremen de l'image");
				File imageFile = new File("/home/romaindarous/PACT/pact63/projet/CCS/ccsProject/data/aliNoelServer/" + plantName);
				ImageIO.write(plantImageBuffered, "jpg", imageFile);
				plantImageBuffered.flush();
				System.out.println("Test unitaire 5 - Sortie - Image enregistrée dans le dossier aliNoelServer");
				
				//Envoi de confirmation au serveur
				bufferedWriter.write("Test unitaire 6 - Entrée/Sortie - Image enregistrée dans le dossier aliNoelServer");
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				

				
				
				
			}
		
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			bufferedWriter.close();
			sSocket.close();
			ss.close();
		}
	}
	
	
}