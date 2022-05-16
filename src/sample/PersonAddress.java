package sample;

public class PersonAddress extends Address{

    private String homeNumber;

    public PersonAddress(int id,String country,String district,String street,String zipCode,String homeNumber) {
        super(id,country,district,street,zipCode);
        this.homeNumber = homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    @Override
    public String toString() {
        return  country +", "+ district +"\n"+ street + " " + homeNumber + ", "+zipCode   ;
    }
}
