package es.upm.dit.isst.dise;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import es.upm.dit.isst.dise.dao.DISEDAO;
import es.upm.dit.isst.dise.dao.DISEDAOImpl;
import es.upm.dit.isst.dise.dao.PuntuacionDAO;
import es.upm.dit.isst.dise.dao.PuntuacionDAOImpl;
import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Puntuacion;
import es.upm.dit.isst.dise.model.Traduccion;

public class ISST_DISE_Servlet extends HttpServlet {
	@Override
	public void init() throws ServletException { // Se ejecuta cuando se inicia
													// por primera vez el
													// servlet
		
		ObjectifyService.register(Emoji.class);
		ObjectifyService.register(Puntuacion.class);
		DISEDAO dao = DISEDAOImpl.getInstancia();
		PuntuacionDAO daoRank = PuntuacionDAOImpl.getInstancia();
		
		String[] traduccionesIniciales = {"Preocupado",
		                                  "Despreocupado",
		                                  "Llorón",
		                                  "Triste",
		                                  "Feliz",
		                                  "Compungido",
		                                  "Decepcionado",
		                                  "Alegre",
		                                  "Contento",
		                                  "Divertido",
		                                  "Desternillado de risa",
		                                  "Un beso",
		                                  "Un beso cariñoso",
		                                  "Besito",
		                                  "Chincha",
		                                  "¡Me encanta!",
		                                  "¿En serio?",
		                                  "Avergonzado",
		                                  "Adormilado",
		                                  "Nervioso",
		                                  "Abrumado",
		                                  "Despistado",
		                                  "Cansado",
		                                  "Hasta los huevos",
		                                  "Hasta la punta del pié",
		                                  "Cagada máxima",
		                                  "Alucinante",
		                                  "Cabreado",
		                                  "A punto de explotar",
		                                  "Enfadado",
		                                  "Asqueado",
		                                  "Risa",
		                                  "Simpático",
		                                  "Enfermo",
		                                  "Pro",
		                                  "Dormido",
		                                  "Muerto",
		                                  "Muerto con dentadura",
		                                  "Qué dices",
		                                  "Horrorizado",
		                                  "Impactado",
		                                  "Perverso",
		                                  "Enfadado",
		                                  "¡Oh!",
		                                  "Sonrisa forzada",
		                                  "Defraudado",
		                                  "Expectante",
		                                  "Mudo",
		                                  "Angel",
		                                  "Puto amo",
		                                  "Pokerface",
		                                  "Búlgaro",
		                                  "Árabe",
		                                  "Poli",
		                                  "Obrero",
		                                  "Guardia",
		                                  "Niño",
		                                  "Niña",
		                                  "Señor",
		                                  "Señora",
		                                  "Abuelo",
		                                  "Abuela",
		                                  "Rubio",
		                                  "Ángel",
		                                  "Princesa",
		                                  "Gato sonriente",
		                                  "Gato gracioso",
		                                  "Gato enamorado",
		                                  "Beso de gato",
		                                  "Gato picarón",
		                                  "Gato sorprendido",
		                                  "Gato llorando",
		                                  "Gato sonriendo",
		                                  "Gato cabreado",
		                                  "Demonio Oni",
		                                  "Demonio Tengu",
		                                  "Prefiero no mirar",
		                                  "Prefiero no escuchar",
		                                  "Prefiero no hablar",
		                                  "Calavera",
		                                  "Alien",
		                                  "Helado de chocolate",
		                                  "Calor",
		                                  "Chispas",
		                                  "Estrella",
		                                  "Estrella Fugaz",
		                                  "Choque",
		                                  "Enfado cómic",
		                                  "Sudando",
		                                  "Gota de agua",
		                                  "Durmiendo",
		                                  "Viento",
		                                  "Oreja",
		                                  "Ojos",
		                                  "Nariz",
		                                  "Lengua",
		                                  "Boca",
		                                  "OK",
		                                  "Mal",
		                                  "Perfecto",
		                                  "Puñetazo",
		                                  "Puño",
		                                  "Victoria",
		                                  "Hola",
		                                  "Alto",
		                                  "Palpar",
		                                  "Dedo arriba",
		                                  "Dedo abajo",
		                                  "Apuntar derecha",
		                                  "Apuntar izquierda",
		                                  "TOP",
		                                  "Rezar",
		                                  "Yo",
		                                  "Aplauso",
		                                  "Fuerza",
		                                  "Niño andando",
		                                  "Niño corriendo",
		                                  "¡OLE!",
		                                  "Pareja",
		                                  "Familia",
		                                  "Pareja chicos",
		                                  "Pareja chicas",
		                                  "Pareja besandose",
		                                  "Pareja enamorada",
		                                  "Baile",
		                                  "Sorprendida",
		                                  "Contigo no",
		                                  "Pija",
		                                  "Responder",
		                                  "Masaje cabeza",
		                                  "Cortarse el pelo",
		                                  "Pintarse las uñas",
		                                  "Casarse",
		                                  "Sorprendida",
		                                  "Triste",
		                                  "Estudiar",
		                                  "Sombrero de copa",
		                                  "Corona",
		                                  "Pamela",
		                                  "Deportiva",
		                                  "Náuticos",
		                                  "Sandalia",
		                                  "Tacones",
		                                  "Bota",
		                                  "Polo",
		                                  "Camisa con corbata",
		                                  "Camisa mujer",
		                                  "Vestido",
		                                  "Camiseta",
		                                  "Kimono",
		                                  "Bikini",
		                                  "Maletín",
		                                  "Bolso",
		                                  "Bolso de mano",
		                                  "Monedero rosa",
		                                  "Gafas",
		                                  "Lacito",
		                                  "Paraguas",
		                                  "Pintalabios",
		                                  "Corazón amarillo",
		                                  "Corazón azul",
		                                  "Corazón violeta",
		                                  "Corazón verde",
		                                  "Corazón",
		                                  "Corazón partido",
		                                  "Amor",
		                                  "Radioamor",
		                                  "Corazones unidos",
		                                  "Corazón brillante",
		                                  "Corazones locos",
		                                  "Flechazo de amor",
		                                  "Carta de amor",
		                                  "Beso",
		                                  "Anillo",
		                                  "Diamante",
		                                  "Persona",
		                                  "Gente",
		                                  "Charla",
		                                  "Huellas",
		                                  "Pensamiento",
		                                  "Bebé",
		                                  "Besito",
		                                  "Chincha",
		                                  "Sonrojado",
		                                  "Sonrisa forzada",
		                                  "Guiño",
		                                  "Coña"};
		
		int maximo = traduccionesIniciales.length-1;
		fuera:for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (i*100+j*10+k>maximo) break fuera;
					String imagen = "emojisIniciales/"+Integer.toString(i)+Integer.toString(j)+Integer.toString(k)+".png";
					dao.crearEmoji(imagen, "Developers", traduccionesIniciales[i*100+j*10+k], true);
				}
			}
		}
		
	}
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		
		DISEDAO dao = DISEDAOImpl.getInstancia();
		PuntuacionDAO daoRank = PuntuacionDAOImpl.getInstancia();
		Puntuacion  puntuacion = null;
			
		if (request.getUserPrincipal() != null) {
			
			user = request.getUserPrincipal().getName();
			if(daoRank.leerPuntuacion(user) == null){
				puntuacion = daoRank.crearPuntuacion(user);
			}
			else{
				puntuacion = daoRank.leerPuntuacion(user);
			}
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "Logout";
		}
		ArrayList<Emoji> emojis = new ArrayList<>();
		ArrayList<Emoji> emojis1 = new ArrayList<>();
		
		emojis1.addAll(dao.leerTodosEmojis());
		
		for(int i = 0; i<emojis1.size(); i++){
			if(emojis1.get(i).isValidado()){
				emojis.add(emojis1.get(i));
			}
		}
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("url", url);
		request.getSession().setAttribute("urlLinktext", urlLinktext);
		request.getSession().setAttribute("emojis", emojis);
		request.getSession().setAttribute("puntuacion", puntuacion);
		
		request.getSession().setAttribute("textoFinal", "Aquí aparecerá la traducción");
		
		
		RequestDispatcher view = request.getRequestDispatcher("TraducirVista.jsp");
		view.forward(request, response);
		
	}
	
	
	
	

}
