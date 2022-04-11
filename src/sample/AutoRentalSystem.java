package sample;

import java.util.ArrayList;

public class AutoRentalSystem {
    //add
    //display
    //delete
    //modify

    protected ArrayList<Customer> customers;
    protected ArrayList<Car> cars;
    protected ArrayList<Lease> leases;
    protected ArrayList<Bill> bills;
    protected ArrayList<Employee> employees;

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setLeases(ArrayList<Lease> leases) {
        this.leases = leases;
    }


    public ArrayList<Lease> getLeases() {
        return leases;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }


    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public boolean containsCustomer(Customer customer) {
        return customers.contains(customer);
    }

    public boolean addCustomer(Customer customer) {
        if(containsCustomer(customer) == false) {
            customers.add(customer);
            return true;
        }else {
            return false;
        }
    }

    public int searchCustomer(String id) {
        //returns the index
        Customer customer;
        for(int i = 0; i<customers.size(); i++)
        {
            customer= (Customer) customers.get(i);
            if(customer.getId().equals(id) == true) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeCustomer(String id) {
        int i = searchCustomer(id);
        if(i == -1) {
            return false;
        }else {
            Customer customer = (Customer) customers.get(i);
            customers.remove(customer);
            return true;
        }
    }

    public String printCustomerList() {
        String a = "";
        for(int i = 0; i<customers.size(); i++) {
            a = a + customers.get(i).toString();
        }
        return a;
    }

    public boolean containsCar(Car car) {
        return cars.contains(car);
    }

    public boolean addCar(Car car) {
        if(containsCar(car) == false) {
            cars.add(car);
            return true;
        }else {
            return false;
        }
    }

    public int searchCar(String id) {
        //returns the index
        Car car;
        for(int i = 0; i<cars.size(); i++)
        {
            car = (Car) cars.get(i);
            if(car.getId().equals(id) == true) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeCar(String id) {
        int i = searchCar(id);
        if(i == -1) {
            return false;
        }else {
            Car car = (Car) cars.get(i);
            cars.remove(car);
            return true;
        }
    }

    public String printCarList() {
        String a = "";
        for(int i = 0; i<cars.size(); i++) {
            a = a + cars.get(i).toString();
        }
        return a;
    }


    public boolean containsLease(Lease lease) {
        return leases.contains(lease);
    }

    public boolean addLease(Lease lease) {
        if(containsLease(lease) == false) {
            leases.add(lease);
            return true;
        }else {
            return false;
        }
    }

    public int searchLease(String id) {
        //returns the index
        Lease lease;
        for(int i = 0; i<leases.size(); i++)
        {
            lease = (Lease) leases.get(i);
            if(lease.getId().equals(id) == true) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeLease(String id) {
        int i = searchLease(id);
        if(i == -1) {
            return false;
        }else {
            Lease lease = (Lease) leases.get(i);
            leases.remove(lease);
            return true;
        }
    }

    public String printLeaseList() {
        String a = "";
        for(int i = 0; i<leases.size(); i++) {
            a = a + leases.get(i).toString();
        }
        return a;
    }

    public boolean containsBil(Bill bill) {
        return bills.contains(bill);
    }

    public boolean addBill(Bill bill) {
        if(containsBil(bill) == false) {
            bills.add(bill);
            return true;
        }else {
            return false;
        }
    }

    public int searchBill(String id) {
        //returns the index
        Bill bill;
        for(int i = 0; i<bills.size(); i++)
        {
            bill = (Bill) bills.get(i);
            if(bill.getId().equals(id) == true) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeBill(String id) {
        int i = searchBill(id);
        if(i == -1) {
            return false;
        }else {
            Bill bill = (Bill) bills.get(i);
            bills.remove(bill);
            return true;
        }
    }

    public String printBillList() {
        String a = "";
        for(int i = 0; i<bills.size(); i++) {
            a = a + bills.get(i).toString();
        }
        return a;
    }

    public boolean containsEmployee(Employee employee) {
        return employees.contains(employee);
    }


    public boolean addEmployee(Employee employee) {
        if(containsEmployee(employee) == false) {
            employees.add(employee);
            return true;
        }else {
            return false;
        }
    }

    public int searchEmployee(String id) {
        //returns the index
        Employee employee;
        for(int i = 0; i<employees.size(); i++)
        {
            employee = (Employee) employees.get(i);
            if(employee.getId().equals(id) == true) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeEmployee(String id) {
        int i = searchEmployee(id);
        if(i == -1) {
            return false;
        }else {
            Employee employee = (Employee) employees.get(i);
            customers.remove(employee);
            return true;
        }
    }

    public String printEmployeeList() {
        String a = "";
        for(int i = 0; i<employees.size(); i++) {
            a = a + employees.get(i).toString();
        }
        return a;
    }

    public void addCustomerToEmployee(Employee employee,String id) throws InvalidID_Exception {
        Customer customer = new Customer(id);
        if(employee.getCustomers().contains(id) == false) {
            employee.getCustomers().add(customer);
        }
    }

    public String printCustomersOfEmployee(String id){
        Employee employee = employees.get(searchEmployee(id));
        String a = "";
        for(int i = 0;i<employee.getCustomers().size();i++) {
            a = a + employee.getCustomers().get(i).toString();
        }
        return a;
    }

    public void addLeaseToEmployee(Employee employee,String id)  {
        Lease lease = new Lease(id);
        if(employee.getLeases().contains(id) == false) {
            employee.getLeases().add(lease);
        }
    }

    public String printLeasesOfEmployee(String id){
        Employee employee = employees.get(searchEmployee(id));
        String a = "";
        for(int i = 0;i<employee.getLeases().size();i++) {
            a = a + employee.getLeases().get(i).toString();
        }
        return a;
    }

    public void addCustomerToCar(Car car,String id) throws InvalidID_Exception {
        Customer customer = new Customer(id);
        if(car.getCustomers().contains(id) == false) {
            car.getCustomers().add(customer);
        }
    }

    public String printCustomersOfCar(String id){
        Car car = cars.get(searchCar(id));
        String a = "";
        for(int i = 0;i<car.getCustomers().size();i++) {
            a = a + car.getCustomers().get(i).toString();
        }
        return a;
    }

    public void addLeaseToCar(Car car,String id)  {
        Lease lease = new Lease(id);
        if(car.getLeases().contains(id) == false) {
            car.getLeases().add(lease);
        }
    }

    public String printLeasesOfCar(String id){
        Car car = cars.get(searchCar(id));
        String a = "";
        for(int i = 0;i<car.getLeases().size();i++) {
            a = a + car.getLeases().get(i).toString();
        }
        return a;
    }

    public void addLeaseToCustomer(Customer customer,String id)  {
        Lease lease = new Lease(id);
        if(customer.getLeases().contains(id) == false) {
            customer.getLeases().add(lease);
        }
    }

    public String printLeasesOfCustomer(String id){
        Customer customer = customers.get(searchCar(id));
        String a = "";
        for(int i = 0;i<customer.getLeases().size();i++) {
            a = a + customer.getLeases().get(i).toString();
        }
        return a;
    }

}
