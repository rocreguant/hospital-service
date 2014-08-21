package as.domini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.dades.HibernateConfig;
import as.main.Excepcions;

@Entity
public class Especialitat {

	private String nom;
	private List<Hospital> hospitals = new ArrayList<Hospital>();
//	private List<Habitacio> habitacions = new ArrayList<Habitacio>();
//no s'usaba	
	
	@Id
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@ManyToMany
	@JoinTable(name="EspecialitatHospital", 
		joinColumns={@JoinColumn(name="especialitat_nom",nullable=true)},
		inverseJoinColumns={@JoinColumn(name="hospital_nom")})
	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}
	

	@Transient
	public List<TupleHospHab> getHabitacions() throws Exception{
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		List<TupleHospHab> tuples = new ArrayList<TupleHospHab>();
		
		//Hibernate.initialize(hospitals);
		this.getHospitals();
		Iterator<Hospital> iteratorHosp = hospitals.iterator();
		while (iteratorHosp.hasNext()) {
			Hospital hosp = iteratorHosp.next();
			List<Integer> hab = hosp.getHabitacions();
			if(hab.size()>0){
				TupleHospHab tupla = new TupleHospHab();
				tupla.nom = hosp.getNom();
				tupla.adreca = hosp.getAdreça();
				tupla.descripcio = hosp.getDescripcio();
				tupla.habitacions = hab; 
				tuples.add(tupla);
			}
		}
		session.getTransaction().commit();
		if (tuples.isEmpty()) throw new Exception(Excepcions.NO_HOSPITALS); 
		return tuples;
	}

}
