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

	public void displayTasks(){

		//Drawing of grid
		float gridX1 = width*.25f;
		float gridX2 = width*.95f;
		float gridY1 = height*.075f;
		float gridY2 = height*.925f;

		// Line loop
		for( int i = 1; i <= 30; i++){


			// Line code _______________________________
			if(i%2 == 0){
				stroke(100);
			}
			else{
				stroke(200);
			}

			float posX = map(i,1,30, gridX1, gridX2);

			line(posX, gridY1, posX, gridY2);


			// grid numbers ___________________________
			stroke(255);
			textAlign(CENTER);
			text(""+i, posX, gridY1 - 10);

		} // end of loop

		
		// Gant Chart Population Loop
		for(Task t:tasks){
            		
		} // end of loop
		   
	} // end of method
	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		colorMode(HSB);
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
