package es.upm.dit.isst.dise.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Emoji implements Serializable {
	
	@Id
	private String imagen;
	
	@Index
	private String autor;
	
	@Index
	private ArrayList<Traduccion> traducciones = new ArrayList<Traduccion>();
	
	@Index
	private boolean validado;

	public Emoji() {}
	
	public Emoji(String imagen, String autor, ArrayList<Traduccion> traducciones, boolean validado) {
		super();
		this.imagen = imagen;
		this.autor = autor;
		this.traducciones = traducciones;
		this.validado = validado;
	}
	
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public ArrayList<Traduccion> getTraducciones() {
		return traducciones;
	}

	public void setTraducciones(ArrayList<Traduccion> traducciones) {
		this.traducciones = traducciones;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	@Override
	public String toString() {
		return "Emoji [imagen=" + imagen + ", autor=" + autor + ", traducciones=" + traducciones + ", validado="
				+ validado + "]";
	}

	
	
	
	
}
