package repository;

import dto.ResponseDto;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class StudentRepository {
    private Session session;
    private Transaction transaction;

    public StudentRepository(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate/hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(ssr).buildMetadata();

        SessionFactory sessionFactory = metadata.buildSessionFactory();

        session = sessionFactory.openSession();
        transaction = session.getTransaction();

    }

    public ResponseDto<Student> addStudent(Student student){
        transaction.begin();
        session.persist(student);
        transaction.commit();

        return new ResponseDto<>(true, student, "OK");
    }
}
