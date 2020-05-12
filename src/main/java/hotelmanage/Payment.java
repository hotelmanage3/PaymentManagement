package hotelmanage;


import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;


@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int PaymentId; // 생성
    private int reservationNumber; // 받아야 되고
    private int PaymentPrice; // 받아야되고
    private String reserveStatus; //받아야되고
    private String PaymentStatus;//결재될떄 "Y" 로 셋팅

    @PostPersist
    public void onPrePersist() {

        if ("reserve".equals(reserveStatus) ) {
            System.out.println("=============결재 승인 처리중=============");
            CompletePayment PaymentCompleted = new CompletePayment();
            BeanUtils.copyProperties(this, PaymentCompleted);
            PaymentStatus = "Y";
            PaymentCompleted.setPaymentId(PaymentId);
            System.out.printf("PaymentId : %d\n",PaymentId);
            PaymentCompleted.setReservationNumber(reservationNumber);
            System.out.printf("ReservationNumber : %d\n",reservationNumber);
            PaymentCompleted.setPaymentPrice(PaymentPrice);
            System.out.printf("PaymentPrice : %d\n",PaymentPrice);
            PaymentCompleted.setReservationStatus(reserveStatus);
            System.out.printf("ReservationStatus : %s\n",reserveStatus);
            PaymentCompleted.setPaymentStatus(PaymentStatus);
            System.out.printf("PaymentStatus : %s\n",PaymentStatus);

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void beforeCommit(boolean readOnly) {
                    PaymentCompleted.publish();
                }
            });

            try {
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
                System.out.println("=============결재 승인 완료=============");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        reservationNumber = reservationNumber;
    }

    public int getPaymentPrice() {
        return PaymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        PaymentPrice = paymentPrice;
    }

    public String getReservationStatus() {
        return reserveStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        reserveStatus = reservationStatus;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}
