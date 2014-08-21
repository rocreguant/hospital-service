package as.dades;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.domini.Ingres;
import as.domini.PKIngres;
import as.domini.Pacient;
import as.domini.controladors.CtrlDataFactoria;
import as.domini.controladors.CtrlIngres;
import as.domini.controladors.CtrlPacient;
import as.main.Excepcions;

public class CtrlIngresDB implements CtrlIngres{


	public Ingres getIngres(String nts, Date data) throws Exception {
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		PKIngres clauIngres = new PKIngres();
		clauIngres.setData(data);
		CtrlDataFactoria cdf = CtrlDataFactoria.getInstance();
		CtrlPacient ctrlPacient= cdf.getCtrlPacient();
		Pacient p = ctrlPacient.getPacientNts(nts);
		clauIngres.setPacient(p);
		Ingres i =	(Ingres) session.get(Ingres.class, clauIngres);
		session.evict(i);
		session.getTransaction().commit();
		if (i == null) throw new Exception(Excepcions.NO_INGRES);
		return i;
	}


}
