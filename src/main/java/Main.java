import entity.Course;
import entity.House;
import entity.HousePoints;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

        EntityManager em4 = emf.createEntityManager();
        List<HousePoints> houses =
                em4.createNamedQuery("getAllTeachersAndTheirPoints", HousePoints.class).getResultList();
        for (HousePoints h : houses) {
            System.out.println(h.toString());
        }

        EntityManager em5 = emf.createEntityManager();
        List<Course> coursesProfessors = em5.createNamedQuery("getCoursesProfessors", Course.class).getResultList();
        for (Course c : coursesProfessors) {
            System.out.println(c.toString());
        }

        EntityManager em6 = emf.createEntityManager();
        Query q6 = em6.createQuery("SELECT p FROM Person p WHERE p.id = " +
                "(SELECT h.personByGiver.id FROM HousePoints h WHERE h.points = " +
                "(SELECT MAX(h.points) FROM HousePoints h)) and p.id in (select c.teacher.id from Course c)");
        Person p6 = (Person) q6.getSingleResult();
        System.out.println(p6.toString());

        EntityManager em7 = emf.createEntityManager();
        Query q7 = em7.createQuery("DELETE FROM Person WHERE id = ?1");
        q7.setParameter(1, 1);
        em7.getTransaction().begin();
        q7.executeUpdate();
        em7.getTransaction().commit();

//        SessionFactory sessionFactory;
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("from Person where id = :id ");
//        query.setParameter("id", 5);
//        List<?> list = ((org.hibernate.query.Query<?>) query).list();
//        Person p = (Person)list.get(0);
//        System.out.println(p);

    }
}
