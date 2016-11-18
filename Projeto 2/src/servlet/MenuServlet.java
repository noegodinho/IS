package servlet;

import dto.CourseDTO;

import ejbservices.AdminBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "/MenuServlet")
public class MenuServlet extends HttpServlet {

    @EJB
    AdminBeanRemote ejbremote;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<CourseDTO> courses = this.ejbremote.getCourses();
        session.setAttribute("coursesList", courses);

        if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("Create New User")) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("Edit User Information"));

        else if(request.getParameter("action").equals("Delete User")){
            request.getRequestDispatcher("deleteUser.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("Create New Course")) {
            request.getRequestDispatcher("newCourse.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("Edit Course Information"));

        else if(request.getParameter("action").equals("Delete Course")){
            request.getRequestDispatcher("deleteCourse.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("Upload Course Material"));

        else if(request.getParameter("action").equals("Delete Material"));

        else if (request.getParameter("action").equals("List Students")){
            request.getRequestDispatcher("listStudents.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("Search Student"));



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
