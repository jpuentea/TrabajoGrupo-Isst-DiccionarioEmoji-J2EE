package es.upm.dit.isst.dise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import es.upm.dit.isst.dise.dao.DISEDAO;
import es.upm.dit.isst.dise.dao.DISEDAOImpl;
import es.upm.dit.isst.dise.model.Emoji;

public class SubirEmoji_Servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DISEDAO dao = DISEDAOImpl.getInstancia();
		Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory.getBlobstoreService().getUploads(request);
		List<BlobKey> blobKeys = blobs.get("file");
		if (blobKeys == null || blobKeys.isEmpty() || blobKeys.get(0) == null) {
			response.sendError(1200);
		}
		
		
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		
		String imageUrl = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));
		String user = request.getParameter("autor");
		dao.crearEmoji(imageUrl, user, request.getParameter("traduccion"), false);

		/*RequestDispatcher view = request.getRequestDispatcher("NuevoEmoji.jsp");
		view.forward(request, response);*/
		response.reset();
		response.sendRedirect("NuevoEmoji.jsp");
		//BlobstoreServiceFactory.getBlobstoreService().serve(blobKeys.get(0), response);
		//MIRAR https://ikaisays.com/2010/09/08/gwt-blobstore-the-new-high-performance-image-serving-api-and-cute-dogs-on-office-chairs/
		
	}

}
