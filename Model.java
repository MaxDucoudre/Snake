	import java.lang.*;
	import java.util.*;
	import java.io.*;
	public class Model {
		private char[][] grid;
		private int numberMaxOfBonus;
		private Movement direction;
		private List<SnakePiece> snake;
		private boolean lost;
		private int score;

		private int gridWidth;
		private int gridHeight;


		public Model(int gridWidth, int gridHeight, int numberOfBonus) {

			this.gridWidth = gridWidth;
			this.gridHeight = gridHeight;
			this.numberMaxOfBonus = numberOfBonus;

			this.score = 0;
			this.direction = Movement.LEFT;
			this.grid = new char[gridWidth][gridHeight];
			this.lost = false;

			int i,j;

			for(i = 0; i<grid.length; i++) {
				for(j = 0; j<grid[0].length; j++) {
					this.grid[i][j] = 'V';
				}
			}
			this.snake = new ArrayList<SnakePiece>();
			this.snake.add(0, new SnakePiece(gridWidth/2,gridHeight/2));

			this.spawnBonus();

		}


		
		public void spawnBonus() {
			System.out.println("New Bonus!");
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

			for(i = 0; i<this.numberMaxOfBonus+1-bonusNumber; i++) {

				spawnX = random.nextInt(gridWidth-1);
				spawnY = random.nextInt(gridHeight-1);

				while(grid[spawnX][spawnY] != 'V' && grid[spawnX][spawnY] != 'S') {
					spawnX = random.nextInt(gridWidth-1);
					spawnY = random.nextInt(gridHeight-1);
				}

				this.grid[spawnX][spawnY] = 'B';
			}


		}

		public void eatBonus() {
			System.out.println("Bonus!");
			this.score++;
			this.spawnBonus();
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
		}

		public boolean isOver() {
			
			return this.lost;
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

			piece = this.snake.get(0);
			this.grid[piece.x][piece.y] = 'H';

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
						headSnake.x = headSnake.x-1;
					}
					else if(this.grid[headSnake.x-1][headSnake.y] == 'S') {
						this.gameOver();
					} else {
						headSnake.x = headSnake.x-1;
					}
				} else if(this.direction == Movement.DOWN) {
					if(this.grid[headSnake.x+1][headSnake.y] == 'B') {
						this.eatBonus();
						headSnake.x = headSnake.x+1;
					}
					else if(this.grid[headSnake.x+1][headSnake.y] == 'S') {
						this.gameOver();
					} else {
						headSnake.x = headSnake.x+1;
					}
				} else if(this.direction == Movement.LEFT) {
					if(this.grid[headSnake.x][headSnake.y-1] == 'B') {
						this.eatBonus();
						headSnake.y = headSnake.y-1;
					}
					else if(this.grid[headSnake.x][headSnake.y-1] == 'S') {
						this.gameOver();
					} else {
						headSnake.y = headSnake.y-1;
					}
				} else if(this.direction == Movement.RIGHT) {
					if(this.grid[headSnake.x][headSnake.y+1] == 'B') {
						this.eatBonus();
						headSnake.y = headSnake.y+1;
					}
					 else if(this.grid[headSnake.x][headSnake.y+1] == 'S') {
						this.gameOver();
					} else {
						headSnake.y = headSnake.y+1;
					}

				}
				if(this.isOver() == false) {
					this.bodyFollowHead();
					this.updateSnakeGrid();	
				}				
				

			} catch(ArrayIndexOutOfBoundsException e) {
				this.gameOver();
			}
		}

		public String getHightScore() {
			String data = "";
			try {
		      File myObj = new File("./hightscore");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      
		    }
		    return data;
		}

		public void setHightScore(int score) {
			try {
		      FileWriter myWriter = new FileWriter("./hightscore");
		      myWriter.write(String.valueOf(score));
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		}


		public void gameOver() {
			System.out.println("Game over!");

			if(Integer.parseInt(this.getHightScore()) < this.score) {
				this.setHightScore(this.score);
			}
			this.lost = true;

		}

		public void setDirection(Movement direction) {
			this.direction = direction;
		}

		public Movement getDirection() {
			return this.direction;
		}

		public int getSnakeSize() {
			return this.snake.size();
		}

		public char[][] getGrid() {
			return this.grid;
		}


		public int getScore() {
			return this.score;
		}

		public SnakePiece getSnakeHead() {
			return this.snake.get(0);

		}

		public int getGridWidth() {
			return this.gridWidth;
		}
		public int getGridHeight() {
			return this.gridHeight;
		}
	}
