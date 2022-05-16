package sample;

import java.util.Date;

public class PaymentInformation {

    private int id;
    private Customer customer;
    private String cardType;
    private String cardNumber;
    private String expiryDate;
    private int cardCode;

    public PaymentInformation(int id, Customer customer, String cardType, String cardNumber, String expiryDate, int cardCode) {
        this.id = id;
        this.customer = customer;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardCode = cardCode;
    }

    public PaymentInformation(int id, String cardType, String cardNumber, String expiryDate, int cardCode) {
        this.id = id;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardCode = cardCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setCardCode(int cardCode) {
        this.cardCode = cardCode;
    }

    public int getCardCode() {
        return cardCode;
    }

    @Override
    public String toString() {
        return cardType  + "\n" + cardNumber + "\n" + expiryDate + ", " + cardCode;
    }
}
