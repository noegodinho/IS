package generated;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ListCourses")
public class ListCourses {
    private List<CourseDTO> courses;


    public ListCourses() {
        this.courses = new ArrayList<>();
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public void addCourse(CourseDTO c) {
        this.courses.add(c);
    }
    
    public String toString(){
    	return this.getCourses().toString();
    }
}