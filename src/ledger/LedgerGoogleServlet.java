package ledger;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LedgerGoogleServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String str = req.getQueryString();
		System.out.println("**********The query has : "+str);
		resp.setContentType("text/plain");
		resp.getWriter().println("exists");
	}
}
