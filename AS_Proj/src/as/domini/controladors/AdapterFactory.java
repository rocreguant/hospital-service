package as.domini.controladors;


public class AdapterFactory {

	private static AdapterFactory instance;
	private IServeiInformeAdapter serveiInformeAdapter;
	
	
	 private static void createInstance() {
	        if (instance == null) instance = new AdapterFactory();
	    }
	
	 public static AdapterFactory getInstance() {
	        if (instance == null) createInstance();
	        return instance;
	    }
	
	 public IServeiInformeAdapter getServeiInformeAdapter() {
	        if (serveiInformeAdapter == null) serveiInformeAdapter = new ServeiInformeAdapter();
	        return serveiInformeAdapter;
	    }
}
