package servlet;

import dto.CourseDTO;
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

@WebServlet(name = "EditCourseServlet")
public class EditCourseServlet extends HttpServlet{
    @EJB
    private AdminBeanRemote ejbremote;
    private Logger logger;
    private UtilsServlet utils;
    private boolean found;
    private String courseName;

    public EditCourseServlet(){
        super();
        this.logger = LoggerFactory.getLogger(EditCourseServlet.class);
        this.utils = new UtilsServlet();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String newCourseName = request.getParameter("newCourseName");

        if (!courseName.isEmpty() && !newCourseName.isEmpty()){
            this.ejbremote.editCourse(courseName, "", null, newCourseName);
            logger.info("Course successfully edited");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        else{
            utils.popupMessage(response, "Course not found", "editCourse");
            request.getRequestDispatcher("editCourse.jsp").forward(request, response);
        }
    }

    private void searchCourse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        found = false;
        courseName = request.getParameter("courseName");

        for(CourseDTO courseDTO : this.ejbremote.getCourses()){
            if(courseDTO.getCourseName().equals(courseName)){
                found = true;
                break;
            }
        }

        if(!found){
            utils.popupMessage(response, "Course not found", "editCourse");
            logger.error("Course not found");
            request.getRequestDispatcher("editCourse.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("edit")){
            processRequest(request, response);
        }

        if(request.getParameter("action").equals("search")){
            searchCourse(request, response);
            if (found) {
                request.setAttribute("course", courseName);
                request.getRequestDispatcher("editCourse.jsp").forward(request, response);
            }
            else{
                courseName = "";
            }
        }

        else if(request.getParameter("action").equals("logout")) {
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
