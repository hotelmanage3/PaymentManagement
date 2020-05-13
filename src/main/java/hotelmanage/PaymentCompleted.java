package hotelmanage;

public class PaymentCompleted extends AbstractEvent{
    private int ReservationNumber;
    private int PaymentId;
    private int PaymentPrice;
    private String ReservationStatus;

    public String getReservationStatus() {
        return ReservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        ReservationStatus = reservationStatus;
    }



    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    private String PaymentStatus;


    public PaymentCompleted(){
        super();
    }

    public int getReservationNumber() {
        return ReservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.ReservationNumber = reservationNumber;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        this.PaymentId = paymentId;
    }

    public int getPaymentPrice() {
        return PaymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        this.PaymentPrice = paymentPrice;
    }

}
