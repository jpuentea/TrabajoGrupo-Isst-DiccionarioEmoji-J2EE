package es.upm.dit.isst.dise.dao;

import java.util.List;

import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Puntuacion;

public interface PuntuacionDAO {

	// CREAR
	public Puntuacion crearPuntuacion(String usuario);

	// LEER

	public List<Puntuacion> leerTodasPuntuaciones();

	public Puntuacion leerPuntuacion(String usuario);

	// ACTUALZAR
	public Puntuacion actualizarPuntuacion(Puntuacion puntuacion);

	// BORRAR
	public Puntuacion borrarPuntuacion(Puntuacion puntuacion);

}
