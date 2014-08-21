package org.apache.ws.axis2;



import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.AxisFault;

import org.apache.ws.axis2.ServeiInformesSanitatStub;
import org.apache.ws.axis2.ServeiInformesSanitatStub.EnviarInformeIngres;

import as.domini.controladors.ServiceLocator;

public class ServeiInformesSantitatClient {
	
	public void enviarInformeIngres(String nomEsp, Date dataAvui,
			String nomHosp, Integer numHab, String nTs, String dniMetge,
			String emailPacient) throws Exception {
		
		try {
			ServeiInformesSanitatStub stub = new ServeiInformesSanitatStub();

			EnviarInformeIngres ingres = new EnviarInformeIngres();
			
			SimpleDateFormat ft = 
				      new SimpleDateFormat ("yyyy-MM-dd");

			ingres.setNomEsp(nomEsp);
			ingres.setDataAvui(ft.format(dataAvui));
			ingres.setNomHosp(nomHosp);
			ingres.setNumHab(numHab);
			ingres.setNTs(nTs);
			ingres.setDniMetge(dniMetge);
			ingres.setEmailPac(emailPacient);
			
			stub.enviarInformeIngres(ingres);
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
}
