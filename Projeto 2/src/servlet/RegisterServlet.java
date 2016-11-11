package servlet;

import ejb.AdminBean;
import ejb.AdminBeanRemote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB
    private AdminBeanRemote ejbremote;
    private Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet(){
        this.logger = LoggerFactory.getLogger(AdminBean.class);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //Get sign up information
        String password = request.getParameter("password");

        String hashedPassword = new Utils().createHash(password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(request.getParameter("action").equals("register")){
            processRequest(request, response);
        }
    }
}
