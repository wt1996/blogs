package cn.wangtao.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @ClassName Application
 * @Auth 桃子
 * @Date 2019-5-13 9:03
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = "cn.wangtao")
@MapperScan(basePackages = "cn.wangtao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
