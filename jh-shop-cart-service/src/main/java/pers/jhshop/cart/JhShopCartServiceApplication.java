package pers.jhshop.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = "pers.jhshop.cart.mapper")
@SpringBootApplication
public class JhShopCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JhShopCartServiceApplication.class, args);
    }

}
