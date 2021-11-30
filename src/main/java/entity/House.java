package entity;

import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name = "getAllHousePoints",
        query = "select * from house h inner join house_points hp on h.id=hp.id;",
        resultClass=House.class
)
@Table(name="house")

public class House {
    private int id;
    private String name;
    private Person personByHeadTeacher;

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

        House house = (House) o;

        if (id != house.id) return false;
        if (name != null ? !name.equals(house.name) : house.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "head_teacher", referencedColumnName = "id", nullable = false)
    public Person getPersonByHeadTeacher() {
        return personByHeadTeacher;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personByHeadTeacher=" + personByHeadTeacher +
                '}';
    }

    public void setPersonByHeadTeacher(Person personByHeadTeacher) {
        this.personByHeadTeacher = personByHeadTeacher;
    }
}
