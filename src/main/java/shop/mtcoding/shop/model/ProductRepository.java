package shop.mtcoding.shop.model;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@Repository //컴퍼넌트 스캔 즉 알아서 new가 된다는 거지
public class ProductRepository { //DAO와 다르게 범용적인 저장소로 쓰인다 레파지토리

    @Autowired //의존성 주입을 위해 사용/객체생성시 필요한 객체주입
    private EntityManager em; //디펜더씨 인젝션

    public Product findByIdJoinSeller(int id){
        Query query = em.createNativeQuery("select *\n" +
                "from product_tb pt\n" +
                "inner join seller_tb st\n" +
                "on pt.seller_id = st.id\n" +
                "where pt.id = :id", Product.class);
        query.setParameter("id",id);
        Product product=(Product) query.getSingleResult();
        return product;

    }

    public ProductDTO findByIdDTO(int id){
        Query query = em.createNativeQuery("select id,name,price,qty,'설명' des from product_tb where id = :id");
        query.setParameter("id",id);

        JpaResultMapper mapper = new JpaResultMapper();
        ProductDTO productDTO = mapper.uniqueResult(query, ProductDTO.class);
        //이거 원리확실하게 이해하기

        return productDTO;
    }//as는 생략가능하다  조회할거니까 매핑할 클래스를 적어준것이다.
 //훨씬 쉬운 방법이 있다 손가락만 튕기면 되는정도
    @Transactional
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }

    @Transactional
    public void saveWithFK(String name, int price, int qty, int sellerId){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty, seller_id) values(:name, :price, :qty, :sellerId)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.setParameter("sellerId", sellerId);
        query.executeUpdate();
    }
    @Transactional
    public void update(int id, String name, int price, int qty){
        Query query = em.createNativeQuery("update product_tb set name = :name, price = :price, qty = :qty where id=:id"); //캡슐화
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);


        query.executeUpdate();
    }


    @Transactional//spring 트랜잭션(임포트 주의)
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }




    public List<Product> findAll() {
       Query query = em.createNativeQuery("select  * from product_tb", Product.class);
       List<Product> productList=query.getResultList();
       return productList;
    }



    public Product findById(int id) { //클라이언트로부터 받은 ID를 얘로 전달해주는거
        Query query = em.createNativeQuery("select  * from product_tb where id = :id", Product.class);
        query.setParameter("id",id);
        Product product=(Product) query.getSingleResult();
        return product;
    }



//    public Product findById2(int id) {
//        Query query = em.createNativeQuery("select * from product_tb where id = :id");
//        // row가 1건
//        // 1, 바나나, 1000, 50
//        Object[] object = (Object[]) query.getSingleResult();
//        int id2 = (int) object[0];
//        String name2 = (String) object[1];
//        int price2 = (int) object[2];
//        int qty2 = (int) object[3];
//
//        Product product = new Product();
//        product.setId(id2);
//        product.setName(name2);
//        product.setPrice(price2);
//        product.setQty(qty2);
//        return product;
//    }
}
