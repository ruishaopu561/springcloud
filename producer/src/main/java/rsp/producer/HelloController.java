package rsp.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

//    @GetMapping(value = "/hello")
//    public String index()
//    {
//        List<ServiceInstance> instances = client.getInstances("hello-service");
//        for (ServiceInstance instance : instances) {
//            logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
//        }
//        return "Hello World!";
//    }

    @GetMapping(value = "/hello1")
    public String hello1(@RequestParam String name) {
        return "hello " + name + "!";
    }

    @GetMapping(value = "/hello2")
    public Book hello2(@RequestHeader String name, @RequestHeader String author, @RequestHeader Integer price) throws UnsupportedEncodingException {
        Book book = new Book();
        book.setName(URLDecoder.decode(name,"UTF-8"));
        book.setAuthor(URLDecoder.decode(author,"UTF-8"));
        book.setPrice(price);
        System.out.println(book);
        return book;
    }

    @PostMapping(value = "/hello3")
    public String hello3(@RequestBody Book book) {
        return "书名为：" + book.getName() + ";作者为：" + book.getAuthor();
    }
}
