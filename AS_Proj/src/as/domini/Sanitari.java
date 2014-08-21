package as.domini;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@AttributeOverrides({
	@AttributeOverride(name="dni", column=@Column(name="dni")),
	@AttributeOverride(name="nom", column=@Column(name="nom"))
})
public class Sanitari extends Persona{
	
	private String codiEmpleat;
	private Hospital hospital;


	public String getCodiEmpleat() {
		return codiEmpleat;
	}

	public void setCodiEmpleat(String codiEmpleat) {
		this.codiEmpleat = codiEmpleat;
	}

	@ManyToOne(targetEntity=Hospital.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="hospital_nom")
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	

	@Transient
	public Especialitat getEspecialitat(){return null;}
	@Transient
	public String getCategoria(){return null;}
	


}
