package mog.net.teams.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class ServeBlob extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 443728861382958124L;

	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BlobKey blobKey = new BlobKey(req.getParameter("key"));
		blobstoreService.serve(blobKey, resp);
	}

}
