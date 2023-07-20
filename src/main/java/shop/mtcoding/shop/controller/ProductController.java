package shop.mtcoding.shop.controller;

import com.mysql.cj.PreparedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.shop.model.Product;
import shop.mtcoding.shop.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.PrimitiveIterator;


//컨트롤러란 진입점이다.
//메모리에 띄우고 뷰를 넘겨주려고 만드는것이다.
@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product/delete")
    public String delete(int id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/product/update") //product를 줄게
    public String update(int id, String name, int price, int qty) {
        productRepository.update(id, name, price, qty);
        return "redirect:/";
    }

    @GetMapping("/product/{id}") //패스밸리어블 변수로 바인딩 ""경로에서 GET요청
    public String detail(@PathVariable int id, HttpServletRequest Request) {
        Product product = productRepository.findById(id);
        Request.setAttribute("p", product);
        return "detail";
    }


    @GetMapping("/") //메인'/'페이지를 줘
    public String home(HttpServletRequest request) {//디스패쳐서블릿이 여기에 값을 주입
        //메서드를 invoke할때 매개변수를 넘겨줘
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);

        return "home";
    }

    @GetMapping("write") //라이트페이지를 줘
    public String writePage() {
        return "write";
    }

    @PostMapping("/product") //product를 줄게
    public String write(String name, int price, int qty) {
        System.out.println("name: " + name);
        System.out.println("price: " + price);
        System.out.println("qty: " + qty);

        productRepository.save(name, price, qty);
//        respose.sendRedirect("/");
        return "redirect:/"; //컨트롤러의 메서드를 재호출하는 방법

    }


}
