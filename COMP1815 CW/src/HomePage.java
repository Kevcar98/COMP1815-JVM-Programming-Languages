import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage {
    private JButton viewTeamsButton;
    private JButton createTeamButton;
    private JButton createProjectButton;
    private JButton viewProjectsButton;
    private JPanel HomePanel;

    public static void main(String[] args){
        JFrame HomeP = new JFrame("HomePage");
        HomeP.setContentPane(new HomePage().HomePanel);
        HomeP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeP.pack();
        HomeP.setVisible(true);

    }

    public HomePage() {
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





            }
        });
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewProjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
