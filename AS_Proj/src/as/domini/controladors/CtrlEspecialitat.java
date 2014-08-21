package as.domini.controladors;

import org.hibernate.Session;

import as.domini.Especialitat;


public interface CtrlEspecialitat {
	public Especialitat getEspecialitat(String nomEspecialitat) throws Exception;
}