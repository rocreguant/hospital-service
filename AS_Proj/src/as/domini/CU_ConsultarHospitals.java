package as.domini;

import java.util.List;

import org.hibernate.Hibernate;

import as.domini.controladors.CtrlDataFactoria;
import as.domini.controladors.CtrlEspecialitat;

public class CU_ConsultarHospitals {

	private List<TupleHospHab> result;
	private static CU_ConsultarHospitals instancia;
	
	public static CU_ConsultarHospitals getInstance() {
		if(instancia == null) instancia = new CU_ConsultarHospitals();
		return instancia;
	}
	
	private CU_ConsultarHospitals() {
		
	}
	
	public List<TupleHospHab> consultarHospitals(String nomEsp) throws Exception{
		CtrlDataFactoria cdf = CtrlDataFactoria.getInstance();
		CtrlEspecialitat ctrlEsp = cdf.getCtrlEspecialitat();
		Especialitat esp = ctrlEsp.getEspecialitat(nomEsp);
		result = esp.getHabitacions();
		return result; 
	}
}
