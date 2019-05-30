package rsp.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonconsumerApplication.class, args);
    }

//    @LoadBalanced注解，表示开启客户端负载均衡。
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public HystrixCommandAspect hystrixCommandAspect() {
//        return new HystrixCommandAspect();
//    }
}
