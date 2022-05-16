package sample;

public abstract class Person {

    protected int id;
    protected String name ;
    protected String surname;
    protected PersonAddress address;
    protected String email;
    protected String phoneNumber1;


    public Person(int id)  {
           this.id = id;
    }



    public Person(int id,String name,String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Person(int id,String name,String surname,PersonAddress address,String email,String phoneNumber1) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phoneNumber1 = phoneNumber1;
    }

    /*
    public static boolean ID_control(String id) {
        return id.matches("\\d+") && id.length() == 7;
    }

     */

    @Override
    public String toString() {
        return "Person\n" + "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person) {
            Person p = (Person) obj;
            return id == p.id;
        }

        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setAddress(PersonAddress address) {
        this.address = address;
    }

    public PersonAddress getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }
}
