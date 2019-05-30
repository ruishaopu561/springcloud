package rsp.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;
import rsp.ribbonconsumer.entity.Book;

public class BookCommand extends HystrixCommand<Book> {

    private RestTemplate restTemplate;

//    定义一个 HystrixCommand 还需定义一个构造函数。这个构造函数十分重要，因为在使用这个 HystrixCommand 时，需要通过构造函数传递参数。
//    在构造函数中，需要调用父构造函数对当前的 HystrixCommand 进行配置。主要的配置主要有三个：GroupKey、ThreadPoolSize 和 Timeout。具体的配置方式有多种，较常用的一种方式是通过一个名为 Setter 的 Builder 类进行配置。
    //    GroupKey 通过 Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserService")) 语句进行配置；
    //    ThreadPoolSize 通过 .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(20)) 语句进行配置；
    //    Timeout 通过 .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100) 语句进行配置；
    public BookCommand(Setter setter, RestTemplate restTemplate){
        super(setter);
        this.restTemplate = restTemplate;
    }

//    通过实现 getFallback() 方法，实现失败逻辑，可以在其中实现降级等功能。
    @Override
    protected Book getFallback(){
        Throwable executionException = getExecutionException();
        System.out.println(executionException.getMessage());
        return new Book("宋诗选注", 88, "钱钟书", "三联书店");
    }

//    通过实现 run() 方法，在其中实现业务逻辑。通常是调用外部类的方法或外部类依赖的方法。
    @Override
    protected Book run() throws Exception {
        return restTemplate.getForObject("http://HELLO-SERVICE/getbook1", Book.class);
    }

}
