package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.services.OrderService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final int AANTAL_RIJEN = 10;
	private final transient OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vanafRij = request.getParameter("vanafRij") == null ? 0 : Integer.parseInt(request.getParameter("vanafRij"));
		request.setAttribute("vanafRij", vanafRij);
		request.setAttribute("aantalRijen", AANTAL_RIJEN);
		List<Order> orders = orderService.findMetStatusBehalveShippedEnCancelled(vanafRij, AANTAL_RIJEN + 1);
		if (orders.size() <= AANTAL_RIJEN) {
			request.setAttribute("laatstePagina", true);
		} else {
			orders.remove(AANTAL_RIJEN);
		}
		request.setAttribute("orders", orders);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checked = request.getParameterValues("orderIdShip");
		Map<String, String> fouten = new HashMap<>();
		if (checked != null) {
			try {
				orderService.updateStock(checked, fouten);
			} catch (RecordAangepastException ex) {
				fouten.put("order", "een andere gebruiker heeft deze order gewijzigd");
			}
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			doGet(request, response);
//			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
//			response.sendRedirect(request.getContextPath() + REDIRECT_URL);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
		}
	}

}
