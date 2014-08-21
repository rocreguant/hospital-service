package as.domini;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import as.main.Excepcions;

@Entity
public class Ingres {

	Hospital hospital;
	Habitacio habitacio;
	Metge metgeAssignat;
	
	private PKIngres pkIngres;
	private Date dataAlta;
	
	public Ingres() {
		
	}
	
	public Ingres(Pacient pacient, Date data) {
		pkIngres = new PKIngres();
		pkIngres.setData(data);
		pkIngres.setPacient(pacient);
		metgeAssignat = null;
	}

	@Id
	public PKIngres getPkIngres(){
		return pkIngres;
	}
	public void setPkIngres(PKIngres pk){
		pkIngres=pk;
	}
	
	@ManyToOne(targetEntity=Hospital.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="hospital_nom",nullable=true, insertable=false, updatable=false)
	public Hospital getHospital() {
		return hospital;
	}

	@ManyToOne(targetEntity=Habitacio.class, fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="hospital_nom",referencedColumnName="hospital_nom"),
			      @JoinColumn(name="num_habitacio",referencedColumnName="numHab")})
	public Habitacio getHabitacio() {
		return habitacio;
	}

	public void setHospital(Hospital h){
		hospital = h;
	}
	public void setHabitacio(Habitacio habitacio) {
		this.habitacio = habitacio;
	}

	public void associaIngres(Habitacio hab, Pacient p) throws Exception{
		boolean b = p.pacientIngressat();
		if(b) throw new Exception(Excepcions.PACIENT_INGRESSAT);
		habitacio = hab;
		pkIngres.setPacient(p);		
	}
	
	@Transient
	public String getNomHospital(){
		return hospital.getNom();
	}
	
	public void setNomHospital( String nom ){
		hospital.setNom( nom );
	}
	
	@Transient
	public int getNumHabitacio(){
		return habitacio.getNumHabitacio();
	}
	
	public void setNumHabitacio( int num ){
		habitacio.setNumHabitacio( num );
	}
	
	@ManyToOne
	@JoinColumn(name="metge_dni",nullable=true)
	public Metge getMetgeAssignat() {
		return metgeAssignat;
	}

	public void setMetgeAssignat(Metge metgeAssignat) {
		this.metgeAssignat = metgeAssignat;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public boolean donatDeAlta(){
		return (dataAlta!=null);
	}
	
	public boolean etsIngressat(){
		return !donatDeAlta();
	}

	public void setMetgeIngres(Metge m, Hospital hosp) throws Exception{

		if(dataAlta != null) throw new Exception(Excepcions.ALTA_INGRES);
		if(metgeAssignat != null) throw new Exception(Excepcions.INGRES_AMB_METGE);
		if(!hospital.getNom()
				.equals(hosp.getNom())) 
		if(m.getNomEspecialitat().equals(habitacio.getNomEspecialitat())) throw new Exception(Excepcions.NO_COINCIDEIXEN_ESPECIALITATS);
		metgeAssignat = m;
	}
	
}
