package hotelmanage;


import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int PaymentId; // 생성
    private int ReservationNumber; // 받아야 되고
    private int PaymentPrice; // 받아야되고
    private String ReservationStatus; //받아야되고
    private String PaymentStatus;//결재될떄 "Y" 로 셋팅

    @PrePersist
    public void onPrePersist() {

        if ("reserve".equals(ReservationStatus) ) {
            System.out.println("=============결재 승인 처리중=============");
            PaymentCompleted paymentCompleted = new PaymentCompleted();

            setPaymentStatus("Y");
            paymentCompleted.setPaymentId(PaymentId);
            System.out.printf("PaymentId : %d\n",PaymentId);
            paymentCompleted.setReservationNumber(ReservationNumber);
            System.out.printf("ReservationNumber : %d\n",ReservationNumber);
            paymentCompleted.setPaymentPrice(PaymentPrice);
            System.out.printf("PaymentPrice : %d\n",PaymentPrice);
            paymentCompleted.setReservationStatus(ReservationStatus);
            System.out.printf("ReservationStatus : %s\n",ReservationStatus);
            paymentCompleted.setPaymentStatus(PaymentStatus);
            System.out.printf("PaymentStatus : %s\n",PaymentStatus);
            BeanUtils.copyProperties(this, paymentCompleted);
            paymentCompleted.publishAfterCommit();

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
        return ReservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        ReservationNumber = reservationNumber;
    }

    public int getPaymentPrice() {
        return PaymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        PaymentPrice = paymentPrice;
    }

    public String getReservationStatus() {
        return ReservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        ReservationStatus = reservationStatus;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}


