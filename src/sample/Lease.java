package sample;

import java.util.Date;

public class Lease {

    private int id;
    private Customer customer;
    private Car rentedCar;
    private String startDate;
    private String endDate;
    private double price;
    private int duration;
    private double startKm;
    private double endKm;
    private double priceProDay;
    private double insuranceCosts;
    private boolean closed;
    private Employee supervisingEmployee;
    private Bill bill;

    public Lease(int id) {
        this.id = id;
    }

    public Lease(int id,Customer customer,Employee employee) {
        this.id = id;
        this.customer = customer;
        this.supervisingEmployee = employee;

    }

    public Lease(int id,Customer customer,Car car) {
        this.id = id;
        this.customer = customer;
        this.rentedCar = car;

    }

    public Lease(int id, Customer customer, Car rentedCar, String startDate, String endDate, double price, int duration, double startKm, double endKm, double priceProDay, double insuranceCosts, boolean closed, Employee supervisingEmployee, Bill bill) {
        this.id = id;
        this.customer = customer;
        this.rentedCar = rentedCar;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.duration = duration;
        this.startKm = startKm;
        this.endKm = endKm;
        this.priceProDay = priceProDay;
        this.insuranceCosts = insuranceCosts;
        this.closed = closed;
        this.supervisingEmployee = supervisingEmployee;
        this.bill = bill;
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

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
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

    public void setPriceProDay(double priceProDay) {
        this.priceProDay = priceProDay;
    }

    public double getPriceProDay() {
        return priceProDay;
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
        return (priceProDay*duration) + insuranceCosts;
    }

    @Override
    public String toString() {
        return "Lease\n" +
                "id=" + id  +
                "\ncustomer:\n" + customer +
                "\nrentedCar=" + rentedCar +
                "\nstartDate=" + startDate +
                ",endDate=" + endDate +
                "\nprice=" + price +
                "\nduration=" + duration +
                "\nstartKm=" + startKm +
                ", endKm=" + endKm +
                "\npricePerDay=" + priceProDay +
                ", insuranceCosts=" + insuranceCosts +
                "\nclosed=" + closed +
                "\nsupervisingEmployee:\n" + supervisingEmployee ;
    }
}
