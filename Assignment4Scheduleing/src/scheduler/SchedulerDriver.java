package scheduler;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

import scheduler.exceptions.InvalidDeadlineException;
import scheduler.exceptions.InvalidScheduleException;
import scheduler.exceptions.InvalidValueException;

/**
 * Driver using a Scheduler to retrieve user Tasks and schedule them for optimal profit.
 * @author Mr. Cavanaugh
 */
public class SchedulerDriver
{
	/**
	 * Entry point of execution.
	 * @param args Not used.
	 */
	public static void main(String[] args)
	{
		//Create our Scheduler
		Scheduler s = new Scheduler();
		//Tasks will be input via a String
		String input;
		//Input String will be tokenized into deadline and value ints
		int deadline, value;
		//Create a Scanner for input
		Scanner in = new Scanner(System.in);
		//Prompt for Task input
		System.out.println("Input Tasks: DEADLINE VALUE\nType DONE to schedule");

		//Continue to accept Tasks until user enters "DONE"
		do
		{
			try
			{
				//Get the input
				input = in.nextLine();
				//If they are done, break out of loop
				if (input.equals("DONE"))
				{
					break;
				}
				
				//Create a StringTokenizer to split up the String
				StringTokenizer token = new StringTokenizer(input);
				//The first int is the Task's deadline
				deadline = Integer.parseInt(token.nextToken());
				//The second int is the Task's value
				value = Integer.parseInt(token.nextToken());
				//Create a new Task and add it to the Scheduler
				s.addTask(new Task(deadline, value));
			}
			catch (NumberFormatException nfe) //For input that could not be correctly parsed
			{
				System.out.println("Invalid Task");
			}
			catch (NoSuchElementException nsee)
			{
				System.out.println("Invalid Task");
			}
			catch (InvalidDeadlineException | InvalidValueException ex) //Valid input, but invalid Task
			{
				System.out.printf("Invalid Task: %s\n", ex.getMessage());
			}
		} while (true); //Go forever, or until "DONE" entered, whichever comes first
		in.close(); //close the scanner
		
		try
		{
			//Output all the Tasks before Scheduling
			System.out.printf("\n%s", s.allTasksString());
			//Let the Scheduler work its magic
			s.scheduleTasks();
			//Output the Scheduled Tasks
			System.out.printf("\n%s", s.scheduleResultString());
			//Report the profit of the Scheduled Tasks
			System.out.printf("\nMaximum Value of Tasks is %d\n", s.calculateValue());
		}
		catch (NoSuchElementException nsee) //If there are no Tasks, inputTasksString will throw this
		{
			System.out.println("No tasks given."); 
		}
		catch (InvalidScheduleException e) //ADDED******************************************
		{
			System.out.println("ADDED: NOT CAUGHT IN DRIVER");
		}

	}
}
