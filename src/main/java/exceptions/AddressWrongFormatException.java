/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author juanni420
 */
public class AddressWrongFormatException extends Exception {

    /**
     * Creates a new instance of <code>AddressWrongFormatException</code>
     * without detail message.
     */
    public AddressWrongFormatException() {
    }

    /**
     * Constructs an instance of <code>AddressWrongFormatException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AddressWrongFormatException(String msg) {
        super(msg);
    }
}
