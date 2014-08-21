package as.dades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.domini.Hospital;
import as.domini.controladors.CtrlHospital;
import as.main.Excepcions;

public class CtrlHospitalDB implements CtrlHospital{


	public Hospital getHospital(String nomHospital) throws Exception {
	
	    SessionFactory factory = HibernateConfig.instance.getFactory();
	    Session session = factory.getCurrentSession();
		session.beginTransaction();
		Hospital hosp = (Hospital) session.get(Hospital.class, nomHospital);
		if (hosp == null) throw new Exception(Excepcions.NO_HOSPITALS);
		return hosp;
	}
}
