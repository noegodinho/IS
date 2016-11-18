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
    private boolean found;
    private UtilsServlet utils;

    Logger logger;

    public NewCourseServlet(){
        super();
        this.courseStudents = new ArrayList<>();
        this.students = new ArrayList<>();
        this.logger = LoggerFactory.getLogger(AdminBean.class);
        this.utils = new UtilsServlet();
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String profEmail = request.getParameter("profEmail");

        List<ProfessorDTO> professorsDTO = this.ejbremote.getProfessors();

        for (ProfessorDTO professor : professorsDTO){
            if (professor.getInstEmail().equals(profEmail))
                choosenProfessor = professor;
        }

        if (!courseName.isEmpty() && choosenProfessor != null && !courseStudents.isEmpty()){
            this.ejbremote.createCourse(courseName, profEmail, courseStudents);
            logger.info("Course successfully created");
            courseStudents.clear();
            students.clear();
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }

        else {

            if (profEmail.isEmpty() || choosenProfessor == null) {
                utils.popupMessage(response,"Professor doesn't exist","newCourse");
                logger.error("Invalid Professor");
            }

            else if (courseStudents.isEmpty()) {
                utils.popupMessage(response, "Add at least one student", "newCourse");
                logger.error("Add at least one student");
            }
        }

        courseStudents.clear();
        students.clear();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("create")){
            processRequest(request, response);
        }

        if(request.getParameter("action").equals("add")) {
            addStudentToList(request, response);
            if (found) {
                session.setAttribute("students", students);
                request.getRequestDispatcher("newCourse.jsp").forward(request, response);
            }
        }

        else if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void addStudentToList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        found = false;
        String studentEmail = request.getParameter("studentEmail");
        courseStudents.add(studentEmail);
        logger.info(String.valueOf(courseStudents.size()));
        for (StudentDTO student : this.ejbremote.getStudents()){
            if (student.getInstEmail().equals(studentEmail)) {
                students.add(student);
                found = true;
            }
        }

        if (!found){
            utils.popupMessage(response, "Student not found", "newCourse");
            logger.error("Student not found");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
