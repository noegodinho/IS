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
import java.util.List;


@WebServlet(name = "/ListStudentsServlet")
public class ListStudentsServlet extends HttpServlet {

    @EJB
    private ProfBeanRemote ejbremote;
    private List<StudentDTO> students;

    private Logger logger;

    public ListStudentsServlet(){
        super();
        this.students = new ArrayList<>();
        this.logger = LoggerFactory.getLogger(ProfBean.class);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("course");

        students = this.ejbremote.getStudentsByCourse(courseName,true);

        request.setAttribute("studentsList", students);
        request.getRequestDispatcher("listStudents.jsp").forward(request, response);

        students.clear();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("search")){
            processRequest(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
