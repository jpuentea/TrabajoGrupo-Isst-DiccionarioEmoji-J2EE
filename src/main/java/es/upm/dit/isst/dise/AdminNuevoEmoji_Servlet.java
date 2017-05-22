package es.upm.dit.isst.dise;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.dise.dao.DISEDAO;
import es.upm.dit.isst.dise.dao.DISEDAOImpl;
import es.upm.dit.isst.dise.dao.PuntuacionDAO;
import es.upm.dit.isst.dise.dao.PuntuacionDAOImpl;
import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Puntuacion;
import es.upm.dit.isst.dise.model.Traduccion;

public class AdminNuevoEmoji_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String imagen = request.getParameter("imagen");
		String submit = request.getParameter("submit");
		Emoji emoji = null;
		DISEDAO dao = DISEDAOImpl.getInstancia();
		PuntuacionDAO daoRank = PuntuacionDAOImpl.getInstancia();

		emoji = dao.leerEmoji(imagen);

		if (submit.equals("Aceptar")) {
			emoji.setValidado(true);
			Puntuacion puntuacion = daoRank.leerPuntuacion(emoji.getAutor());
			int p = puntuacion.getPuntuacion();
			p += 50;
			puntuacion.setPuntuacion(p);
			daoRank.actualizarPuntuacion(puntuacion);
			dao.actualizarEmoji(emoji);
		} else {
			Puntuacion puntuacion = daoRank.leerPuntuacion(emoji.getAutor());
			int p = puntuacion.getPuntuacion();
			p -= 15;
			puntuacion.setPuntuacion(p);
			daoRank.actualizarPuntuacion(puntuacion);
			dao.borrarEmoji(emoji);
		}
		
		ArrayList<Emoji> emojis = new ArrayList<>();
		ArrayList<Emoji> emojis1 = new ArrayList<>();
		
		emojis1.addAll(dao.leerTodosEmojis());
		
		for(int i = 0; i<emojis1.size(); i++){
			if(!(emojis1.get(i).isValidado())){
				emojis.add(emojis1.get(i));
			}
		}

		RequestDispatcher view = request.getRequestDispatcher("NuevoEmojiAdmin.jsp");
		request.getSession().setAttribute("emojis", emojis);
		view.forward(request, response);
	}

}