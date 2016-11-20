package servlet;

import dto.*;
import ejbservices.ClientBeanRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "/ListMaterialsServlet")
public class ListMaterialsServlet extends HttpServlet {

    @EJB
    private ClientBeanRemote ejbremote;
    private Logger logger;
    private List<CourseDTO> courses;
    private List<MaterialDTO> materials;
    private UtilsServlet utils;

    public ListMaterialsServlet(){
        super();
        this.logger = LoggerFactory.getLogger(ListMaterialsServlet.class);
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
        else if (request.getParameter("action").equals("download")){
            downloadMaterials(request, response);
        }
        else{
            doGet(request, response);
        }
    }

    private void downloadMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("materialSelected");
        logger.info("FILE SELECTED: "+fileName);
        logger.info("MATERIALS SIZE: "+materials.size());

        if(fileName.equals("Choose your option")){
            utils.popupMessage(response,"Choose a material","listMaterials");
        }


        else {
            String fileTimeStamp = null;
            for (MaterialDTO material : materials){
                if (material.getFilename().equals(fileName)) {
                    fileTimeStamp = material.getTimestamp();
                    logger.info(fileTimeStamp+ "FOUND");
                }
            }

            if (fileTimeStamp!= null) {
                File file = new File(fileTimeStamp);
                logger.info("File location on server::" + file.getAbsolutePath());
                ServletContext ctx = getServletContext();
                InputStream fis = new FileInputStream(file);
                String mimeType = ctx.getMimeType(file.getAbsolutePath());
                response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                ServletOutputStream os = response.getOutputStream();
                byte[] bufferData = new byte[1024];
                int read = 0;
                while ((read = fis.read(bufferData)) != -1) {
                    os.write(bufferData, 0, read);
                }
                os.flush();
                os.close();
                fis.close();
                System.out.println("File downloaded at client successfully");
            }

            else{
                utils.popupMessage(response, "Error retrieving material", "listMaterials");
            }


        }
    }

    private void listMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");

        materials = this.ejbremote.getMaterials(courseName);

        request.setAttribute("materialsList", materials);
        request.getRequestDispatcher("listMaterials.jsp").forward(request, response);

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
