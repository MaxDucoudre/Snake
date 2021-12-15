import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	
	private JPanel gridPanel;
	private JLabel[][] labels;
	private JLabel scoreLabel;
	private int gridHeight;
	private int gridWidth;
	private JLabel hightScoreLabel;

	public Window(int gridWidth, int gridHeight) {
		super();
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());
		this.setLocation(100, 30);
		this.setTitle("Snake");
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;

		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridLayout(this.gridWidth,this.gridHeight));
		this.add(gridPanel, BorderLayout.CENTER);

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new GridLayout(2,1));

		this.scoreLabel = new JLabel("Score : 0",  SwingConstants.CENTER);
		headerPanel.setBackground(Color.ORANGE);
		headerPanel.add(scoreLabel);

		hightScoreLabel = new JLabel("Hight score : 0",  SwingConstants.CENTER);
		headerPanel.add(hightScoreLabel);



		this.add(headerPanel, BorderLayout.NORTH);

		int i,j;

		this.labels = new JLabel[this.gridWidth][this.gridHeight];


		for(i = 0; i < 25; i++) {
			for(j = 0; j < 25; j++) {
				this.labels[i][j] = new JLabel();
				this.labels[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
				this.labels[i][j].setBackground(Color.GREEN);
				this.labels[i][j].setOpaque(true);
				this.gridPanel.add(this.labels[i][j]);
			}
		}
	}

	public void setHightScore(String hightScore) {
		this.hightScoreLabel.setText("Hight score : " + hightScore);
	}

	public void setGridView(char[][] grid) {
		int i,j;

		for(i = 0; i < this.gridWidth; i++) {
			for(j = 0; j < this.gridHeight; j++) {
				if(grid[i][j] == 'V') {
				this.labels[i][j].setBackground(Color.GREEN);
				} else if(grid[i][j] == 'S') {
				this.labels[i][j].setBackground(Color.ORANGE);
				} else if(grid[i][j] == 'B') {
				this.labels[i][j].setBackground(Color.RED);
				} else if(grid[i][j] == 'H') { 
				this.labels[i][j].setBackground(Color.DARK_GRAY);

				}
			}
		}

		this.gridPanel.updateUI();

	}

	public void setScoreLabel(int score) {
		this.scoreLabel.setText("Score : " + score);
	}


}