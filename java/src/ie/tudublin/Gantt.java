package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}


	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");

        for(TableRow tr:t.rows())
        {
            Task s = new Task(tr);
            tasks.add(s);
        }

	}


	public void printTasks()
	{
		for(Task t:tasks)
        {
            println(t);
        }

	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
	}
	
	public void draw()
	{			
		background(0);
	}
}
