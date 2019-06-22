package exort.se418ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String homepage(){
        return restTemplate.getForEntity("http://WORDLADDER/", String.class).getBody();
    }

    @GetMapping("/hello/{who}")
    public String hello(@PathVariable("who") String who){
        return restTemplate.getForEntity("http://WORDLADDER/hello/"+who, String.class).getBody();
    }

    @GetMapping("/sayhello/{who}")
    public String sayHello(@PathVariable("who") String who){
        return restTemplate.getForEntity("http://WORDLADDER/sayhello/"+who, String.class).getBody();
    }

    @GetMapping("/ladder/{begin}/{end}")
    public String generateLadder(@PathVariable("begin") String begin, @PathVariable("end") String end){
        return restTemplate.getForEntity("http://WORDLADDER/ladder/"+begin+"/"+end, String.class).getBody();
    }

    @GetMapping("/github")
    public String ladderDogToCat(){
        return restTemplate.getForEntity("http://WORDLADDER/github", String.class).getBody();
    }
}
