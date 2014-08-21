package as.domini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import as.main.Excepcions;

@Entity
public class Hospital {
	
	private String nom;
	private String adreça;
	private String descripcio;
	private List<Habitacio> habitacions = new ArrayList<Habitacio>();
	private List<Especialitat> especialitats = new ArrayList<Especialitat>();
	private List<Sanitari> sanitaris = new ArrayList<Sanitari>();
	private List<Ingres> ingresos = new ArrayList<Ingres>();
	
	@Id
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdreça() {
		return adreça;
	}
	public void setAdreça(String adreça) {
		this.adreça = adreça;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	@OneToMany(targetEntity=Habitacio.class, mappedBy="pkHabitacio.hospital"
			, cascade=CascadeType.ALL)
	@Column(nullable=true)
	public List<Integer> getHabitacions() {
		List<Integer> habs = new ArrayList<Integer>();
		Iterator<Habitacio> iteratorHab = habitacions.iterator();
		while (iteratorHab.hasNext()) {
			Habitacio hab = iteratorHab.next();
			if(hab.etsLliure()){
				habs.add(hab.getNumHabitacio());
			}
		}
			
		return habs;
	}
	
	public void setHabitacions(List<Habitacio> habitacions) {
		this.habitacions = habitacions;
	}
	

	@ManyToMany
	@JoinTable(name="EspecialitatHospital", 
		joinColumns={@JoinColumn(name="hospital_nom")},
		inverseJoinColumns={@JoinColumn(name="especialitat_nom")})
	public List<Especialitat> getEspecialitats() {
		return especialitats;
	}
	public void setEspecialitats(List<Especialitat> especialitats) {
		this.especialitats = especialitats;
	}
	
	@OneToMany(targetEntity=Sanitari.class, mappedBy="hospital")
	public List<Sanitari> getSanitaris() {
		return sanitaris;
	}
	public void setSanitaris(List<Sanitari> sanitaris) {
		this.sanitaris = sanitaris;
	}
	
	@OneToMany(targetEntity=Ingres.class, mappedBy="hospital"
			, cascade=CascadeType.ALL)
    @Column(nullable=true)
	public List<Ingres> getIngresos() {
		return ingresos;
	}
	public void setIngresos(List<Ingres> ingresos) {
		this.ingresos = ingresos;
	}
	
	public List<TuplaMetge> getMetges(Especialitat esp) throws Exception{
		List<TuplaMetge> tuples = new ArrayList<TuplaMetge>();
		boolean b = false;
		Iterator<Especialitat> iteratorEsp = especialitats.iterator();
		while (iteratorEsp.hasNext() && !b) {
			b = (iteratorEsp.next().getNom().equals(esp.getNom()));
		}
		if(!b) throw new Exception(Excepcions.NO_COINCIDEIXEN_ESPECIALITATS);
		Iterator<Sanitari> iteratorSan = sanitaris.iterator();
		while (iteratorSan.hasNext()) {
			Sanitari san = iteratorSan.next();
			if(san.getEspecialitat().getNom().equals(esp.getNom())){
				TuplaMetge tupla = new TuplaMetge();
				tupla.nom = san.getNom();
				tupla.dni = san.getDni();
				tupla.categoria = san.getCategoria();
				tuples.add(tupla);
			}
		}
			
		return tuples;
	}

}
