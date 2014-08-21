package as.domini.controladors;

import java.util.Date;

import as.domini.Ingres;

public interface CtrlIngres {
	public Ingres getIngres(String dni, Date data) throws Exception;
}
