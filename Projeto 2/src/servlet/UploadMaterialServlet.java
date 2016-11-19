package servlet;

import ejbservices.ProfBeanRemote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;

@WebServlet(name = "/UploadMaterialServlet")
@MultipartConfig
public class UploadMaterialServlet extends HttpServlet {
    @EJB
    private ProfBeanRemote ejbremote;
    private Logger logger;

    public UploadMaterialServlet(){
        super();
        this.logger = LoggerFactory.getLogger(UploadMaterialServlet.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action").equals("upload")){
            String courseName = request.getParameter("courseName");
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();

            //TRATAR DO FICHEIRITO AQUI
            //AQUI
            //AQUI CRL
            //CHUPMOS OH BOI
            //ORE WA OCHINCHIN GA DAISUKI NANDAYO (se estiver mal é porque nao fiz copy-paste e escrevi de cabeça)
            //esta perfect :3

            OutputStream outStream;

            try{
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String[] extension = fileName.split("\\.");
                String fileTimestamp = timestamp.toString() + extension[extension.length - 1];
                File file = new File(fileTimestamp);

                outStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];

                int length;

                while((length = fileContent.read(buffer)) > 0){
                    outStream.write(buffer, 0, length);
                }

                fileContent.close();
                outStream.close();

                if(this.ejbremote.uploadMaterial(fileName, fileTimestamp, courseName)){
                    logger.info("File: " + fileName + " copied successfully");
                }

                else{
                    logger.error("Error copying file");
                }
            }catch(IOException ioe){
                logger.error("Error copying file");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
