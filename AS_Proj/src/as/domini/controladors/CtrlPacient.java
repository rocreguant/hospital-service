package as.domini.controladors;

import as.domini.Pacient;


public interface CtrlPacient {
	public Pacient getPacientDni(String dni) throws Exception;
	public Pacient getPacientNts(String nts) throws Exception;
}
