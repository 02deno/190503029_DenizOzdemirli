package sample;

import java.util.Date;

public class Lease {

    private String id;
    private Customer customer;
    private Car rentedCar;
    private Date startDate;
    private Date endDate;
    private double price;
    private int duration;
    private double startKm;
    private double endKm;
    private double priceProKm;
    private double insuranceCosts;
    private boolean closed;
    private Employee supervisingEmployee;
    private Bill bill;

    public Lease(String id, Customer customer, Car rentedCar, Date startDate, Date endDate, double price, int duration, double startKm, double endKm, double priceProKm, double insuranceCosts, boolean closed, Employee supervisingEmployee, Bill bill) {
        this.id = id;
        this.customer = customer;
        this.rentedCar = rentedCar;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.duration = duration;
        this.startKm = startKm;
        this.endKm = endKm;
        this.priceProKm = priceProKm;
        this.insuranceCosts = insuranceCosts;
        this.closed = closed;
        this.supervisingEmployee = supervisingEmployee;
        this.bill = bill;
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

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setStartKm(double startKm) {
        this.startKm = startKm;
    }

    public double getStartKm() {
        return startKm;
    }

    public void setEndKm(double endKm) {
        this.endKm = endKm;
    }

    public double getEndKm() {
        return endKm;
    }

    public void setPriceProKm(double priceProKm) {
        this.priceProKm = priceProKm;
    }

    public double getPriceProKm() {
        return priceProKm;
    }

    public void setInsuranceCosts(double insuranceCosts) {
        this.insuranceCosts = insuranceCosts;
    }

    public double getInsuranceCosts() {
        return insuranceCosts;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setSupervisingEmployee(Employee supervisingEmployee) {
        this.supervisingEmployee = supervisingEmployee;
    }

    public Employee getSupervisingEmployee() {
        return supervisingEmployee;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }

    public double calculatePrice() {
        return (endKm-startKm)*priceProKm + insuranceCosts;
    }
}
