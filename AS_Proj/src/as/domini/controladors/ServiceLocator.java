package as.domini.controladors;

import java.util.HashMap;

import org.apache.ws.axis2.ServeiInformesSantitatClient;


public class ServiceLocator {

	private HashMap<String,Object> servicesCache;
    
    private static ServiceLocator instance;
    
    private ServiceLocator() {
        servicesCache = new HashMap<String,Object>();
        servicesCache.put("ServeiInformesSanitat", new ServeiInformesSantitatClient());
        
    }
    
    private synchronized static void createInstance() {
        if (instance == null) instance = new ServiceLocator();
    }
    
    public static ServiceLocator getInstance() {
        if (instance == null) createInstance();
        return instance;
    }
    
    public Object find(String serviceName) throws Exception{
            
		if (!servicesCache.containsKey(serviceName)){
			Object ob = new Object();
			servicesCache.put(serviceName, ob);
		}
		
		return servicesCache.get(serviceName);

    }

    
	
	
}