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

public class NuevoEmoji_Servlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DISEDAO dao = DISEDAOImpl.getInstancia();
		ArrayList<Emoji> emojis = new ArrayList<>();
		ArrayList<Emoji> emojis1 = new ArrayList<>();
		
		emojis1.addAll(dao.leerTodosEmojis());
		
		for(int i = 0; i<emojis1.size(); i++){
			if(!(emojis1.get(i).isValidado())){
				emojis.add(emojis1.get(i));
			}
		}
		UserService userService = UserServiceFactory.getUserService();
		
		if (req.getUserPrincipal() != null) {
			if (userService.isUserAdmin()) {
				RequestDispatcher view = req.getRequestDispatcher("NuevoEmojiAdmin.jsp");
				req.getSession().setAttribute("emojis", emojis);
				view.forward(req, resp);
			} else {
				RequestDispatcher view = req.getRequestDispatcher("NuevoEmoji.jsp");
				view.forward(req, resp);
			}
		}else {
			RequestDispatcher view = req.getRequestDispatcher("NuevoEmoji.jsp");
			view.forward(req, resp);
		}

	}

}