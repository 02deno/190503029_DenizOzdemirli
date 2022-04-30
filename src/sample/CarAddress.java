package sample;

public class CarAddress extends Address {

    //private String nameParkingLot;
    private int numberParkingSpot;

    public CarAddress(int id,String country,String district,String street,String zipCode,int numberParkingSpot) {
       super(id,country,district,street,zipCode);
       this.numberParkingSpot = numberParkingSpot;
    }

    public void setNumberParkingSpot(int numberParkingSpot) {
        this.numberParkingSpot = numberParkingSpot;
    }

    public int getNumberParkingSpot() {
        return numberParkingSpot;
    }

    @Override
    public String toString() {
        return "CarAddress :\n" +
                "id='" + id + '\'' +
                "country='" + country + '\'' +
                "district='" + district + '\'' +
                "street='" + street + '\'' +
                "zipCode='" + zipCode + '\'' +
                "numberParkingSpot='" + numberParkingSpot;
    }

}
