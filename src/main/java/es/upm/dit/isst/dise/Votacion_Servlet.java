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
import es.upm.dit.isst.dise.model.Emoji;
import es.upm.dit.isst.dise.model.Traduccion;

public class Votacion_Servlet extends HttpServlet {
	//public String seleccionado;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DISEDAO dao = DISEDAOImpl.getInstancia();
		String nemoji = req.getParameter("escrito");
		Emoji emoji = null;
		emoji = dao.leerEmoji(nemoji);
		
		ArrayList<Traduccion> traducciones = new ArrayList<>();
		ArrayList<Traduccion> validadas = new ArrayList<>();
		ArrayList<Traduccion> noValidadas = new ArrayList<>();
		traducciones = emoji.getTraducciones();
		req.getSession().setAttribute("emoji", emoji);
		
		for(int x = 0; x < traducciones.size(); x++){
			
			if(traducciones.get(x).isValidado()){
				validadas.add(traducciones.get(x));
			}
			else{
				noValidadas.add(traducciones.get(x));
			}
			
		}
		
		UserService userService = UserServiceFactory.getUserService();
		
		req.getSession().setAttribute("validadas", validadas);
		req.getSession().setAttribute("noValidadas", noValidadas);
		
		if(userService.isUserAdmin()){
			RequestDispatcher view = req.getRequestDispatcher("VotacionAdmin.jsp");
			view.forward(req, resp);	
		} else {
			RequestDispatcher view = req.getRequestDispatcher("Votacion.jsp");
			view.forward(req, resp);
		}
		
	}

}
