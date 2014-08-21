package as.dades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.domini.Metge;
import as.domini.controladors.CtrlMetge;
import as.main.Excepcions;

public class CtrlMetgeDB implements CtrlMetge{

	public Metge getMetge(String dni) throws Exception {
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Metge m = (Metge) session.get(Metge.class, dni);
		if (m == null) throw new Exception(Excepcions.NO_METGES);
		session.getTransaction().commit();
		return m;
	}

}
