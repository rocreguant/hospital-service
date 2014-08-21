package as.domini;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.dades.HibernateConfig;
import as.domini.controladors.CtrlDataFactoria;
import as.domini.controladors.CtrlHabitacio;
import as.domini.controladors.CtrlPacient;

public class CU_IngressarPacient {
	
	private CU_ConsultarHospitals cuch;
	private CU_AssignarMetgeAUnIngres cuam;
	
	private CtrlDataFactoria cdf;
	
	private String nomEspecialitat;
	private Date data;
	private String dniMetge;
	private String nts;
	private String nomHospital;
	private int numHabitacio;
	private static CU_IngressarPacient instancia;
	
	public static CU_IngressarPacient getInstance() {
		if(instancia == null) instancia = new CU_IngressarPacient();
		return instancia;
	}
	
	/*
	 * Creadora Ingressar Pacient
	 */
	private CU_IngressarPacient(){
		cuch = CU_ConsultarHospitals.getInstance();
		cuam = CU_AssignarMetgeAUnIngres.getInstance();
		cdf = CtrlDataFactoria.getInstance();
		SessionFactory factory = HibernateConfig.instance.getFactory();
		factory.getCurrentSession();
	}
	
	public List<TupleHospHab> obteHospitalsLliuresPerEspecialitat(String nomEsp) throws Exception{
		nomEspecialitat = nomEsp;
		return cuch.consultarHospitals(nomEsp);
	}

	public void crearIngres(String nomHosp, int numHab, String nts) throws Exception{
		
		//Obtenim els controladors de pacient i habitació
		CtrlPacient ctrlPacient = cdf.getCtrlPacient();
		CtrlHabitacio ctrlHabitacio = cdf.getCtrlHabitacio();
		Pacient p = ctrlPacient.getPacientNts(nts);
		Habitacio h = ctrlHabitacio.getHabitacio(nomHosp, numHab);

		data = new Date();
		
		//Donem d'alta l'ingrés
		h.altaIngres(nts, data, p);
		this.nts = nts;
		nomHospital = nomHosp;
		numHabitacio = numHab;
	}
	
	public List<TuplaMetge> mostraMetgesHospitalPerEspecialitat() throws Exception{
		return cuam.mostrarMetgesEspecialitat(nomEspecialitat, nomHospital);
	}
	
	public void assignarMetgeAIngres(String dni) throws Exception{

		cuam.assignarMetgeAUnIngres(dni, nts, data);
		dniMetge = dni;
	}
	
	public void enviarInformeIngres() throws Exception{

		CtrlPacient ctrlPacient= cdf.getCtrlPacient();
		Pacient p = ctrlPacient.getPacientNts(nts);
		TuplaInfo info = new TuplaInfo();
		info.dataAvui = new Date();
		info.dni = dniMetge;
		info.nomHosp = nomHospital;
		info.numHab = numHabitacio;
		info.nts = nts;
		info.nomEsp = nomEspecialitat;
		p.enviarInformacio(info);
		
	}

}
