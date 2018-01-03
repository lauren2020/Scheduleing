package scheduler;

import scheduler.exceptions.InvalidDeadlineException;
import scheduler.exceptions.InvalidValueException;

/**
 * A class to represent a single task to be scheduled. Contains the task's 
 * deadline and value. The Task class also holds a static value that holds 
 * the largest deadline of all Tasks created.
 * @author Main
 *
 */
public class Task 
{
	/**
	 * Holds the maximum deadline of all tasks.
	 */
	private static int maximum = 0;
	/**
	 * Holds deadline of task.
	 */
	private int deadline;
	/**
	 * Holds value of task.
	 */
	private int value;
	
	/**
	 * Task Constructor; sets the deadline and value of the Task.
	 * @param deadlineIn - Deadline of the Task.
	 * @param valueIn - Value of the Task.
	 * @throws InvalidDeadlineException - invalid deadline
	 * @throws InvalidValueException - invalid value
	 */
	public Task(int deadlineIn, int valueIn) throws InvalidDeadlineException, InvalidValueException
	{
		if (deadlineIn >= 1)
		{
			deadline = deadlineIn;
			if (deadline > maximum)
			{
				maximum = deadline;
			}
		}
		else
		{
			throw new InvalidDeadlineException(deadlineIn);
		}
		if (valueIn >= 0)
		{
			value = valueIn;
		}
		else
		{
			throw new InvalidValueException(valueIn);
		}
	}
	/**
	 * Sets the deadline of the Task. Deadlines have a minimum value of 1. 
	 * This also ensures that the overall maximum deadline for all Tasks 
	 * is updated if the new deadline is greater than the previous maximum.
	 * @param deadlineIn - Desired deadline of this Task.
	 * @throws InvalidDeadlineException - If an invalid deadline is given as a parameter value.
	 */
	public void setDeadline(int deadlineIn) throws InvalidDeadlineException
	{
		if (deadline >= 1)
		{
			deadline = deadlineIn;
			if (deadline > maximum)
			{
				maximum = deadline;
			}
		}
		else
		{
			throw new InvalidDeadlineException(deadlineIn);
		}
		/*if (deadline > maximum)
		{
			maximum = deadline;
		}*/
	}
	/**
	 * Sets the value of the Task.
	 * @param valueIn - Desired value of this Task.
	 * @throws InvalidValueException - If the parameter valueIn is less than 1
	 */
	public void setValue(int valueIn) throws InvalidValueException
	{
		if (valueIn < 1)
		{
			throw new InvalidValueException(valueIn);
		}
		value = valueIn;
	}
	/**
	 * Retrieves the deadline for this Task.
	 * @return The Task's deadline.
	 */
	public int getDeadline()
	{
		return deadline;
	}
	/**
	 * Retrieves the value for this Task.
	 * @return The Task's value.
	 */
	public int getValue()
	{
		return value;
	}
	/**
	 * Retrieves the maximum deadline across all Task objects that have 
	 * been created thus far.
	 * @return The maximum deadline of all Tasks. 0 if no Tasks have yet 
	 * been created.
	 */
	public static int getMaxDeadline()
	{
		return maximum;
	}
	/**
	 * Compares this Task to a passed Task, determining logical order. 
	 * Order is determined by the Tasks' value. Lower valued Tasks are considered 
	 * less than the other, higher valued Tasks are considered greater than the other, 
	 * Tasks with the same value are equal.
	 * @param t - Incoming Task to be compared with the calling Task.
	 * @return Returns an int less than 0 if the local Task is less than the passed Task, 
	 * an int greater than 0 if the local Task is greater than the passed Task, and a 0 
	 * if the Tasks are equal.
	 */
	public int compareTo(Task t)
	{
		int taskOrder;
		int newTask = t.getValue();
		int thisTask = this.getValue();
		if (newTask == thisTask)
		{
			taskOrder = 0;
		}
		if (newTask > thisTask)
		{
			taskOrder = -1;
		}
		else
		{
			taskOrder = 1;
		}
		return taskOrder;
	}
	/**
	 * Generates and returns a String representation of the Task. 
	 * The String is in the form: "Deadline: DEADLINE Value: VALUE", 
	 * where DEADLINE and VALUE are replaced by the Task's deadline and value.
	 * @overrides toString in class java.lang.Object
	 * @return A String of this Task.
	 */
	public java.lang.String toString()
	{
		return String.format("Deadline: %d Value: %d", deadline, value);
	}
}
