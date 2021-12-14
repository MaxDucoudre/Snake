import java.lang.*;
import java.util.*;

public class Model {
	private char[][] grid;
	private char[][] copyGrid;
	private int numberMaxOfBonus;
	private Movement direction;
	private int snakeSize;
	private int[] lastSnakePiece;
	private int[] snakeHead;
	private ArrayDeque<SnakePiece> snake;

	public Model() {
		this.numberMaxOfBonus = 3;
		this.direction = Movement.LEFT;
		this.grid = new char[25][25];
		this.copyGrid = new char[25][25];
		this.snakeSize = 1;
		this.lastSnakePiece = new int[2];
		this.snakeHead = new int[2];

		int i,j;

		for(i = 0; i<grid.length; i++) {
			for(j = 0; j<grid[0].length; j++) {
				this.grid[i][j] = 'V';
				this.copyGrid[i][j] = 'V';
			}
		}

		this.grid[12][13] = 'S';
		this.copyGrid[12][13] = 'S';
		this.lastSnakePiece[0] = 12;
		this.lastSnakePiece[1] = 13;		
		this.snakeHead[0] = 12;
		this.snakeHead[1] = 13;

		this.spawnBonus();
	}

	public void spawnBonus() {
		int i;
		int j,k;
		int spawnX, spawnY;
		Random random = new Random();

		for(i = 0; i<this.numberMaxOfBonus; i++) {

				spawnX = random.nextInt(24);
				spawnY = random.nextInt(24);

				while(grid[spawnX][spawnY] != 'V') {
					spawnX = random.nextInt(24);
					spawnY = random.nextInt(24);
				}

				this.grid[spawnX][spawnY] = 'B';
				this.copyGrid[spawnX][spawnY] = 'B';
		}
	}



	public void eatBonus() {
		System.out.println("Bonus!");
		this.snakeSize++;
		int i,j;
		if(this.lastSnakePiece[1]+1 < 24) {
			if(this.grid[this.lastSnakePiece[0]][this.lastSnakePiece[1]+1] == 'V') {
				this.grid[this.lastSnakePiece[0]][this.lastSnakePiece[1]+1] = 'S';
				this.copyGrid[this.lastSnakePiece[0]][this.lastSnakePiece[1]+1] = 'S';
			}
		}

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
		int iteration = 0;

		if(this.direction == Movement.UP) {
			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'S') {
						if(i>0) {
							iteration++;
							if(iteration == this.snakeSize) { // derniere piece snake
								this.lastSnakePiece[0] = i;
								this.lastSnakePiece[1] = j;
							}
							if(iteration == 1) {
								this.snakeHead[0] = i;
								this.snakeHead[1] = j;
							}

							if(this.grid[this.snakeHead[0]-1][this.snakeHead[1]] == 'B') {
								this.eatBonus();
							}
							this.copyGrid[i-1][j] = 'S';
							this.copyGrid[i][j] = 'V';
						}
					}
				}
			}

		} else if(this.direction == Movement.DOWN) {
			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'S') {
						if(i < 24) {
							iteration++;
							if(iteration == this.snakeSize) { // derniere piece snake
								this.lastSnakePiece[0] = i;
								this.lastSnakePiece[1] = j;
							}
							if(iteration == 1) {
								this.snakeHead[0] = i;
								this.snakeHead[1] = j;
							}

							if(this.grid[this.snakeHead[0]+1][this.snakeHead[1]] == 'B') {
								this.eatBonus();
							}
							this.copyGrid[i+1][j] = 'S';
							this.copyGrid[i][j] = 'V'; 
						}
					}
				}
			}

		} else if(this.direction == Movement.LEFT) {
			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'S') {
						if(j > 0) {
							iteration++;
							if(iteration == this.snakeSize) { // derniere piece snake
								this.lastSnakePiece[0] = i;
								this.lastSnakePiece[1] = j;
							}
							if(iteration == 1) {
								this.snakeHead[0] = i;
								this.snakeHead[1] = j;
							}

							if(this.grid[this.snakeHead[0]][this.snakeHead[1]-1] == 'B') {
								this.eatBonus();
							}
							this.copyGrid[i][j-1] = 'S';
							this.copyGrid[i][j] = 'V';
						}
					}
				}
			}

			
		} else if(this.direction == Movement.RIGHT) {
			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'S') {
						if(j < 24) {
							iteration++;
							if(iteration == this.snakeSize) { // derniere piece snake
								this.lastSnakePiece[0] = i;
								this.lastSnakePiece[1] = j;
							}
							if(iteration == 1) {
								this.snakeHead[0] = i;
								this.snakeHead[1] = j;
							}

							if(this.grid[this.snakeHead[0]][this.snakeHead[1]+1] == 'B') {
								this.eatBonus();
							}
							this.copyGrid[i][j+1] = 'S';
							this.copyGrid[i][j] = 'V';
						}
					}
				}
			}
		}

			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.copyGrid[i][j] == 'S') {
						this.grid[i][j] = 'S';
					} else if(this.copyGrid[i][j] == 'V') {
						this.grid[i][j] = 'V';
					} else if(this.copyGrid[j][j] == 'B') {
						this.grid[i][j] = 'B';
					}
				}
			}		

	}


}
