package as.domini;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.dades.HibernateConfig;
import as.domini.controladors.CtrlDataFactoria;
import as.domini.controladors.CtrlEspecialitat;
import as.domini.controladors.CtrlHospital;
import as.domini.controladors.CtrlIngres;
import as.domini.controladors.CtrlMetge;
import as.main.Excepcions;

public class CU_AssignarMetgeAUnIngres {

	private List<TuplaMetge> metges;
	private Hospital hospital;
	private Especialitat especialitat;
	private CtrlDataFactoria cdf;
	private static CU_AssignarMetgeAUnIngres instancia;
	
	public static CU_AssignarMetgeAUnIngres getInstance() {
		if(instancia == null) instancia = new CU_AssignarMetgeAUnIngres();
		return instancia;
	}
	
	private CU_AssignarMetgeAUnIngres(){
		cdf = CtrlDataFactoria.getInstance();
	}
	
	public List<TuplaMetge> mostrarMetgesEspecialitat(String nomEsp, String nomHosp) throws Exception{
		
		CtrlHospital ctrlHosp = cdf.getCtrlHospital();
		hospital = ctrlHosp.getHospital(nomHosp);
		CtrlEspecialitat ctrlEsp = cdf.getCtrlEspecialitat();
		especialitat = ctrlEsp.getEspecialitat(nomEsp);		
		metges = hospital.getMetges(especialitat);
		if(metges.isEmpty()) throw new Exception(Excepcions.NO_METGES);
		return metges;
	}
	
	public void assignarMetgeAUnIngres(String dni, String nts, Date data) throws Exception{
		
		CtrlMetge ctrlMetge = cdf.getCtrlMetge();
		CtrlIngres ctrlIngres = cdf.getCtrlIngres();
		Metge m = ctrlMetge.getMetge(dni);
		Ingres i = ctrlIngres.getIngres(nts, data);
		System.out.println(i.getHospital().getNom() + " - " + i.getPkIngres().getPacient().getNom());
		i.setMetgeIngres(m, hospital);
		
		//Desem l'ingrés utilitzant hibernate.
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();		
		session.update(i);
		session.getTransaction().commit();
		session.flush();
	}
}
