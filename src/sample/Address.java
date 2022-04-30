package sample;

public abstract class Address {

    protected int id;
    protected String country;
    protected String district;
    protected String street;
    protected String zipCode;

    public Address(int id,String country,String district,String street,String zipCode) {
        this.id = id;
        this.country = country;
        this.district = district;
        this.street = street;
        this.zipCode = zipCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
