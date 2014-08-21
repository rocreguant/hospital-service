package as.dades;


import org.hibernate.SessionFactory;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import as.domini.Especialitat;
import as.domini.Habitacio;
import as.domini.Hospital;
import as.domini.Ingres;
import as.domini.Metge;
import as.domini.Pacient;
import as.domini.Persona;

public enum HibernateConfig {
	
	
	instance;
	
	private SessionFactory factory;

	@SuppressWarnings("deprecation")
	private HibernateConfig() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();	
		configuration.configure("hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();
	}

	
	public SessionFactory getFactory() {
		return factory;
	}
	
}