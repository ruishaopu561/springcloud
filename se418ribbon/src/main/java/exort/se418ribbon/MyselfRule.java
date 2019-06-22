package exort.se418ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return new NewRule();
    }
}
