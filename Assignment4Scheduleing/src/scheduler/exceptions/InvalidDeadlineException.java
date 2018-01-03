package scheduler.exceptions;

import java.io.Serializable;

/**
 * Exception thrown when it is attempted to set a Task's deadline to an invalid value.
 * @author Main
 *
 */
public class InvalidDeadlineException extends java.lang.Exception implements Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7526472295622776147L;
	
	/**
	 * Constructor, sets this Exceptions message to "Non-positive number given for d
	 * eadline: " followed by the offending deadline.
	 * @param deadline - The invalid deadline.
	 */
	public InvalidDeadlineException(int deadline)
	{
		super("Non-positive number given for deadline: " + deadline);
	}
}
