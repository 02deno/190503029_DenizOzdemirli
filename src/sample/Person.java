package sample;

public abstract class Person {

    protected String id;
    protected String name ;
    protected String surname;
    protected Address address;
    protected String email;
    protected String phoneNumber1;

    public Person(String id) throws InvalidID_Exception {
        if(ID_control(id)) {
            this.id = id;
        }else {
            throw new InvalidID_Exception();
        }
    }


    public Person(String id,String name,String surname) throws InvalidID_Exception {
        this(id);
        this.name = name;
        this.surname = surname;
    }

    public Person(String id,String name,String surname,Address address,String email,String phoneNumber1) throws InvalidID_Exception {
        this(id);
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phoneNumber1 = phoneNumber1;
    }

    public static boolean ID_control(String id) {
        return id.matches("\\d+") && id.length() == 7;
    }

    @Override
    public String toString() {
        return "Person\n" + "Id: " + id + "\nName: " + name + "\nSurname: " + surname + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person) {
            Person p = (Person) obj;
            return id.equals(p.id);
        }

        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
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
