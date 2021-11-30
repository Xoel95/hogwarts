import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("consultarAlumnosMaestro", Person.class);
        q.setParameter(1, 97);
        List<Person> l = q.getResultList();
        for (Person p : l) {
            System.out.println(p.toString());
        }
    }
}
