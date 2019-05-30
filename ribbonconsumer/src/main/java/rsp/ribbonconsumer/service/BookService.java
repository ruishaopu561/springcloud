package rsp.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.RestTemplate;
import rsp.ribbonconsumer.entity.Book;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

public class BookService {

    @Autowired
    RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "error")
//    public Future<Book> test3() {
//        return new AsyncResult<Book>() {
//            @Override
//            public Book invoke() {
//                return restTemplate.getForObject("http://HELLO-SERVICE/getbook1", Book.class);
//            }
//        };
//    }

    @HystrixCommand(fallbackMethod = "error")
    public Observable<Book> test4() {
        return Observable.create(new Observable.OnSubscribe<Book>() {
            @Override
            public void call(Subscriber<? super Book> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Book book = restTemplate.getForObject("http://HELLO-SERVICE/getbook1", Book.class);
                    subscriber.onNext(book);
                    subscriber.onCompleted();
                }
            }
        });
    }

    public String error() {
        return "error";
    }

}
