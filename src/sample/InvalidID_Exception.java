package sample;

public class InvalidIDException extends Exception {

    public  InvalidIDException() {
        super("Invalid id.It must consist of 7 digits only.");
    }
}
