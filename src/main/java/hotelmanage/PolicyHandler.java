package hotelmanage;

import hotelmanage.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository PaymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentRequested_Payment(@Payload PaymentCompleted PaymentCompleted){
        if(PaymentCompleted.isMe()){
            System.out.println("##### listener PaymentCompleted : " + PaymentCompleted.toJson());
            Payment Payment = new Payment();

            Payment.setPaymentStatus("Y");
            PaymentRepository.save(Payment);

        }
    }

}
