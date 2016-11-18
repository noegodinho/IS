package servlet;

import ejbservices.AdminBean;
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


@WebServlet(name = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    @EJB
    private AdminBeanRemote ejbremote;
    private Logger logger;
    private UtilsServlet popups;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet(){
        super();
        this.logger = LoggerFactory.getLogger(AdminBean.class);
        this.popups = new UtilsServlet();
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String instEmail = request.getParameter("instEmail");

        if (userType.equals("student")){
            if(this.ejbremote.deleteStudent(instEmail)){
                popups.popupMessage(response, "Student successfully deleted", "deleteUser");
            }
            else{
                popups.popupMessage(response, "Student doesn't exists", "deleteUser");
            }
        }
        else if (userType.equals("professor")){
            if(this.ejbremote.deleteProfessor(instEmail)){
                popups.popupMessage(response, "Professor successfully deleted", "deleteUser");
            }

            else{
                popups.popupMessage(response, "Professor doesn't exists", "deleteUser");
            }
        }
        else{
            popups.popupMessage(response, "Choose a type of user", "deleteUser");
            logger.error("User didn't especify type of user to delete");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(request.getParameter("action").equals("delete")){
            processRequest(request, response);
        }

        else if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
