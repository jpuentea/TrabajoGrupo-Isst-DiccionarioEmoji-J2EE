package es.upm.dit.isst.dise.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Puntuacion;

public class PuntuacionDAOImpl implements PuntuacionDAO {

	private static PuntuacionDAOImpl instancia;

	private PuntuacionDAOImpl() {
	}

	public static PuntuacionDAOImpl getInstancia() {
		if (instancia == null)
			instancia = new PuntuacionDAOImpl();
		return instancia;
	}

	@Override
	public Puntuacion crearPuntuacion(String usuario) {
		Puntuacion puntuacion = new Puntuacion(usuario);
		
		ofy().save().entity(puntuacion).now();
		return puntuacion;
	}

	@Override
	public List<Puntuacion> leerTodasPuntuaciones() {
		List<Puntuacion> puntuaciones = ofy().load().type(Puntuacion.class).list();
		return puntuaciones;
	}

	@Override
	public Puntuacion leerPuntuacion(String usuario) {
		Puntuacion puntuacion = ofy().load().type(Puntuacion.class).filterKey(Key.create(Puntuacion.class, usuario)).first().now();
		return puntuacion;

	}

	@Override
	public Puntuacion actualizarPuntuacion(Puntuacion puntuacion) {
		ofy().save().entity(puntuacion).now();
		return puntuacion;
	}

	@Override
	public Puntuacion borrarPuntuacion(Puntuacion puntuacion) {
		ofy().delete().entity(puntuacion).now();
		return puntuacion;
	}

}
