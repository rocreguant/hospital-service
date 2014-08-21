package as.domini;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Metge extends Sanitari{
	
	private String categoria;
	private Especialitat especialitat;
	private List<Ingres> ingresos = new ArrayList<Ingres>();

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@ManyToOne
	@JoinColumn(name="especialitat")
	public Especialitat getEspecialitat() {
		return especialitat;
	}

	public void setEspecialitat(Especialitat especialitat) {
		this.especialitat = especialitat;
	}
	
	@OneToMany(targetEntity=Ingres.class, mappedBy="metgeAssignat"
			, cascade=CascadeType.ALL)
    @Column(nullable=true)
	public List<Ingres> getIngresos() {
		return ingresos;
	}
	public void setIngresos(List<Ingres> ingresos) {
		this.ingresos = ingresos;
	}
	
	@Transient
	public String getNomEspecialitat() {
		return especialitat.getNom();
	}
	
	public void setNomEspecialitat( String esp ) {
		especialitat.setNom( esp );
	}
	
	
	

	

}
