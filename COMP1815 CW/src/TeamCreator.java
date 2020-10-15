import javax.swing.*;

public class TeamCreator {

    public JPanel TeamCPanel;

    public static void main(String[] args){
        JFrame TeamC = new JFrame("TeamCreator");
        TeamC.setContentPane(new TeamCreator().TeamCPanel);
        TeamC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TeamC.pack();
        TeamC.setVisible(true);

    }



    public TeamCreator() {
    }
}
