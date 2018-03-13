package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.OrderService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
//	private static final int AANTAL_RIJEN = 10;
	private final transient OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int vanafRij = request.getParameter("vanafRij") == null ? 0 : Integer.parseInt(request.getParameter("vanafRij"));
//		request.setAttribute("vanafRij", vanafRij);
//		request.setAttribute("aantalRijen", AANTAL_RIJEN);
		request.setAttribute("orders", orderService.findMetStatusBehalveShippedEnCancelled());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checked = request.getParameterValues("orderIdShip");
		if (checked != null) {
			for (String id : checked) {
				orderService.updateStock(Long.parseLong(id));
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

}
