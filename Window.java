import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	
	private JPanel gridPanel;
	private JLabel[][] labels;
	public Window() {
		super();
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());
		this.setLocation(100, 30);
		this.setTitle("Snake");

		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridLayout(25,25));
		this.add(gridPanel, BorderLayout.CENTER);
		int i,j;

		this.labels = new JLabel[25][25];


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

	public void setGridView(char[][] grid) {
		int i,j;

		for(i = 0; i < 25; i++) {
			for(j = 0; j < 25; j++) {
				if(grid[i][j] == 'V') {
				this.labels[i][j].setBackground(Color.GREEN);
				} else if(grid[i][j] == 'S') {
				this.labels[i][j].setBackground(Color.ORANGE);
				} else if(grid[i][j] == 'B') {
				this.labels[i][j].setBackground(Color.RED);
				}
			}
		}
		this.gridPanel.updateUI();

	}


}