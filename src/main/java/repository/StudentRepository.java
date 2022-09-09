    package repository;

    import congifuration.DatabaseConfiguration;
    import dto.ResponseDto;
    import entity.Student;
    import org.hibernate.HibernateException;
    import org.hibernate.Session;
    import org.hibernate.SessionFactory;
    import org.hibernate.Transaction;

    public class StudentRepository {
        private static StudentRepository studentRepository;
        private Session session;
        private Transaction transaction;
        private SessionFactory sessionFactory;

        private StudentRepository(){
            session = DatabaseConfiguration.getSession();
            transaction = session.getTransaction();

            sessionFactory = DatabaseConfiguration.getSessionFactory();
        }

        public static StudentRepository getInstance(){
            if (studentRepository != null){
                return studentRepository;
            }

            studentRepository = new StudentRepository();

            return studentRepository;
        }

        public Student findById(Integer id){
    //        session.find();
    //        session.get();
    //        session.load(Student.class, id);
            return session.find(Student.class, id);
        }

        public Student loadById(Integer id){
            Session ss = sessionFactory.openSession();
            try {
                return ss.load(Student.class, id);
            }catch (HibernateException ex){
                return null;
            }finally {
                ss.close();
            }
        }

//        public ResponseDto<Student> addStudent(Student student){
//            try {
//                transaction.begin();
//                session.persist(student);
//                transaction.commit();
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//                transaction.rollback();
//            }
//
//            return new ResponseDto<>(true, student, "OK");
//        }

        public ResponseDto<Student> addStudent(Student student){
            Session s = sessionFactory.openSession();
            Transaction t = s.beginTransaction();
            try{
                s.persist(student);
                s.persist(student.getCourse());
                t.commit();
            }catch (Exception e){
                e.printStackTrace();
                t.rollback();
            }finally {
                session.close();
            }

            return new ResponseDto<>(true, student, "OK");
        }
    }
