package es.upm.dit.isst.dise.dao;

import java.util.List;

import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Traduccion;


public interface DISEDAO {
	
	// CREAR
	public Emoji crearEmoji(String imagen, String autor, String traduccionPorDefecto, boolean validado);
	
	// LEER
	// Todos los emojis
	public List<Emoji> leerTodosEmojis();
	// Dada la imagen obtener el emoji
	public Emoji leerEmoji(String imagen);
	// Para ver los tuys o de amigos
	public List<Emoji> leerEmojisPorAutor(String autor);
	// Para los que ve cualquier usuario (no admin)
	public List<Emoji> leerEmojisValidados();
	
	// Esto se hace en otro lado, en el servlet supongo
	/*
	// Para desempate en traduccion de texto a emoji. Devuelve tdas las traducciones de todos los emojis
	public List<Traduccion> leerTodasTraduccionesValidadas();
	// Devulve la primera traducciond de cada emoji (TODOS)
	public List<Traduccion> leerPrimerasTraducciones();
	// Para ver las traducciones de amigos o las tuyas
	//public List<Traduccion> leerTodasTraduccionesPorAutor(String autor);
	*/
	
	// ACTUALZAR
	public Emoji actualizarEmoji(Emoji emoji);	// añade una traduccion al emoji
	
	
	// BORRAR
	public Emoji borrarEmoji(Emoji emoji);
	
}
