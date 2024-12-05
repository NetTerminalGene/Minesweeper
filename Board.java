import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JComponent implements ActionListener{


	private static final long serialVersionUID = 2219119240388800376L;
	private JButton[][] msBoard;
	private Grid msGrid;

	public Board() {
		msBoard = new JButton[10][10];
		msGrid = new Grid();

		this.setLayout(new GridLayout(10, 10));

		for(int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				msBoard[row][col] = new JButton("F");
				msBoard[row][col].setFont(new Font("Serif", Font.BOLD, 40));
				this.add(msBoard[row][col]);
				msBoard[row][col].addActionListener(this);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton)e.getSource();

		int rowIndex = -1;
		int colIndex = -1;
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				if(buttonClicked.equals(msBoard[row][col])) {
					rowIndex = row;
					colIndex = col;
				}
			}
		}

		if(msGrid.isBombAtLocation(rowIndex, colIndex)) {
			buttonClicked.setText("T");
			buttonClicked.setForeground(Color.RED);
			revealBombs();
			promptPlayAgain();
		}else {
			int bombNum = msGrid.getCountAtLocation(rowIndex, colIndex);//countAdjBombs(rowIndex, colIndex);
			buttonClicked.setText(String.valueOf(bombNum));
			if(bombNum == 0) {
				revealCells(rowIndex,colIndex);
			}
		}
	}

	private int countAdjBombs(int row, int col) {
		int count = 0;

		for(int i = row - 1; i <= row + 1; i++) {
			for(int j = col - 1; j <= col + 1; j++) {
				if ((i >= 0 && i < 10) && (j >= 0 && j < 10) && (msGrid.isBombAtLocation(i, j))){
					count++;
				}
			}
		}if(msGrid.isBombAtLocation(row, col)) {
			count--;
		}
		return count;
	}

	private void revealCells(int row, int col) {
		
		
		//if ((row < 0) || (row >= msBoard.length) || (col < 0) || (col >= msBoard.length)){
			//return;
		//}
		int bombCount = msGrid.getCountAtLocation(row, col);//countAdjBombs(rowIndex, colIndex);
		if(bombCount == 0) {
			msBoard[row][col].setText("" +bombCount);
			for(int i = row - 1; i <= row + 1; i++) {
				for (int j = col - 1; j <= col + 1; j++) {
					int bombCountCurr = msGrid.getCountAtLocation(i, j);//countAdjBombs(rowIndex, colIndex);
					if(bombCountCurr == 0) {
						msBoard[i][j].setText("" +bombCountCurr);
						//revealCells(i,j);
					}
				}
			}
		}
//		} 
	}

	private void revealBombs() {
		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {
				if(msGrid.isBombAtLocation(row, col)) {
					msBoard[row][col].setText("T");
					msBoard[row][col].setForeground(Color.RED);

				}
			}
		}
	}

	private void promptPlayAgain() {
		int yesNo = JOptionPane.showConfirmDialog(null, "Play Again?", "YOU LOSE",JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION){
			Game.main(null);
		}
		else{
			System.exit(JFrame.EXIT_ON_CLOSE);
		}
	}
}


/*for (row = 0; row < msGrid.getNumRows(); row++) {
	for (col = 0; col < msGrid.getNumColumns(); col++) {
		if(row > 0) {
			msBoard[row - 1][col].setVisible(true); // Top
			if(col > 0) {
				msBoard[row - 1][col - 1].setVisible(true); // Top-left
			}if (col < 9) {
				msBoard[row - 1][col + 1].setVisible(true); //Top-right
			}
		}
		if(row < 9) {
			msBoard[row + 1][col].setVisible(true); // Bottom
			if(col > 0) {
				msBoard[row + 1][col - 1].setVisible(true); // Bottom-left
			}
			if(col < 9) {
				msBoard[row + 1][col + 1].setVisible(true); // Bottom-right
			}
		}
		if(col > 0) {
			msBoard[row][col - 1].setVisible(true); // Left
		}
		if(col < 9) {
			msBoard[row][col + 1].setVisible(true); // Right
		}
	}
} */
