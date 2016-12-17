package servlet;

import dto.CourseDTO;
import dto.MaterialDTO;
import dto.StudentDTO;
import ejbservices.AdminBeanRemote;
import ejbservices.ProfBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//To access the methods go to localhost:8080/Web/servlet/project3
@Path("/project3")
public class RESTgetStudents {

    ProfBeanRemote profBeanRemote;
    AdminBeanRemote adminBeanRemote;

    public RESTgetStudents() throws NamingException {
       this.profBeanRemote = (ProfBeanRemote) InitialContext.doLookup("java:global/Web/ProfBean!ejbservices.ProfBeanRemote");
        this.adminBeanRemote = (AdminBeanRemote) InitialContext.doLookup("java:global/Web/AdminBean!ejbservices.AdminBeanRemote");
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

    @GET
    @Path("getstudents")
    @Produces({MediaType.APPLICATION_JSON})
    public List<StudentDTO> getAllStudents(@QueryParam("course") String name) {
        System.out.println("getAllStudents started");

        List<StudentDTO> listStudents = profBeanRemote.getStudentsByCourse(name,true);

        return listStudents;
    }

    //TODO CHANGE THIS METHOD, IT'S GIVING BODE HUEHUEHUE
    @GET
    @Path("getmaterials")
    @Produces({MediaType.APPLICATION_XML})
    public List<List<MaterialDTO>> getAllMaterials() {
        System.out.println("getAllMaterials started");

        List<CourseDTO> listCourses = adminBeanRemote.getCourses();

        List<List<MaterialDTO>> listAllMaterials = new ArrayList<>();

        for(CourseDTO course : listCourses){
            listAllMaterials.add(adminBeanRemote.getMaterials(course.getCourseName()));
        }

        return listAllMaterials;
    }
}


