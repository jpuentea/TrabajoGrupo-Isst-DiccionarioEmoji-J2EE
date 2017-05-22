package es.upm.dit.isst.dise.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Traduccion;

import static com.googlecode.objectify.ObjectifyService.ofy;


public class DISEDAOImpl implements DISEDAO {
	
	private static DISEDAOImpl instancia;
	
	private DISEDAOImpl() {}
	
	public static DISEDAOImpl getInstancia() {
		if (instancia == null)
			instancia = new DISEDAOImpl();
		return instancia;
	}
	
	@Override
	public Emoji crearEmoji(String imagen, String autor, String traduccionPorDefecto, boolean validado) {
		ArrayList<Traduccion> traducciones = new ArrayList<Traduccion>();
		Traduccion traduccion = new Traduccion(traduccionPorDefecto, autor);
		traduccion.setValidado(true);
		traducciones.add(traduccion);
		Emoji emoji = new Emoji(imagen, autor, traducciones, validado);
		
		ofy().save().entity(emoji).now();
		return emoji;
	}

	@Override
	public List<Emoji> leerTodosEmojis() {
		List<Emoji> emojis = ofy().load().type(Emoji.class).list();
		return emojis;
	}

	@Override
	public Emoji leerEmoji(String imagen){
		Emoji emoji = ofy().load().type(Emoji.class).filterKey(Key.create(Emoji.class, imagen)).first().now();
		return emoji;
	}

	@Override
	public List<Emoji> leerEmojisPorAutor(String autor) {
		List<Emoji> emojis = ofy().load().type(Emoji.class).filter("autor", autor).list();
		return emojis;
	}
	
	@Override
	public List<Emoji> leerEmojisValidados() {
		List<Emoji> emojis = ofy().load().type(Emoji.class).filter("validado", true).list();
		return emojis;
	}

	@Override
	public Emoji actualizarEmoji(Emoji emoji) {
		ofy().save().entity(emoji).now();
		return emoji;
	}

	@Override
	public Emoji borrarEmoji(Emoji emoji) {
		ofy().delete().entity(emoji).now();
		return emoji;
	}



}
