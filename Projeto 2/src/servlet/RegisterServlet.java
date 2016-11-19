package servlet;

import ejbservices.AdminBeanRemote;

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
import java.util.Date;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB
    private AdminBeanRemote ejbremote;
    private Logger logger;
    private UtilsServlet utils;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet(){

        this.logger = LoggerFactory.getLogger(RegisterServlet.class);
        this.utils = new UtilsServlet();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //Get sign up information
        String userType = request.getParameter("userType");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Integer day = request.getParameter("day").isEmpty()?null:Integer.valueOf(request.getParameter("day"));
        Integer month = request.getParameter("month").isEmpty()?null:Integer.valueOf(request.getParameter("month"));
        Integer year = request.getParameter("year").isEmpty()?null:Integer.valueOf(request.getParameter("year"));
        Date birth = utils.getDate(day, month, year);
        String instEmail = request.getParameter("instEmail");
        String altEmail = request.getParameter("altEmail");
        String address = request.getParameter("address");
        Integer telephone = request.getParameter("telephone").isEmpty()?null:Integer.valueOf(request.getParameter("telephone"));

        String hashedPassword = utils.createHash(password);

        if (userType.equals("student")){
            Integer number = request.getParameter("number").isEmpty()?null:Integer.valueOf(request.getParameter("number"));
            Integer yearOfCourse = request.getParameter("yearOfCourse").isEmpty()?null:Integer.valueOf(request.getParameter("yearOfCourse"));

            if (name.isEmpty() || password.isEmpty() || birth==null || instEmail.isEmpty() || altEmail.isEmpty() || address.isEmpty() || telephone==null || number==null || yearOfCourse==null)
                utils.popupMessage(response,"Fill all the fields","register");
            else {
                this.ejbremote.createStudentAccount(hashedPassword, name, birth, instEmail, altEmail, address,
                        telephone, number, yearOfCourse);
                utils.popupMessage(response, "Student successfully created", "menu");
            }

        }

        else if (userType.equals("professor")){
            Integer internalNumber = request.getParameter("internalNumber").isEmpty()?null:Integer.valueOf(request.getParameter("internalNumber"));
            String category = request.getParameter("category");
            String office = request.getParameter("office");
            Integer number = request.getParameter("internalPhoneNumber").isEmpty()?null:Integer.valueOf(request.getParameter("internalPhoneNumber"));
            Double salary = request.getParameter("salary").isEmpty()?null:Double.valueOf(request.getParameter("salary"));

            if (name.isEmpty() || password.isEmpty() || birth == null || instEmail.isEmpty() || altEmail.isEmpty() || address.isEmpty() || telephone == null ||
                    internalNumber == null || category.isEmpty() || office.isEmpty() || number == null || salary == null)
                utils.popupMessage(response,"Fill all the fields","register");
            else {
                this.ejbremote.createProfessorAccount(hashedPassword, name, birth, instEmail, altEmail, address,
                        telephone, internalNumber, category, office, number, salary);
                utils.popupMessage(response, "Professor successfully created", "menu");
            }
        }
        else {
            utils.popupMessage(response, "Choose a type of user", "register");
        }
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
