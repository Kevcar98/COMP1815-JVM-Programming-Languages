import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage {
    private JButton viewTeamsButton;
    private JButton createTeamButton;
    private JButton createProjectButton;
    private JButton viewProjectsButton;
    public JPanel HomePanel;
    private JButton createTaskButton;
    private JButton viewTasksButton;


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
                JFrame CreateTmF = new JFrame("TeamCreator");
                CreateTmF.setContentPane(new TeamCreator().TeamCPanel);
                CreateTmF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreateTmF.pack();
                CreateTmF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();


            }
        });
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreatePrF = new JFrame("ProjectCreator");
                CreatePrF.setContentPane(new ProjectCreator().ProjectCPanel);
                CreatePrF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreatePrF.pack();
                CreatePrF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

            }
        });
        viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ViewTmF = new JFrame("TeamViewer");
                ViewTmF.setContentPane(new TeamViewer().TeamVPanel);
                ViewTmF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ViewTmF.pack();
                ViewTmF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

            }
        });
        viewProjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ViewPrF = new JFrame("ProjectViewer");
                ViewPrF.setContentPane(new ProjectViewer().TeamVPanel);
                ViewPrF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ViewPrF.pack();
                ViewPrF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();


            }
        });
        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateTsF = new JFrame("TaskCreator");
                CreateTsF.setContentPane(new TaskCreator().TaskCPanel);
                CreateTsF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreateTsF.pack();
                CreateTsF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

            }
        });
        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateTsF = new JFrame("TasksViewer");
                CreateTsF.setContentPane(new TaskViewer().TaskVPanel);
                CreateTsF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreateTsF.pack();
                CreateTsF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });
    }
}
