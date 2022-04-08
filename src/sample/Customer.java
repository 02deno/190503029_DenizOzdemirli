package sample;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {

    private String drivingLicenseNumber;
    private String phoneNumber2;
    private PaymentInformation paymentInformation;
    private Car rentedCar;
    private Lease currentLease;
    private ArrayList<Lease> previousLeases = new ArrayList<>();
    private Employee supervisingEmployee ;
    private boolean returned = false;

    public Customer(String id) throws InvalidID_Exception {
        super(id);
    }

    public Customer(String id,String name,String surname) throws InvalidID_Exception {
        super(id,name,surname);
    }

    public Customer(String id,String name,String surname,Address address,String email,String phoneNumber1) throws InvalidID_Exception {
        super(id, name, surname, address, email, phoneNumber1);
    }

    public Customer(String id,String name,String surname,Address address,String email,String phoneNumber1,String drivingLicenseNumber,String phoneNumber2,PaymentInformation paymentInformation,Car rentedCar,Lease currentLease,ArrayList<Lease> previousLeases,Employee supervisingEmployee) throws InvalidID_Exception {
        super(id, name, surname, address, email, phoneNumber1);
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.phoneNumber2 = phoneNumber2;
        this.paymentInformation = paymentInformation;
        this.rentedCar = rentedCar;
        this.currentLease = currentLease;
        this.previousLeases = previousLeases;
        this.supervisingEmployee = supervisingEmployee;
    }



    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }


    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public void setCurrentLease(Lease currentLease) {
        this.currentLease = currentLease;
    }

    public Lease getCurrentLease() {
        return currentLease;
    }

    public void setPreviousLeases(ArrayList<Lease> previousLeases) {
        this.previousLeases = previousLeases;
    }

    public ArrayList<Lease> getPreviousLeases() {
        return previousLeases;
    }

    public void setSupervisingEmployee(Employee supervisingEmployee) {
        this.supervisingEmployee = supervisingEmployee;
    }

    public Employee getSupervisingEmployee() {
        return supervisingEmployee;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public boolean isReturned() {
        return returned;
    }

    @Override
    public String toString() {
        return "Customer\n" + "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\n";
    }

    public void addLease(Lease lease) {
        previousLeases.add(lease);
    }

    public void returnCar() {
        returned = true;
    }
}
