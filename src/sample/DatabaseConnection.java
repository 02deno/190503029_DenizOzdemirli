package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class DatabaseConnection {

    private ObservableList<Customer> customerList;
    private ObservableList<PersonAddress> addressList;
    private ObservableList<PaymentInformation> paymentInfoList;

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "car";
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

    public void addCustomer(String first_name, String last_name, String country, String district, String street, String zipCode, String homeNumber,String email, String phoneNumber1, String phoneNumber2, String cardType, String cardNumber, Date expiryDate, int cardCode,String driving_license_number) {

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
        int address_id = 0;
        int payment_information_id = 0;

        //customer
        //driving license number eksik dialoglarda
        //ve tableview in yanındaki bilgi tablosunda onu güncelle
        //customerda driver license ile alan construktur var mı ona bak
        //yoksa ekle
        String SQL2 = "INSERT INTO  customer VALUES(NULL" + first_name + ","+ last_name + ",NULL,"+ email + ","+ phoneNumber1 + ","+ phoneNumber2 + ","+ driving_license_number + ",NULL);" ;

        //address
        String SQL = "INSERT INTO  person_address VALUES(NULL" + country + ","+ district + ","+ street + ","+ zipCode + ","+ homeNumber +");" ;
        //String SQL3 = "UPDATE customer SET address_id = "+ + "WHERE driving_license_number = ' " + driving_license_number+ "';";
        //payment info
        String SQL1 = "INSERT INTO  payment_information VALUES(NULL" + first_name + ","+ last_name + ","+ address_id + ","+ email + ","+ phoneNumber1 + ","+ phoneNumber2 + ","+ payment_information_id + ");" ;


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
        String SQL = "UPDATE customer SET first_name = '" +customer.getName()+ "',last_name = '"+customer.getSurname()+"',email = '" + customer.getEmail() +"',phone_number_1 = '"+ customer.getPhoneNumber1()+"',phone_number_2 = '"+ customer.getPhoneNumber2() + "' WHERE customer_id =  " + customer.getId();
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

}
