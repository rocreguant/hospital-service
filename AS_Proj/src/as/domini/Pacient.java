package as.domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import as.domini.controladors.AdapterFactory;
import as.main.Excepcions;

@Entity
@AttributeOverrides({
	@AttributeOverride(name="dni", column=@Column(name="dni")),
	@AttributeOverride(name="nom", column=@Column(name="nom"))
})
public class Pacient extends Persona{
	
	private String nTs;
	private String email;
	
	private List<Ingres> ingressos = new ArrayList<Ingres>();
	
	@Column(unique=true)
	public String getnTs() {
		return nTs;
	}
	public void setnTs(String nTs) {
		this.nTs = nTs;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany(targetEntity=Ingres.class, mappedBy="pkIngres.pacient"
			, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Column(nullable=true)
	public List<Ingres> getIngressos() {
		return ingressos;
	}
	public void setIngressos(List<Ingres> ingressos) {
		this.ingressos = ingressos;
	}

	public boolean pacientIngressat() throws Exception{
		boolean b = false;
		Iterator<Ingres> iterator = ingressos.iterator();
		while (iterator.hasNext() && !b) {
			b = iterator.next().donatDeAlta();
		}
		if(b) throw new Exception(Excepcions.PACIENT_INGRESSAT);
		return b;
	}
	
	public void enviarInformacio(TuplaInfo info) throws Exception{
		info.email = email;
		AdapterFactory.getInstance().getServeiInformeAdapter().enviarInforme(info);
	}

}
