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
import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Traduccion;

public class NuevaTraduccion_Servlet extends HttpServlet {
	//public String seleccionado;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String imagen = req.getParameter("imagen");
		String traduccion1 = req.getParameter("nuevaTraduccion");
		String user = req.getUserPrincipal().getName();
		Traduccion traduccion = new Traduccion(traduccion1, user);
		Emoji emoji = null;
		
		DISEDAO dao = DISEDAOImpl.getInstancia();
		emoji = dao.leerEmoji(imagen);
		ArrayList<Traduccion> array = new ArrayList<Traduccion>();
		array.addAll(emoji.getTraducciones());
		array.add(traduccion);
		
		emoji.setTraducciones(array);
		dao.actualizarEmoji(emoji);
		
		req.getSession().setAttribute("emoji", emoji);
		
		RequestDispatcher view = req.getRequestDispatcher("Votacion.jsp");
		view.forward(req, resp);
	}
}