package servlet;

import dto.CourseDTO;
import dto.ProfessorDTO;
import dto.StudentDTO;
import ejbservices.ClientBean;
import ejbservices.ClientBeanRemote;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "/ListCoursesServlet")
public class ListCoursesServlet extends HttpServlet {

    @EJB
    private ClientBeanRemote ejbremote;
    private Logger logger;
    private List<CourseDTO> courses;
    private UtilsServlet utils;

    public ListCoursesServlet(){
        super();
        this.courses = new ArrayList<>();
        this.logger = LoggerFactory.getLogger(ClientBean.class);
        this.utils = new UtilsServlet();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") instanceof StudentDTO) {
            logger.info("USERS ID: "+((StudentDTO) session.getAttribute("user")).getId());
            courses = this.ejbremote.getCourses(((StudentDTO) session.getAttribute("user")).getId(), 2);
            logger.info("COURSES SIZE: "+courses.size());
            request.setAttribute("coursesIN", courses);
            request.getRequestDispatcher("listCourses.jsp").forward(request, response);
        }

        else if (session.getAttribute("user") instanceof ProfessorDTO) {
            courses = this.ejbremote.getCourses(((ProfessorDTO) session.getAttribute("user")).getId(), 1);
            request.setAttribute("coursesIN", courses);
            request.getRequestDispatcher("listCourses.jsp").forward(request, response);
        }

        else {
            utils.popupMessage(response, "Login as either a Professor or a Student", "menu");
        }
    }
}
