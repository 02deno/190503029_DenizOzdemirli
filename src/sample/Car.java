package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;

public class Car {

    private int id;
    private String name; //Nissan Altima
    private String make; //brand of the vehicle : Nissan
    private String model; //specific vehicle model : Altima
    private String licenseNumber;
    private String year; //the car'S year model
    private boolean available;
    private Customer customer;
    private Image image;
    private int maxPerson; //capacity
    private CarAddress carAddress;
    private ArrayList<Lease> leases;
    private double priceProDay;
    private String insuranceCompName;
    private String insuranceCompPhoneNumber;
    private double currentKmstatus;
    private String nextMaintenance;
    private double fuelIndicator;
    private ArrayList<Customer> customers = new ArrayList<>();

    public Car(int id,String name,String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public Car(int id, String name, String make, String model, String licenseNumber, String year, boolean available, Customer customer, Image image, int maxPerson, CarAddress carAddress, ArrayList<Lease> leases, double priceProDay, String insuranceCompName, String insuranceCompPhoneNumber, double currentKmstatus, String nextMaintenance, double fuelIndicator) {
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
        this.priceProDay = priceProDay;
        this.insuranceCompName = insuranceCompName;
        this.insuranceCompPhoneNumber = insuranceCompPhoneNumber;
        this.currentKmstatus = currentKmstatus;
        this.nextMaintenance = nextMaintenance;
        this.fuelIndicator = fuelIndicator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
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

    public void setPriceProDay(double priceProDay) {
        this.priceProDay = priceProDay;
    }

    public double getPriceProDay() {
        return priceProDay;
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

    public void setNextMaintenance(String nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public String getNextMaintenance() {
        return nextMaintenance;
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
        return "Id: "+ id +"\n" +name + "\n"  + licenseNumber ;
    }

    public String toString3() {
        return "Id: "+ id +"\nName: " +name + " "+year+" \nLicense Number: "  + licenseNumber ;
    }
    public String toString2() {
        if(this.isAvailable()) {
            return  make  + "-"+ model + "(" + year +") \n" + maxPerson +  " Seats \nPrice Per Day: " + priceProDay + " £\n"+"Available: Yes";
        }else {
            return  make  + "-"+ model + "(" + year +") \n" + maxPerson +  " Seats \nPrice Per Day: " + priceProDay + " £\n"+"Available: No";
        }

    }
}
