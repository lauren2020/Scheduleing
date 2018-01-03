package scheduler.exceptions;

import java.io.Serializable;

/**
 * Exception thrown when it is attempted to set a Task's value to an invalid value.
 * @author Main
 *
 */
public class InvalidValueException extends java.lang.Exception implements Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7526472295622776149L;
	
	/**
	 * Constructor, sets this Exceptions message to "Non-positive number given for 
	 * value: " followed by the offending value.
	 * @param value - The invalid value.
	 */
	public InvalidValueException(int value)
	{
		super("Non-positive number given for value: " + value);
	}
}
