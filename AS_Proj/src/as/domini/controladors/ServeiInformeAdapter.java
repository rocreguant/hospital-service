package as.domini.controladors;

import org.apache.ws.axis2.ServeiInformesSantitatClient;

import as.domini.TuplaInfo;

public class ServeiInformeAdapter implements IServeiInformeAdapter{

	@Override
	public void enviarInforme(TuplaInfo info) throws Exception {

		ServeiInformesSantitatClient sisc = 
				(ServeiInformesSantitatClient) ServiceLocator.getInstance().find("ServeiInformesSanitat");
		
		sisc.enviarInformeIngres(info.nomEsp, info.dataAvui, info.nomHosp, 		  
								 info.numHab, info.nts, info.dni, info.email);
	}
	

}
