import java.lang.*;

public class Model {
	private char[][] grid;
	private Movement direction;


	public Model() {
		this.direction = Movement.LEFT;
		this.grid = new char[25][25];

		int i,j;

		for(i = 0; i<grid.length; i++) {
			for(j = 0; j<grid[0].length; j++) {
				this.grid[i][j] = 'V';
			}
		}

		this.grid[12][13] = 'S';

	}

	public boolean checkCollision() {
		return false;
	}

	public void setDirection(Movement direction) {
		this.direction = direction;
	}

	public Movement getDirection() {
		return this.direction;
	}

	public char[][] getGrid() {
		return this.grid;
	}

	public void move() {
		int i,j;

		if(this.direction == Movement.UP) {

			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'S') {
						this.grid[i][j-1] = 'S';
						this.grid[i][j] = 'V';
					}
				}
			}

		} else if(this.direction == Movement.DOWN) {
			
		} else if(this.direction == Movement.LEFT) {
			
		} else if(this.direction == Movement.RIGHT) {
			
		}
	}


}
