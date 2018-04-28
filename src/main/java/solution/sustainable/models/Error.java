package solution.sustainable.models;

import lombok.Getter;

/**
 * Created by aditya.dalal on 26/04/18.
 */

@Getter
public class Error {
    private int status;
    private String message;

    public Error(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
