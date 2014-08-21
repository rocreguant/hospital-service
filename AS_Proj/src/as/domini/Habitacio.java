package as.domini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import as.dades.HibernateConfig;


@Entity
public class Habitacio {

//crec que he fet bé de posar-lo serialitzable
	
	private Especialitat especialitat;
	private PKHabitacio pkHabitacio;
	private List<Ingres> ingressos = new ArrayList<Ingres>();
	
	public Habitacio(int numHabitacio, String nomHospital) {
		pkHabitacio = new PKHabitacio();
		pkHabitacio.setNumHab(numHabitacio);
		pkHabitacio.setNomHospital(nomHospital);
	}
	
	public Habitacio() {
		pkHabitacio = new PKHabitacio();
	}
	
	@Id
	public PKHabitacio getPkHabitacio(){
		return pkHabitacio;
	}
	
	public void setPkHabitacio(PKHabitacio pk){
		pkHabitacio = pk;
	}
	
	@ManyToOne
	@JoinColumn(name="especialitat")
	public Especialitat getEspecialitat() {
		return especialitat;
	}

	public void setEspecialitat(Especialitat especialitat) {
		this.especialitat = especialitat;
	}
	
	@OneToMany(targetEntity=Ingres.class, mappedBy="habitacio")
	@Column(nullable=true)
	public List<Ingres> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingres> ingressos) {
		this.ingressos = ingressos;
	}

	@Transient
	public Hospital getHospital() throws Exception{
		return pkHabitacio.getHospital();
	}
	
	public void setHospital( Hospital hosp ) {
		pkHabitacio.setHospital(hosp);
	}
	
	@Transient
	public String getNomEspecialitat(){
		return especialitat.getNom();
	}
	
	public void setNomEspecialitat( String esp ) {
		especialitat.setNom(esp);
	}
	
	public void altaIngres(String nts, Date data, Pacient p) throws Exception{
		Ingres ingres = new Ingres(p, data);
		ingres.associaIngres(this, p);
		
		//Afegim l'ingrés a la llista d'ingressos de l'habitació
		ingressos.add(ingres);
		
		//Persistim l'ingrés
		SessionFactory factory = HibernateConfig.instance.getFactory();
		Session session = factory.openSession();
		session.beginTransaction().begin();
		session.save(ingres);
		session.getTransaction().commit();
		session.flush();		
	}
	
	public boolean etsLliure(){
		boolean b = true;
		Iterator<Ingres> iterator = ingressos.iterator();
		while (iterator.hasNext() && b) {
			b = iterator.next().etsIngressat();
		}
		return b;
	}
	
	@Transient
	public Integer getNumHabitacio() {
		return pkHabitacio.getNumHab();
	}
	
	
	public void setNumHabitacio( Integer num ) {
		pkHabitacio.setNumHab( num );
	}
	

}
