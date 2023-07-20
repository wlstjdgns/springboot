package shop.mtcoding.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        // String 타입의 name 변수를 "홍길동"으로 초기화합니다.
        String name = "홍길동";

        // request 객체에 "price"라는 이름으로 name 변수를 설정합니다.
        request.setAttribute("name", name);

        // ArrayList 타입의 list 변수를 생성하고, "바나나", "딸기", "참외"를 추가합니다.
        ArrayList<String> list = new ArrayList<>();
        list.add("바나나");
        list.add("딸기");
        list.add("참외");

        // request 객체에 "banana", "ddalgi", "chamwei"라는 이름으로 list의 각 요소를 설정합니다.
//        request.setAttribute("banana", list.get(0));
//        request.setAttribute("ddalgi", list.get(1));
//        request.setAttribute("chamwei", list.get(2));

        // request 객체에 "lost"라는 이름으로 list를 설정합니다.
        request.setAttribute("lost", list);

        // "hello" 뷰를 반환합니다.
        return "hello";
    }
}