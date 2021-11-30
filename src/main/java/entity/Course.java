package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedNativeQuery(
        name = "getCoursesProfessors",
        query = "select * from course c left join person p on p.id=c.teacher_id;",
        resultClass=Course.class
)
@Table(name="course")
public class Course {
    private int id;
    private String name;
    private List<Person> people;
    private Person teacher;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name="enrollment",
            joinColumns={@JoinColumn(name="course_enrollment")},
            inverseJoinColumns={@JoinColumn(name="person_enrollment")})
    public List<Person> getPeople() { return people; }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @ManyToOne
    @JoinColumn (name = "teacher_id")
    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        String s = "El curso de nombre " + name + " y id " + id;
        if (teacher != null){
            s = s + " esta impartido por " + teacher.getFirstName() + " " + teacher.getLastName();
        }
        return s;
    }
}