package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {
  public static void main(String[] args) {
    //Initialization
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    //code
    try {
      Order order = new Order();
      em.persist(order);
//      order.addOrderItem(new OrderItem());

      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);

      em.persist(orderItem);

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