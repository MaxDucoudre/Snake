	import java.lang.*;
	import java.util.*;

	public class Model {
		private char[][] grid;
		private int numberMaxOfBonus;
		private Movement direction;
		private List<SnakePiece> snake;

		public Model() {
			this.numberMaxOfBonus = 3;
			this.direction = Movement.LEFT;
			this.grid = new char[25][25];

			int i,j;

			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					this.grid[i][j] = 'V';
				}
			}
			this.snake = new ArrayList<SnakePiece>();
			this.snake.add(0, new SnakePiece(12,13));

			this.spawnBonus();
		}



		public void spawnBonus() {
			int i,j;
			int spawnX, spawnY;
			Random random = new Random();
			int bonusNumber = 0;
			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'B') {
						bonusNumber++;
					}
				}
			}

			for(i = 0; i<this.numberMaxOfBonus-bonusNumber; i++) {

				spawnX = random.nextInt(24);
				spawnY = random.nextInt(24);

				while(grid[spawnX][spawnY] != 'V' || grid[spawnX][spawnY] != 'S') {
					spawnX = random.nextInt(24);
					spawnY = random.nextInt(24);
				}

				this.grid[spawnX][spawnY] = 'B';
			}
		}

		public void eatBonus() {
			System.out.println("Bonus!");
			int i,j;
			SnakePiece newPiece    = this.snake.get(this.snake.size()-1);

			this.snake.add(this.snake.size()-1, new SnakePiece(newPiece.x,newPiece.y));
			SnakePiece parentPiece = this.snake.get(this.snake.size()-2);
			newPiece    = this.snake.get(this.snake.size()-1);

			if(this.grid[parentPiece.x][parentPiece.y+1] != 'S' || this.grid[parentPiece.x][parentPiece.y+1] != 'B') {
				newPiece.x = parentPiece.x;
				newPiece.y = parentPiece.y+1; 
			} else if(this.grid[parentPiece.x][parentPiece.y-1] != 'S' || this.grid[parentPiece.x][parentPiece.y-1] != 'B') {
				newPiece.x = parentPiece.x;
				newPiece.y = parentPiece.y-1; 
			} else if(this.grid[parentPiece.x+1][parentPiece.y] != 'S' || this.grid[parentPiece.x+1][parentPiece.y] != 'B') {
				newPiece.x = parentPiece.x+1;
				newPiece.y = parentPiece.y; 
			} else if(this.grid[parentPiece.x-1][parentPiece.y] != 'S' || this.grid[parentPiece.x-1][parentPiece.y] != 'B') {
				newPiece.x = parentPiece.x-1;
				newPiece.y = parentPiece.y; 
			}
			this.spawnBonus();
		}

		public boolean checkCollision() {
			return false;
		}


		public void refreshGrid() {
			int i,j;
			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					if(this.grid[i][j] == 'B') {
						this.grid[i][j] = 'B';
					} else {
						this.grid[i][j] = 'V';
					}

				}
			}
		}

		public void updateSnakeGrid() {
			Iterator<SnakePiece> it = this.snake.iterator();
			SnakePiece piece;

			this.refreshGrid();
			while(it.hasNext()) {
				piece = it.next();
				this.grid[piece.x][piece.y] = 'S';
			}

		}

		public void bodyFollowHead() {
			SnakePiece p1;
			SnakePiece p2;

			for (int i = this.snake.size() - 1; i > 0; i--) {
				p1 = this.snake.get(i - 1);
				p2 = this.snake.get(i);
				p2.x = p1.x;
				p2.y = p1.y;
			}		
		}

		public void move() {
			int i,j;
			SnakePiece headSnake = this.snake.get(0);

			try {
				if(this.direction == Movement.UP) {
					if(this.grid[headSnake.x-1][headSnake.y] == 'B') {
						this.eatBonus();
					}
					headSnake.x = headSnake.x-1;

				} else if(this.direction == Movement.DOWN) {
					if(this.grid[headSnake.x+1][headSnake.y] == 'B') {
						this.eatBonus();
					}
					headSnake.x = headSnake.x+1;

				} else if(this.direction == Movement.LEFT) {
					if(this.grid[headSnake.x][headSnake.y-1] == 'B') {
						this.eatBonus();
					}
					headSnake.y = headSnake.y-1;

				} else if(this.direction == Movement.RIGHT) {
					if(this.grid[headSnake.x][headSnake.y+1] == 'B') {
						this.eatBonus();
					}
					headSnake.y = headSnake.y+1;

				}


				this.bodyFollowHead();
				this.updateSnakeGrid();	
			} catch(ArrayIndexOutOfBoundsException e) {
				this.gameOver();
			}
		}

		public void gameOver() {
			System.out.println("Game over!");
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




	}
