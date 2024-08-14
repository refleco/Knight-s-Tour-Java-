import processing.core.PApplet;
import processing.core.PImage;

public class KnightRunner extends PApplet{

	PImage im;
	int count = 0;
	Knight lancelot = new Knight(new RowColPair(5,6),10);
	RowColPair[] john = lancelot.solve();
	int nums = (int) Math.sqrt(john.length);
	
	
	public void settings() {
		this.size(50*nums, 50*nums);
	}
	
	public void setup() {
		
		
		background(255);
		im = this.loadImage("x.png");
		int x = 0;
		int y = 0;
		for (int i = 0; i < nums; i ++) {
			for (int j = 0; j < nums; j++) {
				if ((i+j) % 2 ==0) {
					fill(0);
				}
				else {
					fill(255);
				}
				rect(x,y,50,50);
				x+=50;
			}
			y+=50;
			x=0;
		}
	}
	
	public void draw() {
		
		

		background(255);
		im = this.loadImage("x.png");
		int x = 0;
		int y = 0;
		for (int i = 0; i < nums; i ++) {
			for (int j = 0; j < nums; j++) {
				if ((i+j) % 2 ==0) {
					fill(0);
				}
				else {
					fill(255);
				}
				rect(x,y,50,50);
				x+=50;
			}
			y+=50;
			x=0;
		}
	
		
		
		if (count < john.length)
			this.image(im,john[count].getRow()*50,john[count].getColumn()*50);
			count++;
		delay(100);
	}
	
	
	public static void main(String[] args) {
		PApplet.main("KnightRunner");
		
		
		 
		
	}

}
