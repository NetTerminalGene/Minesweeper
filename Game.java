import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("Minesweeper");
		Board board = new Board();
		window.add(board);
		
		window.setSize(1000, 1000);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}