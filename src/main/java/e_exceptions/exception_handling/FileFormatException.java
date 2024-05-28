package e_exceptions.exception_handling;

import java.io.IOException;

// Custom exception class
public class FileFormatException extends IOException {
    // Constructor 1: Default constructor is must
    public FileFormatException() {}

    // Constructor 2: Constructor that contains detailed message
    public FileFormatException(String s) {
        super(s);
    }
}
