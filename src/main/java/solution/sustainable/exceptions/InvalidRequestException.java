package solution.sustainable.exceptions;

/**
 * Created by aditya.dalal on 26/04/18.
 */
public class InvalidRequestException extends Exception {

    private int status;
    private String message;

    public InvalidRequestException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
