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
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("member1");
      member.changeTeam(team);
      em.persist(member);

      em.flush();
      em.clear();

      Team findTeam = em.find(Team.class, team.getId());
      List<Member> members = findTeam.getMembers();

      System.out.println("members = " + findTeam);

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