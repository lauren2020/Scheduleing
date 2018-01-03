// Name : Lauren Shultz
// Class : 850
// Program # : 4
// Due Date : November 6, 2017
//
// Honor Pledge: On my honor as a student of the University
// of Nebraska at Omaha, I have neither given nor received
// unauthorized help on this homework assignment.
//
// NAME: Lauren Shultz
// EMAIL: lshultz@unomaha.edu
// NUID: 043
// Colleagues: 
// This program takes tasks  that the user inputs, prints the tasks with min and max values and
// the list of tasks and schedules them producing a sum of the total value of all the scheduled tasks.
package scheduler;

import java.util.ArrayList;

import scheduler.exceptions.InvalidScheduleException;

/**
 * Implements a greedy scheduling algorithm that maximizes value or "profit". 
 * Intended use is to accept an undefined amount of Tasks, then call upon 
 * scheduleTasks to perform the algorithm. The total profit of the scheduled 
 * Tasks can then be found by calling upon calculateValue.
 * @author Main
 *
 */
public class Scheduler extends java.lang.Object
{
	/**
	 * Holds a value ordered list of tasks.
	 */
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	/**
	 * Holds a list of tasks in the order they were entered.
	 */
	private ArrayList<Task> tasksOrig = new ArrayList<Task>();
	
	/**
	 * Holds a all tasks that have been scheduled.
	 */
	private ArrayList<Task> scheduledTasks = new ArrayList<Task>();
	
	/**
	 * Holds the task with the max value.
	 */
	private String maxTask;
	
	/**
	 * Holds the task with the min value.
	 */
	private String minTask;
	
	/**
	 * Constructs a new Scheduler instance that is ready for Tasks to be added, 
	 * but has no pre-existing valid schedule of Tasks.
	 */
	public  Scheduler()
	{
		
	}
	/**
	 * Adds a Task to be considered by this Scheduler. Adding a Task will also invalidate 
	 * any previously computed schedule by this Scheduler.
	 * @param t - The Task to be considered for possible scheduling.
	 */
	public void addTask(Task t)
	{
		tasks.add(t);
		tasksOrig.add(t);
	}
	/**
	 * Implements a greedy scheduling algorithm for the Tasks which have been added thus 
	 * far to this Scheduler. Tasks are prioritized based on decreasing value and are 
	 * scheduled in timeslots as close to their deadline as possible (but not exceeding it), 
	 * without overwriting an already scheduled Task. The method assumes that each Task 
	 * takes only 1 timeslot to complete.
	 * Note, the total number of timeslots should correspond to the maximum deadline for all 
	 * tasks. Some timeslots may be unused in the schedule, and not all Tasks are guaranteed 
	 * to be scheduled depending on conflicting deadlines and available resources.

	 * Upon completing this method, a schedule is considered valid if one or more tasks were 
	 * successfully scheduled. The scheduling of Tasks should not affect the order of the 
	 * original Task list.
	 */
	public void scheduleTasks()
	{
		int n = tasks.size();
		Task sortTemp;
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < (n - i); j++)
			{
				if (tasks.get(j - 1).getValue() < tasks.get(j).getValue())
				{
					sortTemp = tasks.get(j - 1);
					tasks.set(j - 1, tasks.get(j));
					tasks.set(j, sortTemp);
				}
			}
		}
		Task[] deadlineSchedule = new Task[Task.getMaxDeadline()];
		for (int i = 0; i < Task.getMaxDeadline(); i++)
		{
			scheduledTasks.add(null);
		}
		for (int i = 0; i < n; i++)
		{
			int deadline = tasks.get(i).getDeadline();
			for (int j = 1; j <= deadline; j++)
			{
				if (deadlineSchedule[deadline - j] == null)
				{
					deadlineSchedule[deadline - j] = tasks.get(i);
					scheduledTasks.set(deadline - j, tasks.get(i));
					break;
				}
			}
		}
		
	}
	/**
	 * Retrieves an array of Tasks corresponding to the most recently computed schedule by this Scheduler. 
	 * Each array position corresponds to a single timeslot in the schedule, and the total number of 
	 * timeslots should correspond to the maximum deadline of all Tasks created to present.
	 * @return The array of Tasks corresponding to the computed schedule.
	 * @throws InvalidScheduleException - if no valid schedule has been computed yet
	 */
	public ArrayList<Task> getSchedule() throws InvalidScheduleException
	{
		if (tasks != null)
		{
			throw new InvalidScheduleException();
		}
		else
		{
			return tasks;
		}
	}
	/**
	 * Sums the values of all actively scheduled Tasks, resulting in total profit for the determined schedule. 
	 * Idle timeslots are ignored.
	 * @throws InvalidScheduleException - if no valid schedule has been computed yet.
	 * @return Total profit of the scheduled Tasks.
	 */
	public int calculateValue() throws InvalidScheduleException
	{
		int sumValue = 0;
		for (int i = 0; i < scheduledTasks.size(); i++)
		{
			if (scheduledTasks.get(i) != null)
			{
				sumValue += scheduledTasks.get(i).getValue();
			}
		}
		return sumValue;
	}
	/**
	 * Sets values maxTask and minTask equal to task that have the max value and min value respectively.
	 * @param data - task list
	 */
	public void setMaxMinTasks(ArrayList<Task> data)
	{
		int n = data.size();
		Task sortTemp;
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < (n - i); j++)
			{
				if (data.get(j - 1).getValue() < data.get(j).getValue())
				{
					sortTemp = data.get(j - 1);
					data.set(j - 1, data.get(j));
					data.set(j, sortTemp);
				}
			}
		}
		minTask = data.get(data.size() - 1).toString();
		maxTask = data.get(0).toString();
	}
	/**
	 * Constructs and returns a formatted String reporting the contents of the list of tasks that have been 
	 * added for consideration by this Scheduler.
	 * String is of the form: "Task with Max value: MAX_TASK\nTask with Min Value: MIN_TASK\n" followed by all 
	 * Tasks on a line of their own in the order in which they were added to this Scheduler object. MAX_TASK 
	 * and MIN_TASK are the Tasks with the maximum and minimum value, respectively.
	 * @throws java.util.NoSuchElementException - when no Tasks have been added yet
	 * @return String of all inputed Tasks.
	 */
	public java.lang.String allTasksString() throws java.util.NoSuchElementException
	{
		if (tasks.size() <= 0)
		{
			throw new java.util.NoSuchElementException();
		}
		setMaxMinTasks(tasks);
		String allTasks = String.format("Task with Max value: %s\nTask with Min Value: %s\n", maxTask, minTask);
		for (int i = 0; i < tasksOrig.size(); i++)
		{
			allTasks = String.format("%s%s\n", allTasks, tasksOrig.get(i).toString());
		}
		return allTasks;
	}
	/**
	 * Constructs and returns a formatted String reporting the contents of the computed schedule array. 
	 * String is of the form: "Scheduled Tasks:\n", followed by all Tasks in the computed schedule on a 
	 * line of their own. If a slot in the schedule array contains no Task, the row should report "IDLE" 
	 * instead of a task.
	 * @return String of all scheduled Tasks.
	 * @throws InvalidScheduleException - if no valid schedule has been computed yet
	 */
	public java.lang.String scheduleResultString() throws InvalidScheduleException
	{
		if (scheduledTasks == null)
		{
			throw new InvalidScheduleException();
		}
		else
		{
			String schedule = "Scheduled Tasks:\n";
			for (int i = 0; i < scheduledTasks.size(); i++)
			{
				if (scheduledTasks.get(i) != null)
				{
					schedule = String.format("%s%s\n", schedule, scheduledTasks.get(i).toString());
				}
				else
				{
					schedule = String.format("%s%s\n", schedule, "IDLE");
				}
			}
			return schedule;
		}
	}
}