package mog.net.teams.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadPlayer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8719906985221332429L;

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Save the image to the blobstore
		
		System.out.println("I tried to upload something");
		Map<String, List<BlobKey>> uploads = blobstoreService.getUploads(req);		
		BlobKey blobKey = uploads.values().iterator().next().get(0);
		
		resp.setContentType("text/html");
		resp.getWriter().print(blobKey.getKeyString());
		
	}
	
	
	
	
}
