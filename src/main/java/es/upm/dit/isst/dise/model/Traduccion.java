package es.upm.dit.isst.dise.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Traduccion implements Serializable {
	
	@Id
	private String traduccion;
	
	@Index
	private String autor;
	
	private long votos;
	
	@Index
	private boolean validado; 
	
	
	public Traduccion() {}
	
	public Traduccion(String traduccion, String autor) {
		super();
		this.traduccion = traduccion;
		this.autor = autor;
		this.votos = 1;
		this.validado = false;
	}

	public String getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}

	public long getVotos() {
		return votos;
	}

	public void setVotos(long votos) {
		this.votos = votos;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Traduccion [traduccion=" + traduccion + ", autor=" + autor + ", votos=" + votos + ", validado="
				+ validado + "]";
	}


	
	
	
}
