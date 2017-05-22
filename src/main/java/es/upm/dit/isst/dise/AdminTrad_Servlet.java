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

public class AdminTrad_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imagen = request.getParameter("imagen");
		String traduccion = request.getParameter("traduccion");
		String submit = request.getParameter("submit");
		Emoji emoji = null;
		DISEDAO dao = DISEDAOImpl.getInstancia();
		PuntuacionDAO daoRank = PuntuacionDAOImpl.getInstancia();

		emoji = dao.leerEmoji(imagen);
		ArrayList<Traduccion> array = new ArrayList<Traduccion>();
		ArrayList<Traduccion> traducciones = new ArrayList<Traduccion>();
		array.addAll(emoji.getTraducciones());
		for(int x=0; x < array.size(); x++){
			if(array.get(x).getTraduccion().equals(traduccion)){
				if(submit.equals("Aceptar")){
					Traduccion n = array.get(x);
					n.setValidado(true);
					traducciones.add(n);
					Puntuacion puntuacion = daoRank.leerPuntuacion(array.get(x).getAutor());
					int p = puntuacion.getPuntuacion();
					p += 10;
					puntuacion.setPuntuacion(p);
					daoRank.actualizarPuntuacion(puntuacion);
				}else{
					Puntuacion puntuacion = daoRank.leerPuntuacion(array.get(x).getAutor());
					int p = puntuacion.getPuntuacion();
					p -= 15;
					puntuacion.setPuntuacion(p);
					daoRank.actualizarPuntuacion(puntuacion);
				}
			}else{
				Traduccion n = array.get(x);
				traducciones.add(n);
			}
		}
		
		emoji.setTraducciones(traducciones);
		dao.actualizarEmoji(emoji);
		
		ArrayList<Traduccion> validadas = new ArrayList<>();
		ArrayList<Traduccion> noValidadas = new ArrayList<>();
		traducciones = emoji.getTraducciones();
		
		for(int x = 0; x < traducciones.size(); x++){
			
			if(traducciones.get(x).isValidado()){
				validadas.add(traducciones.get(x));
				
			}
			else{
				noValidadas.add(traducciones.get(x));

			}
		}
		
		request.getSession().setAttribute("validadas", validadas);
		request.getSession().setAttribute("noValidadas", noValidadas);
		request.getSession().setAttribute("emoji", emoji);
		
		RequestDispatcher view = request.getRequestDispatcher("VotacionAdmin.jsp");
		view.forward(request, response);
	}
	
}