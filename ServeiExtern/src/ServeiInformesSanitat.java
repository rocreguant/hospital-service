

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.naming.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServeiInformesSanitat {

	public void enviarInformeIngres(String nomEsp, Date dataAvui,
			String nomHosp, Integer numHab, String nTs, String dniMetge,
			String emailPac) throws NamingException {

		/* Configuració de la conexió amb gmail */
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");		// Nom del host del correu
		props.setProperty("mail.smtp.starttls.enable", "true"); 	// TLS si està disponible
		props.setProperty("mail.smtp.port", "587");					// Port de gmail per enviar correu
		props.setProperty("mail.smtp.user", "exempleAS@gmail.com");	// Nom del usuari, en aquest cas la nostra
		props.setProperty("mail.smtp.auth", "true");				// Si requereix o no autentificacio

		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);									// Debug Report desactivat
		
		SimpleDateFormat ft = 
			      new SimpleDateFormat ("dd/MM/yyyy");


		/* Missatge */
		
		String msgAux = "";
		String msgBody = "Benvolgut pacient " + nTs + "\n" + "\n"
				+ "El seu ingrés s'ha efectuat correctament a data d'avui: "
				+ ft.format(dataAvui) + "\n"
				+ "Les dades del seu ingrés són les següents: \n"
				+ "Hospital: " + nomHosp + "\n" + "Nº Habitació: " + numHab
				+ "\n" + "Especialitat del tractament: " + nomEsp + "\n";

		if (dniMetge != null)
			msgAux = "DNI Metge Assignat: " + dniMetge + "\n";
		msgBody += (msgAux);

		msgBody += ("\n" + "Aquest missatge ha estat autogenerat, si teniu qualsevol dubte, si us plau contacteu amb la seguent direcció de correu electrònic: "
				+ nomHosp + "@sanitat.com");

		/* Enviament */
		
		try {
			Message msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailPac));
			msg.setSubject("Informe de l'Ingrés a " + nomHosp);
			msg.setText(msgBody);
			Transport t = session.getTransport("smtp");
			t.connect("exempleAS@gmail.com", "exempleASlliurament4");
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
