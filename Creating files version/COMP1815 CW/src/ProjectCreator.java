import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectCreator {
    public JPanel ProjectCPanel;
    private JButton backToMainMenuButton;
    private JTextField AssignedTeamsF;
    private JButton createProjectButton;
    private JTextField ProjectIDF;
    private JTextField CommisionerF;
    private JTextField ProjectManagerF;
    private JTextField NumOfTasksF;
    private JLabel ResultF;
    private JButton saveProjectButton;
    private ProjectHandler handler;
    private Project project;

    public ProjectCreator() {
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame HomePF = new JFrame("HomePage");
                HomePF.setContentPane(new HomePage().HomePanel);
                HomePF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                HomePF.pack();
                HomePF.setVisible(true);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

            }
        });
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                project = handler.createProject(ProjectIDF.getText(), CommisionerF.getText(), ProjectManagerF.getText(), NumOfTasksF.getText(), AssignedTeamsF.getText());
                ResultF.setText(project.toString());


            }
        });
        saveProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handler.Save(project, true); }
        });
    }
}
