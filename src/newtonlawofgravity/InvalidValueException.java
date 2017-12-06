/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

/**
 *
 * @author 
 */
public class InvalidValueException extends IllegalArgumentException{
    
    private String msg;
    private static final String DEFAULT_EXCEPTION_MSG = "INVALID VALUE!";

    public InvalidValueException(){
        super();
        msg = new String(DEFAULT_EXCEPTION_MSG );
    }
    public InvalidValueException(String msg){
                super(msg);
                this.msg = new String(msg);
        }

    @Override
    public String getMessage(){
        return new String(msg);
    }
    @Override
    public String toString(){
        return new String(getClass().getName() + ": " + msg);
    }
}
