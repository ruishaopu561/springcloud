package rsp.ribbonconsumer.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rsp.ribbonconsumer.command.BookCommand;
import rsp.ribbonconsumer.entity.Book;
import rsp.ribbonconsumer.service.BookService;
import rsp.ribbonconsumer.service.HelloService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class ConsumerController {

//    @Autowired
//    private BookService bookService;

    @Autowired
    private HelloService helloService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/ribbon-consumer")
    public String helloController() {
        return helloService.hello();
    }


//    1.获取到BookCommand对象之后，我们有两种方式来执行请求，一种是调用execute方法发起一个同步请求，另一种是调用queue方法发起一个异步请求。
//    2.同步请求中可以直接返回请求结果。
//    3.异步请求中我们需要通过get方法来获取请求结果，在调用get方法的时候也可以传入超时时长。

    @RequestMapping(value = "/test1")
    public Book test1() throws ExecutionException, InterruptedException {
//        编写完 GetUserCommand 之后，使用的时候每次都需要 new 一个新对象，再调用 execute() 方法。注意，不要调用 run() 方法，否则熔断、隔离等功能是不生效的。
//        GroupKey 是 HystrixCommand 不可缺少的配置，其它配置均为可选。
        BookCommand bookCommand = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //同步调用
        Book book1 = bookCommand.execute();
        //异步调用
        Future<Book> queue = bookCommand.queue();

        return queue.get();
    }

//    @RequestMapping("/test3")
//    public Book test3() throws ExecutionException, InterruptedException {
//        Future<Book> bookFuture = bookService.test3();
//        return restTemplate.getForObject("http://HELLO-SERVICE/getbook1", Book.class);
//    }
}
