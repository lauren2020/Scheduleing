package scheduler.exceptions;

import java.io.Serializable;

/**
 * This Exception is used to signal that a valid schedule does not yet exist and 
 * thus requested values cannot be computed.
 * @author Main
 *
 */
public class InvalidScheduleException extends java.lang.Exception implements Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7526472295622776148L;
	
	/**
	 * Constructs a new InvalidScheduleException containing the message: "Schedule not available.".
	 */
	public InvalidScheduleException()
	{
		super("Schedule not available.");
	}
}
