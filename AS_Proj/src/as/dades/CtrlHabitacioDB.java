package as.dades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.domini.Habitacio;
import as.domini.Hospital;
import as.domini.PKHabitacio;
import as.domini.controladors.CtrlHabitacio;
import as.main.Excepcions;

public class CtrlHabitacioDB implements CtrlHabitacio{

	public Habitacio getHabitacio(String nomHospital, int numHabitacio) throws Exception{
		try {
	    SessionFactory factory = HibernateConfig.instance.getFactory();
	    Session session = factory.openSession();
		session.beginTransaction();
		PKHabitacio clauHab = new PKHabitacio();
	    clauHab.setNomHospital(nomHospital);
	    clauHab.getHospital();
	    clauHab.setNumHab(numHabitacio);
	    Habitacio h = (Habitacio) session.get(Habitacio.class, clauHab);
		if (h == null) throw new Exception(Excepcions.NO_HABITACIO);
		session.getTransaction().commit();
		session.flush();
		return h;  
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

}
