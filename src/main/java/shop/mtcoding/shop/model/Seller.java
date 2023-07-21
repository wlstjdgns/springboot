package shop.mtcoding.shop.model;

import lombok.*;

import javax.persistence.*;

@Getter@Setter
@Table(name = "seller_tb")
@Entity //깃발을 보고 필드를 분석하고 클래스명으로 테이블명을 만들어 //오브젝트매핑은 엔티티만 해준다.
public class Seller {
    @Id //필드명보면서 필드를 만들어 내고
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프라이머리키 만드는거)
    private Integer id;
    private String name;
    private String email;

}
