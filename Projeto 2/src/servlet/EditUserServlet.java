package servlet;

import data.Professor;
import data.Student;
import dto.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "/EditUserServlet")
public class EditUserServlet extends HttpServlet {

    @EJB
    AdminBeanRemote ejbremote;
    private Logger logger;
    private UtilsServlet utils;

    public EditUserServlet(){
        super();
        this.logger = LoggerFactory.getLogger(DeleteUserServlet.class);
        this.utils = new UtilsServlet();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        else if (request.getParameter("action").equals("edit")){
            editUser(request, response);
        }

        else if (request.getParameter("action").equals("get")){
            getUser(request, response);
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userType = request.getParameter("userType");
        String instEmail = request.getParameter("instEmail");

        if (userType.equals("student")) {
            for (StudentDTO student : this.ejbremote.getStudents()) {
                if (student.getInstEmail().equals(instEmail)) {
                    request.setAttribute("userSelected", student);
                }
            }
        }

        else if (userType.equals("professor")){
            for (ProfessorDTO professor : this.ejbremote.getProfessors()) {
                if (professor.getInstEmail().equals(instEmail)) {
                    request.setAttribute("userSelected", professor);
                }
            }
        }

        request.getRequestDispatcher("editUser.jsp").forward(request, response);

    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userType = request.getParameter("userType");
        String instEmail = request.getParameter("instEmail");

        String name = request.getParameter("name");
        String newInstEmail = request.getParameter("newInstEmail");
        String altEmail = request.getParameter("altEmail");
        String address = request.getParameter("address");
        Integer telephone = request.getParameter("telephone").isEmpty()?null:Integer.valueOf(request.getParameter("telephone"));



        if (userType.equals("student")){
            Integer number = request.getParameter("number").isEmpty()?null:Integer.valueOf(request.getParameter("number"));
            Integer yearOfCourse = request.getParameter("yearOfCourse").isEmpty()?null:Integer.valueOf(request.getParameter("yearOfCourse"));

            if (name.isEmpty() || newInstEmail.isEmpty() || altEmail.isEmpty() || address.isEmpty() || telephone==null || number==null || yearOfCourse==null)
                utils.popupMessage(response,"Fill all the fields","register");
            else {
                this.ejbremote.editStudent(null, name, null, instEmail, altEmail, address,
                        telephone, number, yearOfCourse,null,newInstEmail);
                utils.popupMessage(response, "Student successfully edited", "menu");
            }

        }

        else if (userType.equals("professor")){
            Integer internalNumber = request.getParameter("internalNumber").isEmpty()?null:Integer.valueOf(request.getParameter("internalNumber"));
            String category = request.getParameter("category");
            String office = request.getParameter("office");
            Integer number = request.getParameter("internalTelephoneNumber").isEmpty()?null:Integer.valueOf(request.getParameter("internalTelephoneNumber"));
            Double salary = request.getParameter("salary").isEmpty()?null:Double.valueOf(request.getParameter("salary"));

            if (name.isEmpty() || newInstEmail.isEmpty() || altEmail.isEmpty() || address.isEmpty() || telephone == null ||
                    internalNumber == null || category.isEmpty() || office.isEmpty() || number == null || salary == null)
                utils.popupMessage(response,"Fill all the fields","register");
            else {
                this.ejbremote.editProfessor(null, name, null, instEmail, altEmail, address,
                        telephone, internalNumber, category, office, number, salary, null, newInstEmail);
                utils.popupMessage(response, "Professor successfully created", "menu");
            }
        }
        else {
            utils.popupMessage(response, "Choose a type of user", "register");
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
