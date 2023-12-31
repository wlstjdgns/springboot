package shop.mtcoding.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Table(name = "product_tb")
@Entity //깃발을 보고 필드를 분석하고 클래스명으로 테이블명을 만들어 //오브젝트매핑은 엔티티만 해준다.
public class Product {
    @Id //필드명보면서 필드를 만들어 내고
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프라이머리키 만드는거)
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    @ManyToOne //다수대1 참조키 설정해주는 방법 연관관계를 잘 따지자
    private Seller seller; //DB는 객체를 저장할 수 없지만 자바는 여기다 객체를 저장할 수 있다.
    //ORM 해주는 방법
}
