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
        JFrame HomePF = new JFrame("HomePage");
        HomePF.setContentPane(new HomePage().HomePanel);
        HomePF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomePF.pack();
        HomePF.setVisible(true);

    }

    public HomePage() {
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateTF = new JFrame("TeamCreator");
                CreateTF.setContentPane(new TeamCreator().TeamCPanel);
                CreateTF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreateTF.pack();
                CreateTF.setVisible(true);


            }
        });
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreatePF = new JFrame("ProjectCreator");
                CreatePF.setContentPane(new ProjectCreator().ProjectCPanel);
                CreatePF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreatePF.pack();
                CreatePF.setVisible(true);

            }
        });
        viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ViewTF = new JFrame("TeamViewer");
                ViewTF.setContentPane(new TeamViewer().TeamVPanel);
                ViewTF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ViewTF.pack();
                ViewTF.setVisible(true);

            }
        });
        viewProjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ViewPF = new JFrame("ProjectViewer");
                ViewPF.setContentPane(new ProjectViewer().TeamVPanel);
                ViewPF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ViewPF.pack();
                ViewPF.setVisible(true);

            }
        });
    }
}
