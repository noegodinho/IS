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
import java.io.PrintWriter;


@WebServlet(name = "/DeleteCourseServlet")
public class DeleteCourseServlet extends HttpServlet {

    @EJB
    private AdminBeanRemote ejbremote;
    Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCourseServlet(){
        super();
        this.logger = LoggerFactory.getLogger(AdminBean.class);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("course");


        if(this.ejbremote.deleteCourse(courseName)){
            new UtilsServlet().popupMessage(response, "Course Successfully deleted","deleteCourse");
        }

        else{
            new UtilsServlet().popupMessage(response, "Course failed to delete","deleteCourse");
            logger.error("Course failed to delete");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("delete")){
            processRequest(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
