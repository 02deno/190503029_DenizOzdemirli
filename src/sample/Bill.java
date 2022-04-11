package sample;

import java.util.Date;

public class Bill {

    private String id;
    private Customer customer;
    private Car car;
    private double price;
    private Date date;
    private boolean paid;
    private PaymentInformation paymentInformation;
    private Lease lease;

    public Bill(String id, Customer customer, Car car, double price, Date date, boolean paid, PaymentInformation paymentInformation, Lease lease) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.price = price;
        this.date = date;
        this.paid = paid;
        this.paymentInformation = paymentInformation;
        this.lease = lease;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public Lease getLease() {
        return lease;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", car=" + car +
                ", price=" + price +
                ", date=" + date +
                ", paid=" + paid +
                ", paymentInformation=" + paymentInformation +
                '}';
    }
}
