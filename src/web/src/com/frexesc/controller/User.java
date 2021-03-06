package com.frexesc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.frexesc.Constants;
import com.frexesc.model.UserBean;
import com.frexesc.service.WebService;
import com.frexesc.soap.UserSoapProxy;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String hostname = Constants.HOSTNAME;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if (request.getSession(true).getAttribute("user_id") == null) {
			response.sendRedirect("register");
		} else {
			if (request.getParameter("action") == null) {
				String user_id = request.getParameter("id");

				/** Set WebService (REST) for retrieving list of User */
				WebService _user = new WebService(hostname + "user");
				_user.addParam("action", "view_profile");
				_user.addParam("user_id", user_id);
				_user.addHeader("GData-Version", "2");
				try {
					_user.execute(WebService.REQUEST_METHOD.GET);
					String user = _user.getResponse();
					JSONParser parser = new JSONParser();
					JSONObject mainJSON = null;
					try {
						mainJSON = (JSONObject) parser.parse(user);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					JSONObject data = (JSONObject) mainJSON.get("data");
					out.println(user);
					UserBean active_user = new UserBean(data.get("username")
							.toString(), data.get("password").toString(), data
							.get("email").toString(), data.get("name")
							.toString(), data.get("telephone").toString(), data
							.get("address").toString(), data.get("province")
							.toString(), data.get("city").toString(), data.get(
							"postal").toString(), Integer.parseInt(data.get(
							"role").toString()), data.get("nocard").toString(),
							data.get("nacard").toString(), data.get("excard")
									.toString(), Integer.parseInt(data.get(
									"transaction").toString()));
					request.setAttribute("user", active_user);
					request.setAttribute("id", user_id);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/profile.jsp");
					dispatcher.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// try {
				// Statement statement = connection.createStatement();
				// ResultSet rs =
				// statement.executeQuery("SELECT * FROM user WHERE id='" + id +
				// "' LIMIT 1");
				// if (rs.next()) {
				// UserBean user = new UserBean(rs.getString("username"), null,
				// rs.getString("email"), rs.getString("nama"),
				// rs.getString("handphone"), rs.getString("alamat"),
				// rs.getString("provinsi"), rs.getString("kota"),
				// rs.getString("kodepos"), rs.getInt("role"),
				// rs.getString("nomor_kartu"), rs.getString("nama_kartu"),
				// rs.getString("expire_kartu"),
				// Integer.parseInt(rs.getString("transaksi")));
				// request.setAttribute("user", user);
				// request.setAttribute("id", id);
				// }
				// RequestDispatcher dispatcher =
				// getServletContext().getRequestDispatcher("/profile.jsp");
				// dispatcher.forward(request, response);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }

			} else {
				String user_id = request.getParameter("id");
				/** Set WebService (REST) for retrieving list of User */
				WebService _user = new WebService(hostname + "user");
				_user.addParam("action", "view_profile");
				_user.addParam("user_id", user_id);
				_user.addHeader("GData-Version", "2");
				try {
					_user.execute(WebService.REQUEST_METHOD.GET);
					String user = _user.getResponse();
					JSONParser parser = new JSONParser();
					JSONObject mainJSON = null;
					try {
						mainJSON = (JSONObject) parser.parse(user);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					JSONObject data = (JSONObject) mainJSON.get("data");
					out.println(user);
					UserBean active_user = new UserBean(data.get("username")
							.toString(), data.get("password").toString(), data
							.get("email").toString(), data.get("name")
							.toString(), data.get("telephone").toString(), data
							.get("address").toString(), data.get("province")
							.toString(), data.get("city").toString(), data.get(
							"postal").toString(), Integer.parseInt(data.get(
							"role").toString()), data.get("nocard").toString(),
							data.get("nacard").toString(), data.get("excard")
									.toString(), Integer.parseInt(data.get(
									"transaction").toString()));
					request.setAttribute("user", active_user);
					request.setAttribute("id", user_id);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/editprofile.jsp");
					dispatcher.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// try {
				// Statement statement = connection.createStatement();
				// ResultSet rs =
				// statement.executeQuery("SELECT * FROM user WHERE id='" + id +
				// "' LIMIT 1");
				// if (rs.next()) {
				// UserBean user = new UserBean(rs.getString("username"),
				// rs.getString("password"), rs.getString("email"),
				// rs.getString("nama"), rs.getString("handphone"),
				// rs.getString("alamat"), rs.getString("provinsi"),
				// rs.getString("kota"), rs.getString("kodepos"),
				// rs.getInt("role"), rs.getString("nomor_kartu"),
				// rs.getString("nama_kartu"), rs.getString("expire_kartu"),
				// Integer.parseInt(rs.getString("transaksi")));
				// request.setAttribute("user", user);
				// }
				// RequestDispatcher dispatcher =
				// getServletContext().getRequestDispatcher("/editprofile.jsp");
				// dispatcher.forward(request, response);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession sessions = request.getSession(true);
		String action = request.getParameter("action");

		String password = request.getParameter("password1");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String postal = request.getParameter("postal");

		if (action.equals("register")) {
			String username = request.getParameter("username");

			/** SOAP Invocation */
			UserSoapProxy _userSoap = new UserSoapProxy();
			_userSoap.register(username, password, email, username, telephone,
					address, province, city, postal);

			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("register", "y");

			getServletContext().getRequestDispatcher("/login").forward(request,
					response);
		} else if (action.equals("edit_profile")) {
			String id = sessions.getAttribute("user_id").toString();
			
			/** Set WebService (REST) for retrieving list of User */
			WebService _user = new WebService(hostname + "user");
			_user.addParam("action", "edit_profile");
			_user.addParam("id", id);
			_user.addParam("password", password);
			_user.addParam("email", email);
			_user.addParam("name", name);
			_user.addParam("telephone", telephone);
			_user.addParam("address", address);
			_user.addParam("province", province);
			_user.addParam("city", city);
			_user.addParam("postal", postal);
			_user.addHeader("GData-Version", "2");
			try {
				_user.execute(WebService.REQUEST_METHOD.POST);
				String user = _user.getResponse();
				out.print(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// String updateQuery = "UPDATE user SET nama='" + name +
			// "', password='" + password + "', email='" + email +
			// "', handphone='" + telephone + "', alamat='" + address +
			// "', kota='" + city + "', provinsi='" + province + "', kodepos='"
			// + postal + "' WHERE id='" +
			// request.getSession(true).getAttribute("user_id") + "'";
			// System.out.println(updateQuery);
			// try {
			// Statement statement = connection.createStatement();
			// statement.executeUpdate(updateQuery);
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			 response.sendRedirect("user?id=" +
			 request.getSession(true).getAttribute("user_id"));
		}

	}

}
