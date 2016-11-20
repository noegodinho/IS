package servlet;

import dto.CourseDTO;
import dto.MaterialDTO;
import dto.ProfessorDTO;
import dto.StudentDTO;
import ejbservices.AdminBeanRemote;
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


@WebServlet(name = "/DeleteMaterialServlet")
public class DeleteMaterialServlet extends HttpServlet {

    @EJB
    private AdminBeanRemote ejbremote;
    private Logger logger;
    private List<CourseDTO> courses;
    private List<MaterialDTO> materials;
    private UtilsServlet utils;

    public DeleteMaterialServlet(){
        super();
        this.logger = LoggerFactory.getLogger(DeleteMaterialServlet.class);
        this.courses = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.utils = new UtilsServlet();
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
        else if (request.getParameter("action").equals("delete")){
            deleteMaterials(request, response);
        }
        else{
            doGet(request, response);
        }
    }

    private void deleteMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("materialSelected");

        String fileTimeStamp = null;
        for (MaterialDTO material : materials){
            if (material.getFilename().equals(fileName)) {
                fileTimeStamp = material.getTimestamp();
                logger.info(fileTimeStamp+ "FOUND");
            }
        }
        if (fileTimeStamp == null){
            utils.popupMessage(response, "Error retrieving material", "deleteMaterial");
        }
        else {

            if (this.ejbremote.deleteMaterial(fileTimeStamp)) {
                utils.popupMessage(response, "Material Successfully deleted", "deleteMaterial");
            } else {
                utils.popupMessage(response, "Material failed to delete", "deleteMaterial");
                logger.error("Material failed to delete");
            }
        }
    }

    private void listMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");

        materials = this.ejbremote.getMaterials(courseName);

        request.setAttribute("materialsList", materials);
        request.getRequestDispatcher("deleteMaterial.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        courses = (List<CourseDTO>) session.getAttribute("coursesList");
        request.setAttribute("coursesIN", courses);
        request.getRequestDispatcher("deleteMaterial.jsp").forward(request, response);
    }
}
