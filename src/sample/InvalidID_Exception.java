package sample;

public class InvalidID_Exception extends Exception {

    public InvalidID_Exception() {
        super("Invalid id.It must consist of 7 digits only.");
    }
}
