package sample;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {

    private String drivingLicenseNumber;
    private String phoneNumber2;
    private PaymentInformation paymentInformation;
    private Car rentedCar;
    private ArrayList<Lease> leases = new ArrayList<>();
    private Employee supervisingEmployee ;
    private boolean returned = false;



    public Customer(int id)  {
        super(id);
    }

    public Customer(int id,String name,String surname)  {
        super(id,name,surname);
    }

    public Customer(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1) {
        super(id, name, surname, address, email, phoneNumber1);
    }

    public Customer(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1,String phoneNumber2,PaymentInformation paymentInformation) {
        super(id, name, surname, address, email, phoneNumber1);
        this.phoneNumber2 = phoneNumber2;
        this.paymentInformation = paymentInformation;
    }


    public Customer(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1,String drivingLicenseNumber,String phoneNumber2,PaymentInformation paymentInformation)  {
        super(id, name, surname, address, email, phoneNumber1);
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.phoneNumber2 = phoneNumber2;
        this.paymentInformation = paymentInformation;
    }

    public Customer(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1,String drivingLicenseNumber,String phoneNumber2,PaymentInformation paymentInformation,Car rentedCar,ArrayList<Lease> leases,Employee supervisingEmployee)  {
        super(id, name, surname, address, email, phoneNumber1);
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.phoneNumber2 = phoneNumber2;
        this.paymentInformation = paymentInformation;
        this.rentedCar = rentedCar;
        this.leases = leases;
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



    public void setLeases(ArrayList<Lease> leases) {
        this.leases = leases;
    }

    public ArrayList<Lease> getLeases() {
        return leases;
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
        return  "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\n";
    }

    public String toString2() {
        return  "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\nPhone Number: " + phoneNumber1+"\n";
    }

    public void returnCar() {
        returned = true;
    }
}
