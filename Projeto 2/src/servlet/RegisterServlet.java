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
import java.util.Date;

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

        PrintWriter out = response.getWriter();
        //Get sign up information
        String userType = request.getParameter("userType");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Integer day = Integer.parseInt(request.getParameter("day"));
        Integer month = Integer.parseInt(request.getParameter("month"));
        Integer year = Integer.parseInt(request.getParameter("year"));
        Date birth = new UtilsServlet().getDate(day, month, year);
        String instEmail = request.getParameter("instEmail");
        String altEmail = request.getParameter("altEmail");
        String address = request.getParameter("address");
        Integer telephone = Integer.parseInt(request.getParameter("telephone"));

        String hashedPassword = new UtilsServlet().createHash(password);

        if (userType.equals("student")){
            Integer number = Integer.parseInt(request.getParameter("number"));
            Integer yearOfCourse = Integer.parseInt(request.getParameter("yearOfCourse"));

            this.ejbremote.createStudentAccount(hashedPassword, name, birth, instEmail, altEmail, address,
                                                telephone, number, yearOfCourse);

        }

        else if (userType.equals("professor")){
            Integer internalNumber = Integer.parseInt(request.getParameter("internalNumber"));
            String category = request.getParameter("category");
            String office = request.getParameter("office");
            Integer number = Integer.parseInt(request.getParameter("internalTelephoneNumber"));
            Double salary = Double.parseDouble(request.getParameter("salary"));

            this.ejbremote.createProfessorAccount(hashedPassword, name, birth, instEmail, altEmail, address,
                    telephone, internalNumber, category, office, number, salary);
        }

        request.getRequestDispatcher("menu.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        if(request.getParameter("action").equals("register")){
            processRequest(request, response);
        }

        else if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
