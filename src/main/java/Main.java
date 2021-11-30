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

        EntityManager em2 = emf.createEntityManager();
        Query q2 = em2.createNamedQuery("personaRecibirPuntosMax", Person.class);
        Person p2 = (Person) q2.getSingleResult();
        System.out.println(p2.toString());

        EntityManager em3 = emf.createEntityManager();
        Query q3 = em3.createNamedQuery("personaDarPuntosMax", Person.class);
        Person p3 = (Person) q3.getSingleResult();
        System.out.println(p3.toString());
    }
}
