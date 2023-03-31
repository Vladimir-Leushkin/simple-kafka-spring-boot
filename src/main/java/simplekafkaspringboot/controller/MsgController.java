package simplekafkaspringboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simplekafkaspringboot.dto.Address;
import simplekafkaspringboot.dto.UserDto;

@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    /*@PostMapping
    public void sendOrder(String msgId, String msg){
        kafkaTemplate.send("msg", msgId, msg);
    }*/

    /*@PostMapping
    public void sendMsg(String msgId, String msg){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("msg", msgId, msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }*/

    @PostMapping
    public void sendMsg(Long msgId, UserDto msg){
        msg.setAddress(new Address("RUS", "MSK", "Lenina", 1l, 1l));
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}