package as.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import as.domini.CU_ConsultarHospitals;
import as.domini.Especialitat;
import as.domini.Habitacio;
import as.domini.Hospital;
import as.domini.Ingres;
import as.domini.PKHabitacio;
import as.domini.Pacient;
import as.domini.Sanitari;


@SuppressWarnings("deprecation")
public class TestDriver {


	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Habitacio.class);
		config.addAnnotatedClass(Hospital.class);
		config.configure("hibernate.cfg.xml");
		
		new SchemaExport(config).create(false, true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Hospital h1 = new Hospital();
		
		h1.setAdreça("Granvia de l'Hospitalet de Llobregat");
		h1.setDescripcio("Hospital universitari");
		h1.setNom("Hospital de Bellvitge");
		
		Especialitat e1 = new Especialitat();
		e1.setNom("Traumatología");
		List<Hospital> hs = new ArrayList<Hospital>();
		hs.add(h1);
		e1.setHospitals(hs);
		
		PKHabitacio pkh = new PKHabitacio();
		pkh.setHospital(h1);
		pkh.setNumHab(1);
		Habitacio hab1 = new Habitacio();
		hab1.setPkHabitacio(pkh);
		hab1.setEspecialitat(e1);
		
		Pacient p1 = new Pacient();
		p1.setDni("12345678Z");
		p1.setEmail("joan@mail.cat");
		p1.setNom("Joan");
		p1.setnTs("123");
		
		session.save(h1);
		session.save(e1);
		session.save(hab1);
		session.save(p1);
		
		
		
		
		
		
		// Creem instancies de les classes stub
		/*Ingres i1 = new Ingres();
		Ingres i2 = new Ingres();
		Especialitat e1 = new Especialitat();
		Especialitat e2 = new Especialitat();
		Sanitari s1 = new Sanitari();
		Sanitari s2 = new Sanitari();
		
		// Creem un hospital
		Hospital hosp1 = new Hospital();
		hosp1.setNom("Vall d'Hebron");
		hosp1.setAdreça("Passeig Vall d'Hebron 119-129, Barcelona");
		hosp1.setDescripcio("L'Hospital Universitari Vall d'Hebron és el complex hospitalari més gran de Catalunya i un dels més grans de l'Estat espanyol");
		List<Especialitat> espsHosp1 = new ArrayList<Especialitat>();
		espsHosp1.add(e1);
		espsHosp1.add(e2);
		hosp1.setEspecialitats(espsHosp1);
		List<Ingres> ings1 = new ArrayList<Ingres>();
		ings1.add(i1);
		hosp1.setIngresos(ings1);
		i1.setHospital(hosp1);
		List<Sanitari> sans1 = new ArrayList<Sanitari>();
		sans1.add(s1);
		sans1.add(s2);
		hosp1.setSanitaris(sans1);
		s1.setHospital(hosp1);
		s2.setHospital(hosp1);
		
		
		
		//Ara creem les habitacions
		//Primer creem la clau primaria d'habitació, que està formada per l'hospital i el num d'habitació
		
		// Habitació 1
		HabitacioPK habpk1 = new HabitacioPK();
		habpk1.setHospital(hosp1);
		habpk1.setNumero(1);
		Habitacio hab1 = new Habitacio();
		hab1.setId(habpk1);
		hab1.setEspecialitat(e1);
		hab1.setIngresos(ings1);
		i1.setHabitacio(hab1);
		
		// Habitació 2
		HabitacioPK habpk2 = new HabitacioPK();
		habpk2.setHospital(hosp1);
		habpk2.setNumero(2);
		Habitacio hab2 = new Habitacio();
		hab2.setId(habpk2);
		hab2.setEspecialitat(e2);
		List<Ingres> ings2 = new ArrayList<Ingres>();
		ings2.add(i2);
		hab2.setIngresos(ings2);
		i2.setHabitacio(hab2);
		
		//Donem visibilitat de les habitacions desde hospital
		List<Habitacio> habitacions1 = new ArrayList<Habitacio>();
		habitacions1.add(hab1);
		habitacions1.add(hab2);
		hosp1.setHabitacions(habitacions1);
		
		
		
		//Desem les dades a la sessió
		session.save(i1);
		session.save(i2);
		session.save(e1);
		session.save(e2);
		session.save(s1);
		session.save(s2);
		session.save(hosp1);
		session.save(hab1);
		session.save(hab2);*/
	
		
		//Ho desem a la BD
		session.getTransaction().commit();
		
		
		
		

	}

}
