package shop.mtcoding.shop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Table(name = "product_tb")
@Entity //깃발을 보고 필드를 분석하고 클래스명으로 테이블명을 만들어
public class Product {
    @Id //필드명보면서 필드를 만들어 내고
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프라이머리키 만드는거)
    private Integer id;
    private String name;
    private String price;
    private Integer qty;
}
