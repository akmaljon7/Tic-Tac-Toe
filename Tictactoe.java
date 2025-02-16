package tictactoe;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tictactoe extends JPanel {
    private static final long serialVersionUID = 1L;
	private int totalCells = 9;
    private int totalRows = 3;
     private char playerSign = 'X';
   private int totalColumns = 3;
    private JButton[] buttons = new JButton[totalCells];

    public Tictactoe() {
        GridLayout ticTacToeGridLayout = new GridLayout(totalRows, totalColumns);
        setLayout(ticTacToeGridLayout);
        createButtons();
    }

    public void createButtons() {
        for (int i = 0; i < totalCells; i++) {
    buttons[i] = new JButton();
    buttons[i].setText("");
    buttons[i].addActionListener(e -> {
    	
        JButton clickedBtn = (JButton) e.getSource();
        clickedBtn.setText(String.valueOf(playerSign));
        clickedBtn.setEnabled(false);
        if (playerSign == 'X') {
            playerSign = 'O';
        } else {
            playerSign = 'X';
        }
        showWinner();
    });
    add(buttons[i]);
}
    }

    public void showWinner() {
    if (checkForWinner()) {
        if (playerSign == 'X') {
            playerSign = 'O';
        } else {
            playerSign = 'X';
        }
        int dialogResult = JOptionPane.showConfirmDialog(
                null,
                "Game Over. The winner is " + playerSign,
                "Result",
                JOptionPane.DEFAULT_OPTION
        );
        if (dialogResult == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    } else if (checkIfMatchDraw()) {
        int dialogResult = JOptionPane.showConfirmDialog(
                null,
                "Game Draw",
                "Result",
                JOptionPane.DEFAULT_OPTION
        );
        if (dialogResult == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
}

public boolean checkIfMatchDraw() {
    for (int i = 0; i < totalCells; i++) {
        if (buttons[i].getText().isEmpty()) {
            return false;
        }
    }
    return true;
}

    public boolean checkForWinner() {
        return checkAllRows() || checkAllColumns() || checkTheDiagonals();
    }

    public boolean checkAllRows() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) &&
                    buttons[i].getText().equals(buttons[i + 2].getText()) &&
                    !buttons[i].getText().isEmpty()) {
                return true;
            }
            i += 2;
        }
        return false;
    }

    public boolean checkAllColumns() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText()) &&
                    buttons[i].getText().equals(buttons[i + 6].getText()) &&
                    !buttons[i].getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTheDiagonals() {
        return (buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText()) &&
                !buttons[0].getText().isEmpty()) ||
                (buttons[2].getText().equals(buttons[4].getText()) &&
                        buttons[2].getText().equals(buttons[6].getText()) &&
                        !buttons[2].getText().isEmpty());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tic Tac Toe");
            frame.getContentPane().add(new Tictactoe());
            frame.setBounds(500, 500, 600, 550);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
