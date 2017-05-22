package es.upm.dit.isst.dise;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.upm.dit.isst.dise.dao.DISEDAO;
import es.upm.dit.isst.dise.dao.DISEDAOImpl;
import es.upm.dit.isst.dise.model.Emoji;

public class TraduccionEspEmo_Servlet extends HttpServlet{
	
	// pasar el html a capon como string, con <p> y cerrando p al meter imagen float
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DISEDAO dao = DISEDAOImpl.getInstancia();
		ArrayList<Emoji> emojis = new ArrayList<>();
		emojis.addAll(dao.leerTodosEmojis());
		
		String[] traducciones = new String[emojis.size()];
		for (int i = 0; i < traducciones.length; i++) {
			traducciones[i] = emojis.get(i).getTraducciones().get(0).getTraduccion();
		}
		String textoATraducir = request.getParameter("escrito");
		//request.getSession().getAttribute("textoATraducir");
		String[] parts = textoATraducir.split("\\s++");
//		String[] parts1 = new String[parts.length];
//		parts1 = parts;
//		//quitamos tildes, interrogaciones, etc
//		for(int x=0; x < parts.length; x++){
//			parts[x] = normalizar(parts[x]);
//		}
		String textoFinal="";
		//String textoFinal="<p>";
		String palabraI = null;
		int saltaNPalabras = 0;
		boolean flag=false;
		for (int i = 0; i < parts.length; i++) {
			palabraI = parts[i];
			
			if (saltaNPalabras > 0){
				saltaNPalabras--;
				continue;
			}
			String[] partsTraduccion = null;
			for (int j = 0; j < traducciones.length; j++) {
				partsTraduccion = traducciones[j].split(" ");
				for(int x=0; x < partsTraduccion.length; x++){
					partsTraduccion[x] = normalizar(partsTraduccion[x]);
				}
				if (partsTraduccion.length > 1){
					if (normalizar(palabraI).equals(partsTraduccion[0]) ){
						int size = partsTraduccion.length;
						if (i+size <= parts.length){
							for (int k = 1; k < size; k++) {
								if (normalizar(parts[i+k]).equals(partsTraduccion[k])){
									if(k==(size-1)){
										saltaNPalabras=size-1;
										flag=true;
										textoFinal += "<img src='"+emojis.get(j).getImagen() +"' width='50px' height='50px'/>";
										//textoFinal += "</p><img src='"+emojis.get(j).getImagen() +"' width='30px' height='30px'/><p>";
									}
									continue;
								}
								break;
							}
							
						}
					}
				}
				else if(normalizar(palabraI).equals(normalizar(traducciones[j]))){
					flag=true;
					textoFinal += "<img src='"+emojis.get(j).getImagen() +"' width='50px' height='50px'/>";
					//textoFinal += "</p><img src='"+emojis.get(j).getImagen() +"' width='30px' height='30px'/><p>";
					break;
				}
				
			}
			if (flag){
				flag=false;
				continue;
			}
			textoFinal += " " + parts[i];
		}
		//textoFinal += "</p>";
		
		request.getSession().setAttribute("textoFinal", textoFinal);
		response.sendRedirect("TraducirVista.jsp");
	}
	
	private String normalizar(String string){
		
	    String limpio =null;
	    if (string !=null) {
	        String valor = string;
	        valor = valor.toUpperCase();
	        // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
	        limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
	        // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
	        limpio = limpio.replaceAll("[^a-zA-Z0-9]+","");
	        // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
	        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
	    }
	    
		String n = limpio.toLowerCase();
	    
		return n;
	}
	
}
