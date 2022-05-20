package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class DatabaseConnection {

    private static ObservableList<Customer> customerList;
    private static ObservableList<Employee> employeeList;
    private static ObservableList<PersonAddress> addressList;
    private static ObservableList<PaymentInformation> paymentInfoList;

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "ödev";
        String databaseUser = "root";
        String databasePassword = "Asko1234";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }

    public void getAllPaymentInformations() {
        paymentInfoList = FXCollections.observableArrayList();

        Connection connectDb = this.getConnection();


        String tableSQL3 = "SELECT * FROM payment_information";

        try {
            Statement statement2 = connectDb.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(tableSQL3);
            while(queryResult2.next()) {
                PaymentInformation paymentInformation = new PaymentInformation(
                        queryResult2.getInt("payment_information_id"),
                        queryResult2.getString("card_type"),
                        queryResult2.getString("card_number"),
                        queryResult2.getString("expiry_date"),
                        queryResult2.getInt("car_code")
                        //card_code olması lazım databank da create table yaparken column adını car_code
                        //diye yanlış girdin şimdilik bu isimle devam et sonra düzeltirsin.
                );

                paymentInfoList.add(paymentInformation);

            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void getAllAddresses() {

        addressList = FXCollections.observableArrayList();

        Connection connectDb = this.getConnection();
        String tableSQL2 = "SELECT * FROM person_address";

        try {
            Statement statement2 = connectDb.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(tableSQL2);
            while(queryResult2.next()) {
                PersonAddress address = new PersonAddress(
                        queryResult2.getInt("address_id"),
                        queryResult2.getString("country"),
                        queryResult2.getString("district"),
                        queryResult2.getString("street"),
                        queryResult2.getString("zip_code"),
                        queryResult2.getString("home_number")


                );
                addressList.add(address);

            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public ObservableList createAllCustomers() {

        customerList = FXCollections.observableArrayList();
        customerList.clear();
        Connection connectDb = this.getConnection();
        String tableSQL = "SELECT * FROM customer ";

        getAllAddresses();
        getAllPaymentInformations();

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(tableSQL);


            while(queryResult.next()) {
                Customer customer = new Customer(
                        queryResult.getInt("customer_id"),
                        queryResult.getString("first_name"),
                        queryResult.getString("last_name")

                );
                customerList.add(customer);
                customer.setDrivingLicenseNumber(queryResult.getString("driving_license_number"));
                for(int i = 0; i<addressList.size(); i++)
                {
                    PersonAddress personAddress= (PersonAddress) addressList.get(i);
                    if(personAddress.getId() == queryResult.getInt("address_id")) {
                        customer.setAddress(personAddress);
                    }
                }

                customer.setEmail(queryResult.getString("email"));
                customer.setPhoneNumber1(queryResult.getString("phone_number_1"));
                customer.setPhoneNumber2(queryResult.getString("phone_number_2"));

                for(int i = 0; i<paymentInfoList.size(); i++)
                {
                    PaymentInformation paymentInformation= (PaymentInformation) paymentInfoList.get(i);
                    if(paymentInformation.getId() == queryResult.getInt("payment_information_id")) {
                        customer.setPaymentInformation(paymentInformation);
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        /*
        for(int i = 0;i<customerList.size();i++) {
            System.out.println(customerList.get(i));
        }

         */
        return customerList;
    }


    public boolean validateLogin(String username,String password) {


        Connection connectDb = this.getConnection();

        String verifyLogin = "SELECT count(1) FROM employee WHERE username =  '" + username + "' AND pass = '" + password + "'" ;

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1)==1) {
                    return true;
                }else {
                    return false;
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;

    }

    public void deleteCustomer(Customer customer) {
        //silince customer'ın address ve payment information
        //ile de bağlantısı kesiliyo
        //tekrardan eklerken onlarla bağlantısını
        //da tekrardan ayarlamak gerekiyo yoksa hata veriyo

        Connection connectDb = this.getConnection();

        String tableSQL = "DELETE FROM customer WHERE customer_id = " + customer.getId();

        try {
            Statement statement = connectDb.createStatement();
            statement.execute(tableSQL);

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    //sonrası için not : (çalışan sayfaısnı oluştururken)
    // sadece manager employeelerle işlem
    //yapabilsin silsin eklesin editlesin

    public void addCustomer(String first_name, String last_name,String driving_license_number, String country, String district, String street, String zipCode, String homeNumber,String email, String phoneNumber1, String phoneNumber2, String cardType, String cardNumber, String expiryDate, int cardCode) {

        //eğer yoksa yeni address ve paymentInfo objesi oluştur
        //ilk kontrol et equals methodu override yap
        //tek tek string kontorlü yap
        //aynı değerlerden oluşan başka adres yoksa
        //yeni id ile yeni adres oluştur
        //aynısı paymentinfo için de geçerli


        //aynı bilgilerle bir müşteri varsa eklemesin yeni
        //ilk customerları arasın sonra karşılaştrısın her customer 'ın
        //bilgilerini burda parametredeki verilerle
        //equals methodu override yap öyle bak
        //hata versin aynı müşteri varsa alert dialog

        //yukarıdakileri yapmadan önce ilk normal ekleme yap
        //yeni müşteri oluşur
        //yeni adres ve yeni kart bilgisi oluştur
        //bakma aynısı var mı diye sonranın işi o
        //INSERT INTO customer VALUES(NULL,'Logan','Roy',NULL,'loganroy@gmail.com','05421234567','05421234568','MH12 20211234567',NULL);

        Connection connectDb = this.getConnection();

        //Şimdi hata vermesin diye 0 ile init edicem

        int n = addressList.size();
        int m = paymentInfoList.size();

        //customer
        //driving license number eksik dialoglarda
        //ve tableview in yanındaki bilgi tablosunda onu güncelle
        //customerda driver license ile alan construktur var mı ona bak
        //yoksa ekle

        //yeni customer oluştur
        String SQL = "INSERT INTO  customer VALUES(NULL,'" + first_name + "','"+ last_name + "',NULL,'"+ email + "','"+ phoneNumber1 + "','"+ phoneNumber2 + "','"+ driving_license_number + "',NULL);" ;

        //yeni address oluştur
        String SQL1 = "INSERT INTO  person_address VALUES("+ (n+1) +",'" + country + "','"+ district + "','"+ street + "','"+ zipCode + "','"+ homeNumber +"');" ;

        //address_id sini bul yeni oluşturduğun adresin
        //String SQL2 = "SELECT address_id FROM person_address WHERE street = '" + street + "'AND home_number = '" +homeNumber + "';";

        //adres ile customerı bağla
        //String SQL3 = "UPDATE customer SET address_id = "+ address_id + "WHERE driving_license_number = ' " + driving_license_number+ "';";

        //String SQL4 = "SELECT customer_id FROM customer WHERE driving_license_number = '" + driving_license_number + "';";

        //yeni payment info
        //String SQL5 = "INSERT INTO  payment_information VALUES(NULL,"+customer_id+","+cardType+","+cardNumber+","+expiryDate+","+cardCode +");" ;

        //gerek kalmaz m + 1 kullanırsan payment_info olarak
        //String SQL6 = "SELECT payment_information_id FROM payment_information WHERE cardType = '" + cardType + "',AND cardNumber = '" +cardNumber + "';";

        //String SQL7 = " UPDATE customer SET payment_information_id = "+ + " WHERE first_name = '" +first_name +'AND last_name = '+last_name+ "'";

        try {
            Statement statement = connectDb.createStatement();
            statement.execute(SQL);

            statement.execute(SQL1);

            int address_id = n + 1;
            String SQL3 = "UPDATE customer SET address_id = "+ address_id + " WHERE driving_license_number = '" + driving_license_number+ "';";
            statement.execute(SQL3);

            String SQL5 = "INSERT INTO  payment_information VALUES(NULL,'"+cardType+"','"+cardNumber+"','"+expiryDate+"',"+cardCode +");" ;
            statement.execute(SQL5);

            int payment_information_id = m + 1;
            String SQL7 = " UPDATE customer SET payment_information_id = "+ payment_information_id + " WHERE first_name = '" +first_name +"'AND last_name = '" +last_name+ "';";
            statement.execute(SQL7);


        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void editCustomer(Customer customer) {

        //String first_name, String last_name, String country, String district, String street, String zipCode, String email, String phoneNumber1, String phoneNumber2, String cardType, String cardNumber, Date expiryDate, int cardCode

        /*

        //UPDATE customer SET address_id = 4 WHERE last_name = 'Roy';
        Connection connectDb = this.getConnection();
        //Şimdi hata vermesin diye 0 ile init edicem
        int address_id = 0;
        int payment_information_id = 0;



        String SQL = "UPDATE customer " + "SET first_name = '" +first_name+ "',last_name = ' "+last_name+"'," + "',address_id = " + address_id + ",email = '" + email +"',phone_number_1 = '"+phoneNumber1  +"',phone_number_2 = '"+ phoneNumber2 + "',payment_information_id = " + payment_information_id + "WHERE customer_id =  " + customer.getAddress() ;


         */
        Connection connectDb = this.getConnection();
        String SQL = "UPDATE customer SET first_name = '" +customer.getName()+ "',last_name = '"+customer.getSurname()+ "',driving_license_number = '"+customer.getDrivingLicenseNumber()+"',email = '" + customer.getEmail() +"',phone_number_1 = '"+ customer.getPhoneNumber1()+"',phone_number_2 = '"+ customer.getPhoneNumber2() + "' WHERE customer_id =  " + customer.getId();
        //address ve payment info için ayrı sql yaz
        String SQL1 = "UPDATE person_address SET country = '" +customer.getAddress().getCountry()+ "',district = '"+customer.getAddress().getDistrict()+"',street = '" + customer.getAddress().getStreet() +"',zip_code = '"+ customer.getAddress().getZipCode()+"',home_number = '"+ customer.getAddress().getHomeNumber() + "' WHERE address_id =  " + customer.getAddress().getId();
        String SQL2 = "UPDATE payment_information SET card_type = '" +customer.getPaymentInformation().getCardType()+ "',card_number = '"+customer.getPaymentInformation().getCardNumber()+"',expiry_date = '" + customer.getPaymentInformation().getExpiryDate() +"',car_code = "+ customer.getPaymentInformation().getCardCode()+ " WHERE payment_information_id =  " + customer.getPaymentInformation().getId();
        try {
            Statement statement = connectDb.createStatement();
            statement.execute(SQL);
            statement.execute(SQL1);

            statement.execute(SQL2);

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public ObservableList createAllEmployees() {

        employeeList = FXCollections.observableArrayList();
        employeeList.clear();
        Connection connectDb = this.getConnection();
        String tableSQL = "SELECT * FROM employee ";

        getAllAddresses();

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(tableSQL);


            while(queryResult.next()) {
                Employee employee = new Employee(
                        queryResult.getInt("employee_id"),
                        queryResult.getString("first_name"),
                        queryResult.getString("last_name")

                );
                employeeList.add(employee);
                for(int i = 0; i<addressList.size(); i++)
                {
                    PersonAddress personAddress= (PersonAddress) addressList.get(i);
                    if(personAddress.getId() == queryResult.getInt("address_id")) {
                        employee.setAddress(personAddress);
                    }
                }

                employee.setEmail(queryResult.getString("email"));
                employee.setPhoneNumber1(queryResult.getString("phone_number_1"));

            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        for(int i = 0;i<employeeList.size();i++) {
            System.out.println(employeeList.get(i));
        }


        return employeeList;
    }

    public void deleteEmployee(Employee employee) {
        //silince customer'ın address ve payment information
        //ile de bağlantısı kesiliyo
        //tekrardan eklerken onlarla bağlantısını
        //da tekrardan ayarlamak gerekiyo yoksa hata veriyo

        Connection connectDb = this.getConnection();

        String tableSQL = "DELETE FROM employee WHERE employee_id = " + employee.getId();

        try {
            Statement statement = connectDb.createStatement();
            statement.execute(tableSQL);

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void editEmployee(Employee employee) {

        //String first_name, String last_name, String country, String district, String street, String zipCode, String email, String phoneNumber1, String phoneNumber2, String cardType, String cardNumber, Date expiryDate, int cardCode

        /*

        //UPDATE customer SET address_id = 4 WHERE last_name = 'Roy';
        Connection connectDb = this.getConnection();
        //Şimdi hata vermesin diye 0 ile init edicem
        int address_id = 0;
        int payment_information_id = 0;



        String SQL = "UPDATE customer " + "SET first_name = '" +first_name+ "',last_name = ' "+last_name+"'," + "',address_id = " + address_id + ",email = '" + email +"',phone_number_1 = '"+phoneNumber1  +"',phone_number_2 = '"+ phoneNumber2 + "',payment_information_id = " + payment_information_id + "WHERE customer_id =  " + customer.getAddress() ;


         */
        Connection connectDb = this.getConnection();
        String SQL = "UPDATE employee SET first_name = '" +employee.getName()+ "',last_name = '"+employee.getSurname()+"',email = '" + employee.getEmail() +"',phone_number_1 = '"+ employee.getPhoneNumber1()+ "' WHERE employee_id =  " + employee.getId();
        //address ve payment info için ayrı sql yaz
        String SQL1 = "UPDATE person_address SET country = '" +employee.getAddress().getCountry()+ "',district = '"+employee.getAddress().getDistrict()+"',street = '" + employee.getAddress().getStreet() +"',zip_code = '"+ employee.getAddress().getZipCode()+"',home_number = '"+ employee.getAddress().getHomeNumber() + "' WHERE address_id =  " + employee.getAddress().getId();

        try {
            Statement statement = connectDb.createStatement();
            statement.execute(SQL);
            statement.execute(SQL1);


        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addEmployee(String first_name, String last_name, String country, String district, String street, String zipCode, String homeNumber,String email, String phoneNumber1, String username, String password) {


        Connection connectDb = this.getConnection();
        int n = addressList.size();

        //INSERT INTO employee VALUES(NULL,'Deniz','Özdemirli',NULL,'denizozdemirli@outlook.com','05385427349','manager','02deno','password123');

        //yeni employee oluştur
        String SQL = "INSERT INTO  employee VALUES(NULL,'" + first_name + "','"+ last_name + "',NULL,'"+ email + "','"+ phoneNumber1 + "','Employee','"+ username + "','"+ password + "');" ;

        //yeni address oluştur
        String SQL1 = "INSERT INTO  person_address VALUES("+ (n+1) +",'" + country + "','"+ district + "','"+ street + "','"+ zipCode + "','"+ homeNumber +"');" ;


        try {
            Statement statement = connectDb.createStatement();
            statement.execute(SQL);

            statement.execute(SQL1);

            int address_id = n + 1;
            String SQL3 = "UPDATE employee SET address_id = "+ address_id + " WHERE first_name = '" + first_name+"' AND last_name = '" + last_name+ "';";
            statement.execute(SQL3);




        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


}
