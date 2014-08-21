package as.domini.controladors;

import as.dades.CtrlEspecialitatDB;
import as.dades.CtrlHabitacioDB;
import as.dades.CtrlHospitalDB;
import as.dades.CtrlIngresDB;
import as.dades.CtrlMetgeDB;
import as.dades.CtrlPacientDB;

public class CtrlDataFactoria {

	private static CtrlDataFactoria instance;
	private static CtrlPacient ctrlPacient;
	private static CtrlMetge ctrlMetge;
	private static CtrlIngres ctrlIngres;
	private static CtrlHospital ctrlHospital;
	private static CtrlHabitacio ctrlHabitacio;
	private static CtrlEspecialitat ctrlEspecialitat;
	
	
	public static CtrlDataFactoria getInstance() {
        if (instance == null) {
            instance = new CtrlDataFactoria();
        }
        return instance;
    }
	
	
	public CtrlPacient getCtrlPacient() {
		if (ctrlPacient == null) {
			ctrlPacient = new CtrlPacientDB();
        }
		return ctrlPacient;
	}
	
	
	public CtrlMetge getCtrlMetge() {
		if (ctrlMetge == null) {
			ctrlMetge = new CtrlMetgeDB();
        }
		return ctrlMetge;
	}
	
	
	public CtrlIngres getCtrlIngres() {
		if (ctrlIngres == null) {
			ctrlIngres = new CtrlIngresDB();
        }
		return ctrlIngres;
	}
	
	
	public CtrlHospital getCtrlHospital() {
		if (ctrlHospital == null) {
			ctrlHospital = new CtrlHospitalDB();
        }
		return ctrlHospital;
	}
	
	
	public CtrlHabitacio getCtrlHabitacio() {
		if (ctrlHabitacio == null) {
			ctrlHabitacio = new CtrlHabitacioDB();
        }
		return ctrlHabitacio;
	}
	
	
	public CtrlEspecialitat getCtrlEspecialitat() {
		if (ctrlEspecialitat == null) {
			ctrlEspecialitat = new CtrlEspecialitatDB();
        }
		return ctrlEspecialitat;
	}
	
	
	
}
