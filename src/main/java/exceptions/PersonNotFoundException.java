/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author martin
 */
public class PersonNotFoundException extends Exception
{

    public PersonNotFoundException()
    {

    }

    public PersonNotFoundException(String msg)
    {
        super(msg);
    }

    public PersonNotFoundException(Exception e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
