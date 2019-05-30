package rsp.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//1.RestTemplate执行网络请求的操作我们放在HelloService中来完成。
//2.error方法是一个请求失败时回调的方法。
//3.在hello方法上通过@HystrixCommand注解来指定请求失败时回调的方法。

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String hello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        return responseEntity.getBody();
    }

    public String error(){
        return "error";
    }
}
