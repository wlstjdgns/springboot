package shop.mtcoding.shop.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class SellerRepository {
    @Autowired
    private EntityManager em; //디펜더씨 인젝션

    @Transactional
    public void save(String name, String email){
        Query query = em.createNativeQuery("insert into seller_tb(name, email) values(:name, :email)");
        query.setParameter("name", name);
        query.setParameter("email", email);
        query.executeUpdate();
    }
}
