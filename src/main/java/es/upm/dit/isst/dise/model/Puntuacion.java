package es.upm.dit.isst.dise.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class Puntuacion implements Serializable{
	@Index
	private int puntuacion;
	@Id
	private String usuario;
	@Index
	private ArrayList<String> votaciones;
	
	public Puntuacion() {}
	
	public Puntuacion(String usuario) {
		super();
		this.puntuacion = 0;
		this.usuario = usuario;
		this.votaciones= new ArrayList<>();
	}
	/**
	 * @return the votaciones
	 */
	public ArrayList<String> getVotaciones() {
		return votaciones;
	}

	/**
	 * @param votaciones the votaciones to set
	 */
	public void setVotaciones(ArrayList<String> votaciones) {
		this.votaciones = votaciones;
	}

	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Puntuacion [puntuacion=" + puntuacion + ", usuario=" + usuario + ", votaciones=" + votaciones + "]";
	}
	
	
}
