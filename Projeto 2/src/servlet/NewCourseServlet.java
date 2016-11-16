package servlet;

import dto.ProfessorDTO;
import dto.StudentDTO;

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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "/NewCourseServlet")
public class NewCourseServlet extends HttpServlet {

    @EJB
    private AdminBeanRemote ejbremote;
    private ProfessorDTO choosenProfessor;
    private List<StudentDTO> students;
    private List<String> courseStudents;

    Logger logger;

    public NewCourseServlet(){
        super();
        this.courseStudents = new ArrayList<>();
        this.students = new ArrayList<>();
        this.logger = LoggerFactory.getLogger(AdminBean.class);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String courseName = request.getParameter("courseName");
        String profEmail = request.getParameter("profEmail");

        /*List<ProfessorDTO> professorsDTO = this.ejbremote.getProfessors();

        for (ProfessorDTO professor : professorsDTO){
            if (professor.getInstEmail().equals(profEmail))
                choosenProfessor = professor;
        }*/

        //if (!courseName.isEmpty() && choosenProfessor != null && !courseStudents.isEmpty()){
        if (!courseName.isEmpty() && !profEmail.isEmpty() && !courseStudents.isEmpty()){
            this.ejbremote.createCourse(courseName, profEmail, courseStudents);
            logger.info("Course successfully created");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        else {

            if (profEmail.isEmpty()) {
                out.println("<script type=\"text/javascript\">");
                out.println("if (confirm(\"Professor doesn't exist\")) {}");
                out.println("window.location.replace(\"http://localhost:8080/Web/newCourse.jsp\");");
                out.println("</script>");
                logger.error("Invalid Professor");
                request.getRequestDispatcher("newCourse.jsp").forward(request, response);
            }

            else if (courseStudents.isEmpty()) {
                out.println("<script type=\"text/javascript\">");
                out.println("if (confirm(\"Add at least one student\")) {}");
                out.println("window.location.replace(\"http://localhost:8080/Web/newCourse.jsp\");");
                out.println("</script>");
                logger.error("Add at least one student");
                request.getRequestDispatcher("newCourse.jsp").forward(request, response);
            }
        }

        courseStudents.clear();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("create")){
            processRequest(request, response);
        }

        if(request.getParameter("action").equals("add")) {
            addStudentToList(request, response);
            session.setAttribute("students",students);
            request.getRequestDispatcher("newCourse.jsp").forward(request, response);
        }

        else if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void addStudentToList(HttpServletRequest request, HttpServletResponse response) {
        String studentEmail = request.getParameter("studentEmail");
        courseStudents.add(studentEmail);
        for (StudentDTO student : this.ejbremote.getStudents()){
            if (student.getInstEmail().equals(studentEmail))
                students.add(student);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
