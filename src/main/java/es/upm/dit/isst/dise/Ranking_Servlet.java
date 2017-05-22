package es.upm.dit.isst.dise;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dise.dao.PuntuacionDAO;
import es.upm.dit.isst.dise.dao.PuntuacionDAOImpl;
import es.upm.dit.isst.dise.model.Puntuacion;
import es.upm.dit.isst.dise.model.Traduccion;

public class Ranking_Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PuntuacionDAO daoRank = PuntuacionDAOImpl.getInstancia();
		ArrayList<Puntuacion> puntuaciones = new ArrayList<>();
		UserService userService = UserServiceFactory.getUserService();
		String user = "";
		Puntuacion puntuacion = null;
		
		puntuaciones.addAll(daoRank.leerTodasPuntuaciones());
		for (int y = 0; y < puntuaciones.size(); y++) {
			for (int x = y+1; x < puntuaciones.size(); x++) {
				Puntuacion m = puntuaciones.get(y);
				if (puntuaciones.get(x).getPuntuacion() > m.getPuntuacion()) {
					m = puntuaciones.remove(x);
					puntuaciones.add(y, m);
				}
			}
		}
		
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
			puntuacion = daoRank.leerPuntuacion(user);
		}
		
		req.getSession().setAttribute("puntuaciones", puntuaciones);
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("puntuacion", puntuacion);

		resp.sendRedirect("Ranking.jsp");
	}
}
