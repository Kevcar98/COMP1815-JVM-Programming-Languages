import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProjectCreator {
    public JPanel ProjectCPanel;
    private JButton backToMainMenuButton;
    private JTextField AssignedTeamsF;
    private JButton createProjectButton;
    private JTextField ProjectIDF;
    private JTextField CommissionerF;
    private JTextField ProjectManagerF;
    private JTextField NumOfTasksF;
    private JLabel ResultF;
    private JButton saveProjectButton;
    private ProjectHandler handler;
    private List<Project> project;

    public ProjectCreator() {
        handler = new ProjectHandler();

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame HomePF = new JFrame("Home Page");
                HomePF.setContentPane(new HomePage().HomePanel);
                HomePF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                HomePF.pack();
                HomePF.setVisible(true);
                HomePF.setLocationRelativeTo(null);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                project = handler.createProject(ProjectIDF.getText(), CommissionerF.getText(), ProjectManagerF.getText(), NumOfTasksF.getText(), AssignedTeamsF.getText());
                ResultF.setText(project.get(project.size() - 1).toString()); // displays last item in list
                // Resizes and centers current window by re-packing it
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.pack();
                win.setLocationRelativeTo(null);
            }
        });
        saveProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(project.size());
                handler.save(project);
                JFrame HomePF = new JFrame("Home Page");
                HomePF.setContentPane(new HomePage().HomePanel);
                HomePF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                HomePF.pack();
                HomePF.setVisible(true);
                HomePF.setLocationRelativeTo(null);
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                JOptionPane.showMessageDialog(ProjectCPanel, "Project saved.");

            }
        });
    }
}
