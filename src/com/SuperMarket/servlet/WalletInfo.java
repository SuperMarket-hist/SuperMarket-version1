package com.SuperMarket.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.wallet;
import com.SuperMarket.utils.DoWallet;

/**
 * Servlet implementation class WalletInfo
 * 实现查询钱包servlet
 */
@WebServlet("/WalletInfo")
public class WalletInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WalletInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//List<wallet> list = new ArrayList<wallet>();
		wallet wt = new DoWallet().walletQuery();
		request.setAttribute("wallet", wt);
		request.getRequestDispatcher("walletInfo.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
