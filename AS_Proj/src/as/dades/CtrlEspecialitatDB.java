package as.dades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.domini.Especialitat;
import as.domini.controladors.CtrlEspecialitat;
import as.main.Excepcions;

public class CtrlEspecialitatDB implements CtrlEspecialitat {

	public Especialitat getEspecialitat(String nomEspecialitat) throws Exception {
        SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Especialitat esp = (Especialitat) session.get(Especialitat.class, nomEspecialitat);	
		if (esp == null) throw new Exception(Excepcions.NO_ESPECIALITAT);
		session.getTransaction().commit();
		return esp;  
	}
	
}
