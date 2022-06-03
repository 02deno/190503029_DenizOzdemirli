package sample;
import java.util.*;

public class Employee extends Person {

    private String roll = "Employee";
    private String username;
    private String password;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Lease> leases = new ArrayList<>();

    public Employee(int id)  {
        super(id);
    }

    public Employee(int id,String name,String surname) {
        super(id,name,surname);
    }

    public Employee(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1)  {
        super(id, name, surname, address, email, phoneNumber1);
    }

    public Employee(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1,String username,String password,ArrayList<Customer> customers)  {
        super(id, name, surname, address, email, phoneNumber1);
        this.username = username;
        this.password = password;
        this.customers = customers;
    }

    public String getRoll() {
        return roll;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setLeases(ArrayList<Lease> leases) {
        this.leases = leases;
    }

    public ArrayList<Lease> getLeases() {
        return leases;
    }

    @Override
    public String toString() {
        return   "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\n";
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
