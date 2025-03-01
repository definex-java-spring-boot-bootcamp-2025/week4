package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    public static String getStackTrace(Exception ex) throws RuntimeException {
        try (
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw)
        ) {
            ex.printStackTrace(pw);
            return sw.toString();
        } catch (IOException ioex) {
            String message = ioex.getMessage();
            throw new RuntimeException(String.format("could not produce stack trace %s", message));
        }
    }
}
