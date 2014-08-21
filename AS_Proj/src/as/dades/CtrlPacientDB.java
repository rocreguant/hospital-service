package as.dades;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import as.domini.Pacient;
import as.domini.controladors.CtrlPacient;
import as.main.Excepcions;

public class CtrlPacientDB implements CtrlPacient{

	public Pacient getPacientDni(String dni) throws Exception {
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.getCurrentSession();
		Pacient p = (Pacient) session.get(Pacient.class, dni);
		if (p == null) throw new Exception(Excepcions.NO_PACIENT);
		return p;
	}
	
	public Pacient getPacientNts(String nts) throws Exception {
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction().begin();
		Criteria cr = session.createCriteria(Pacient.class);
		cr.add(Restrictions.like("nTs", nts));
		List<Pacient> ps = new ArrayList<Pacient>();
		ps = cr.list();

		if (ps.size() == 0) throw new Exception(Excepcions.NO_PACIENT);
		Pacient p = (Pacient) ps.get(0);
		session.getTransaction().commit();
		return p;
	}
}
