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

import es.upm.dit.isst.dise.dao.DISEDAO;
import es.upm.dit.isst.dise.dao.DISEDAOImpl;
import es.upm.dit.isst.dise.dao.PuntuacionDAO;
import es.upm.dit.isst.dise.dao.PuntuacionDAOImpl;
import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Puntuacion;
import es.upm.dit.isst.dise.model.Traduccion;

public class VotarTraducciones_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String imagen = request.getParameter("imagen");
		int traduccion = Integer.parseInt(request.getParameter("n"));
		UserService userService = UserServiceFactory.getUserService();
		String user = request.getUserPrincipal().getName();

		Emoji emoji = null;
		DISEDAO dao = DISEDAOImpl.getInstancia();
		PuntuacionDAO daoRank = PuntuacionDAOImpl.getInstancia();
		emoji = dao.leerEmoji(imagen);
		boolean yaVotada = false;
		Puntuacion puntuacion = daoRank.leerPuntuacion(user);
		ArrayList<String> votadas = new ArrayList<>();
		if (puntuacion.getVotaciones() != null) {
			votadas.addAll(puntuacion.getVotaciones());
		}
		for (int i = 0; i < votadas.size(); i++) {
			ArrayList<Traduccion> array = new ArrayList<Traduccion>();
			array.addAll(emoji.getTraducciones());
			if (votadas.get(i).equals(array.get(traduccion).getTraduccion())) {
				yaVotada = true;

			}
		}

		if (!yaVotada) {
			votadas.add(emoji.getTraducciones().get(traduccion).getTraduccion());
			puntuacion.setVotaciones(votadas);
			daoRank.actualizarPuntuacion(puntuacion);
			Puntuacion puntuacion1 = daoRank.leerPuntuacion(user);
			int p = puntuacion1.getPuntuacion();
			p += 5;
			puntuacion1.setPuntuacion(p);
			daoRank.actualizarPuntuacion(puntuacion1);
			ArrayList<Traduccion> array = new ArrayList<Traduccion>();
			array.addAll(emoji.getTraducciones());
			long n = array.get(traduccion).getVotos();
			n++;
			array.get(traduccion).setVotos(n);

			for (int x = 1; x < array.size(); x++) {
				Traduccion m = array.get(0);
				if (array.get(x).getVotos() > m.getVotos()) {
					m = array.remove(x);
					array.add(0, m);
				}
			}
			emoji.setTraducciones(array);
			dao.actualizarEmoji(emoji);
		}
		ArrayList<Traduccion> traducciones = new ArrayList<>();
		ArrayList<Traduccion> validadas = new ArrayList<>();
		traducciones = emoji.getTraducciones();

		for (int x = 0; x < traducciones.size(); x++) {
			if (traducciones.get(x).isValidado()) {
				validadas.add(traducciones.get(x));

			}
		}

		request.getSession().setAttribute("validadas", validadas);

		request.getSession().setAttribute("emoji", emoji);

		RequestDispatcher view = request.getRequestDispatcher("Votacion.jsp");
		view.forward(request, response);
	}

}
