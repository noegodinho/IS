package servlet;

import dto.*;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "/ListMaterialsServlet")
public class ListMaterialsServlet extends HttpServlet {

    @EJB
    private ClientBeanRemote ejbremote;
    private Logger logger;
    private List<CourseDTO> courses;
    private List<MaterialDTO> materials;

    public ListMaterialsServlet(){
        super();
        this.logger = LoggerFactory.getLogger(ListMaterialsServlet.class);
        this.courses = new ArrayList<>();
        this.materials = new ArrayList<>();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else if(request.getParameter("action").equals("search")){
            listMaterials(request,response);
        }
        else if (request.getParameter("action").equals("download")){
            downloadMaterials(request, response);
        }
        else{
            doGet(request, response);
        }
    }

    private void downloadMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void listMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");

        materials = this.ejbremote.getMaterials(courseName);

        request.setAttribute("materialsList", materials);
        request.getRequestDispatcher("listMaterials.jsp").forward(request, response);

        materials.clear();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") instanceof ProfessorDTO)
            courses = this.ejbremote.getCourses(((ProfessorDTO) session.getAttribute("user")).getId(), 1);
        else if (session.getAttribute("user") instanceof StudentDTO)
            courses = this.ejbremote.getCourses(((StudentDTO) session.getAttribute("user")).getId(), 2);
        else
            courses = (List<CourseDTO>) session.getAttribute("coursesList");
        request.setAttribute("coursesIN", courses);
        request.getRequestDispatcher("listMaterials.jsp").forward(request, response);
    }
}
