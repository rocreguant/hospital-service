package as.domini;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import as.domini.controladors.CtrlDataFactoria;


/*
 * Clau primaria d'ingrés s'identifica per:
 * 
 * 			DataInici + pacient
 */


@Embeddable
public class PKIngres implements Serializable{
	private Date data; //datainici
	private Pacient pacient;
	private String nts;
	
	public Date getData(){
		return data;
	}

	@ManyToOne(targetEntity=Pacient.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="pacient_dni")
	public Pacient getPacient() throws Exception{
		if(pacient == null){
			pacient = CtrlDataFactoria.getInstance().getCtrlPacient().getPacientNts(nts);
		}
		return pacient;
	}
	
	@Transient
	public String getNts(){
		return nts;
	}
	public void setData(Date data){
		this.data=data;
	}
	public void setPacient(Pacient pacient){
		this.pacient=pacient;
	}
	public void setNts(String nts) {
		pacient = null;
		this.nts=nts;
	}
}
