package as.domini;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import as.domini.controladors.CtrlDataFactoria;

/*
 * Clau primaria d'habitació s'identifica per:
 * 
 * 			Hospital + Numero Habitació
 */

@Embeddable
public class PKHabitacio implements Serializable{
	private int numHab;
	private Hospital hospital;
	private String nomHospital;
	

	public int getNumHab(){
		return numHab;
	}
	
	@ManyToOne(targetEntity=Hospital.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="hospital_nom")
	public Hospital getHospital() throws Exception{
		if(hospital == null){
			hospital = CtrlDataFactoria.getInstance().getCtrlHospital().getHospital(nomHospital);
		}
		return hospital;
	}
	
	@Transient
	public String getNomHospital(){
		return nomHospital;
	}
	
	public void setNumHab(int num){
		numHab=num;
	}
	
	public void setHospital(Hospital hosp){
		hospital = hosp;
	}

	public void setNomHospital(String nomHospital) {
		hospital = null;
		this.nomHospital = nomHospital;
	}

}
