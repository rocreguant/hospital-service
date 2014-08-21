package as.domini;


import javax.persistence.Entity;
import javax.persistence.Transient;


@Entity
public class Infermera extends Sanitari{
	
	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
	

	

}
