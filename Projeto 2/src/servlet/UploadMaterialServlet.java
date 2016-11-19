package servlet;

import ejbservices.ProfBeanRemote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Timestamp;

@WebServlet(name = "/UploadMaterialServlet")
@MultipartConfig
public class UploadMaterialServlet extends HttpServlet {
    @EJB
    private ProfBeanRemote ejbremote;
    private Logger logger;
    private UtilsServlet utils;

    public UploadMaterialServlet(){
        super();
        this.logger = LoggerFactory.getLogger(UploadMaterialServlet.class);
        this.utils = new UtilsServlet();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();

        OutputStream outStream;

        if (!courseName.isEmpty()) {

            try {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String[] extension = fileName.split("\\.");
                String fileTimestamp = timestamp.toString() + "." + extension[extension.length - 1];
                File file = new File(fileTimestamp);

                outStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];

                int length;

                while ((length = fileContent.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }

                fileContent.close();
                outStream.close();

                if (this.ejbremote.uploadMaterial(fileName, fileTimestamp, courseName)) {
                    logger.info("File: " + fileName + " copied successfully");
                    utils.popupMessage(response, "Material successfully uploaded","uploadMaterial");
                } else {
                    logger.error("Error copying file");
                    utils.popupMessage(response, "Error uploading file, please try again","uploadMaterial");
                }
            } catch (IOException ioe) {
                logger.error("Error copying file");
                utils.popupMessage(response, "Error uploading file, please try again","uploadMaterial");
            }
        }

        else{
            utils.popupMessage(response, "Specify a course", "uploadMaterial");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("action").equals("upload")){
            processRequest(request, response);
        }
        else if(request.getParameter("action").equals("logout")){
            session.removeAttribute("user");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
