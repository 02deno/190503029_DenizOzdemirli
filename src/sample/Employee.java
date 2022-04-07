package sample;
import java.util.*;

public class Employee extends Person {

    private String username;
    private String password;
    private ArrayList<Customer> customers = new ArrayList<>();

    public Employee(String id) throws InvalidID_Exception {
        super(id);
    }

    public Employee(String id,String name,String surname) throws InvalidID_Exception {
        super(id,name,surname);
    }

    public Employee(String id,String name,String surname,Address address,String email,String phoneNumber1) throws InvalidID_Exception {
        super(id, name, surname, address, email, phoneNumber1);
    }

    public Employee(String id,String name,String surname,Address address,String email,String phoneNumber1,String username,String password,ArrayList<Customer> customers) throws InvalidID_Exception {
        super(id, name, surname, address, email, phoneNumber1);
        this.username = username;
        this.password = password;
        this.customers = customers;
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

    @Override
    public String toString() {
        return "Employee\n" + "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\n";
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
