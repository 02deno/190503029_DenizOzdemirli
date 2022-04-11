package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;

public class Car {

    private String id;
    private String name; //Nissan Altima
    private String make; //brand of the vehicle : Nissan
    private String model; //specific vehicle model : Altima
    private String licenseNumber;
    private int year; //the car'S year model
    private boolean available;
    private Customer customer;
    private Image image;
    private int maxPerson; //capacity
    private CarAddress carAddress;
    private ArrayList<Lease> leases;
    private double priceProKm;
    private String insuranceCompName;
    private String insuranceCompPhoneNumber;
    private double currentKmstatus;
    private Date nextMaintenance;
    private ArrayList<String> damages;
    private double fuelIndicator;
    private ArrayList<Customer> customers = new ArrayList<>();

    public Car(String id, String name, String make, String model, String licenseNumber, int year, boolean available, Customer customer, Image image, int maxPerson, CarAddress carAddress, ArrayList<Lease> leases, double priceProKm, String insuranceCompName, String insuranceCompPhoneNumber, double currentKmstatus, Date nextMaintenance, ArrayList<String> damages, double fuelIndicator) {
        this.id = id;
        this.name = name;
        this.make = make;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.year = year;
        this.available = available;
        this.customer = customer;
        this.image = image;
        this.maxPerson = maxPerson;
        this.carAddress = carAddress;
        this.leases = leases;
        this.priceProKm = priceProKm;
        this.insuranceCompName = insuranceCompName;
        this.insuranceCompPhoneNumber = insuranceCompPhoneNumber;
        this.currentKmstatus = currentKmstatus;
        this.nextMaintenance = nextMaintenance;
        this.damages = damages;
        this.fuelIndicator = fuelIndicator;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setCarAddress(CarAddress carAddress) {
        this.carAddress = carAddress;
    }

    public CarAddress getCarAddress() {
        return carAddress;
    }


    public void setLeases(ArrayList<Lease> leases) {
        this.leases = leases;
    }

    public ArrayList<Lease> getLeases() {
        return leases;
    }

    public void setPriceProKm(double priceProKm) {
        this.priceProKm = priceProKm;
    }

    public double getPriceProKm() {
        return priceProKm;
    }

    public void setInsuranceCompName(String insuranceCompName) {
        this.insuranceCompName = insuranceCompName;
    }

    public String getInsuranceCompName() {
        return insuranceCompName;
    }

    public void setInsuranceCompPhoneNumber(String insuranceCompPhoneNumber) {
        this.insuranceCompPhoneNumber = insuranceCompPhoneNumber;
    }

    public String getInsuranceCompPhoneNumber() {
        return insuranceCompPhoneNumber;
    }

    public void setCurrentKmstatus(double currentKmstatus) {
        this.currentKmstatus = currentKmstatus;
    }

    public double getCurrentKmstatus() {
        return currentKmstatus;
    }

    public void setNextMaintenance(Date nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public Date getNextMaintenance() {
        return nextMaintenance;
    }

    public void setDamages(ArrayList<String> damages) {
        this.damages = damages;
    }

    public ArrayList<String> getDamages() {
        return damages;
    }

    public void setFuelIndicator(double fuelIndicator) {
        this.fuelIndicator = fuelIndicator;
    }

    public double getFuelIndicator() {
        return fuelIndicator;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}
