package soul.dev.customerservice.exceptions;

public class EmailExistedException extends Exception {
    public EmailExistedException(String message) {
        super(message);
    }
}
