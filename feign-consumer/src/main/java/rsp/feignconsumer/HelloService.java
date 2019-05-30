package rsp.feignconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//通过@FeignClient注解来指定服务名进而绑定服务，然后再通过SpringMVC中提供的注解来绑定服务提供者提供的接口
@FeignClient("hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();

//    在SpringMVC中，@RequestParam和@RequestHeader注解，如果我们不指定value，则默认采用参数的名字作为其value，但是在Feign中，这个value必须明确指定，否则会报错。
    @GetMapping(value = "/hello1")
    String hello(@RequestParam("name") String name);

    @GetMapping(value = "/hello2")
    Book hello(@RequestParam("name") String name, @RequestHeader("author") String author, @RequestHeader("price") Integer price);

    @PostMapping(value = "/hello3")
    String hello(@RequestBody Book book);
}
