package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
  public static void main(String[] args) {
    //Initialization
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    //code
    try {
//      Member findMember = em.find(Member.class, 1L);
//      findMember.setName("helloA");
      List<Member> result = em.createQuery("select m from Member as m", Member.class)
        .setFirstResult(0)
        .setMaxResults(10)
        .getResultList();
      for (Member member : result) {
        System.out.println("member.name = " + member.getName());
        System.out.println("member.id = " + member.getId());
      }
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      //close
      em.close();
    }
    emf.close();
  }
}