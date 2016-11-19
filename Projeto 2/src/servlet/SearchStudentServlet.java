package servlet;

import dto.StudentDTO;
import ejbservices.ProfBean;
import ejbservices.ProfBeanRemote;
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


@WebServlet(name = "/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {

    @EJB
    private ProfBeanRemote ejbremote;
    private List<StudentDTO> students;
    private UtilsServlet utils;
    private Logger logger;

    public SearchStudentServlet(){
        super();
        this.students = new ArrayList<>();
        this.utils = new UtilsServlet();
        this.logger = LoggerFactory.getLogger(ProfBean.class);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer day = Integer.parseInt(request.getParameter("day"));
        Integer month = Integer.parseInt(request.getParameter("month"));
        Integer year = Integer.parseInt(request.getParameter("year"));
        Date birth = utils.getDate(day, month, year);
        String instEmail = request.getParameter("instEmail");
        String altEmail = request.getParameter("altEmail");
        String address = request.getParameter("address");
        Integer telephone = Integer.parseInt(request.getParameter("telephone"));
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer yearOfCourse = Integer.parseInt(request.getParameter("yearOfCourse"));

        students = this.ejbremote.searchStudents(name, birth,instEmail,altEmail,address,telephone,number,yearOfCourse);

        if (students.isEmpty()) {
            utils.popupMessage(response, "No students found", "searchStudent");
        }

        else{
            request.setAttribute("studentsList", students);
            request.getRequestDispatcher("searchStudent.jsp").forward(request, response);
        }

        students.clear();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(request.getParameter("action").equals("search")){
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
