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
		//println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		//println("Mouse dragged");
	}


	
	//adjustment variables
	int endFlag = 0;	
	int target = 0;
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
			fill(255);
			textSize(12);
			textAlign(CENTER);
			text(""+i, posX, gridY1 - 10);

		} // end of loop

		
		// Gant Chart Population Loop
		textAlign(LEFT,CENTER);
		textSize(16);
		for( int i = 0; i < tasks.size(); i++){

			float initialGap = height*.1f;
			float taskGap = height*.005f;
			float taskWidth = height*.05f;
			float taskTextOffset = gridX1*.7f;
			Task t = tasks.get(i);

			//text render
			fill(255);
			text(t.getTaskName(),gridX1 - taskTextOffset, gridY1 + initialGap + (taskWidth + taskGap)*i);

			float posX1 = map(t.getStart(),1,30, gridX1, gridX2);
			float posX2 = map(t.getEnd(),1,30, gridX1, gridX2);
			float width = posX2-posX1;


			float rectColor = map(i,0,tasks.size(),0,255);
			fill( (int)rectColor,255,255);
			noStroke();

			float postY1 = gridY1 - (taskWidth/2) + initialGap + (taskWidth + taskGap)*i;
			rect(posX1, postY1,width,taskWidth,5);


			
			// Code to adjust gant chart tasks=====================================================
			
			boolean success = false;
			// Mouse TASK CROSSOVER detection code ________________________________________________________
			if(mouseY >= postY1 && mouseY<= postY1 + taskWidth ){

				if(mousePressed){

				}
				else if(mouseX >= posX1 && mouseX <= posX1 + 20){
					
					//System.out.println("at start" + i);

					endFlag = 1;
					target = i;
					success = true;
				
				}
				else if(mouseX <= posX2 && mouseX >= posX2 - 20){
					

					//System.out.println("at end" + i);

					endFlag = 2;
					target = i;
					success = true;

				} // if in X range eg in width of one of bars
				else if(i+1 == tasks.size() && success == false){
					endFlag = 0;
				}
			} // if in Y Range eg at the height of one of bars

		} // end of loop




		// Task Adjustment code _______________________________________________________
		int dragVar;
		Task targetTask = tasks.get(target);

		if(mousePressed){
			if(endFlag == 1){
				
				dragVar = (int) map(mouseX,gridX1,gridX2,1,30);

				if(dragVar < targetTask.getEnd() && dragVar > 0){
					targetTask.setStart(dragVar);
				}


			}

			if(endFlag == 2){
			
				dragVar = (int) map(mouseX,gridX1,gridX2,1,30) + 1;

				if(dragVar > targetTask.getStart() && dragVar <= 30){
					targetTask.setEnd(dragVar);
				}

			}
		}
		
		
			
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
