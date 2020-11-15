import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectCreator {
    public JPanel ProjectCPanel;
    private JButton backToMainMenuButton;
    private JTextField AssignedTeamsF;
    private JButton verifyProjectButton;
    private JTextField ProjectIDF;
    private JTextField CommissionerF;
    private JTextField ProjectManagerF;
    private JTextField NumOfTasksF;
    private JLabel ResultF;
    private JButton createProjectButton;
    private ProjectHandler handler;
    private List<Project> project;

    public static boolean validationCheck(String s, boolean numberOnly) {
        Pattern alpha = Pattern.compile("[^A-Za-z0-9 ]");
        Pattern numbers = Pattern.compile("[^0-9]");
        Matcher alphaMatcher = alpha.matcher(s);
        Matcher numbersMatcher = numbers.matcher(s);
        boolean alphaBoolean = alphaMatcher.find();
        boolean numbersBoolean = numbersMatcher.find();
        if ((alphaBoolean && !numberOnly) || (numbersBoolean && numberOnly)) {
            System.out.println(s + " did not pass validation check");
            return false;
        }
        return true;
    }

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
        verifyProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( validationCheck(ProjectIDF.getText(), true) &&
                    validationCheck(CommissionerF.getText(), false) &&
                    validationCheck(ProjectManagerF.getText(), false) &&
                    validationCheck(NumOfTasksF.getText(), true) &&
                    validationCheck(AssignedTeamsF.getText(), true)
                ) {
                    project = handler.createProject(ProjectIDF.getText(), CommissionerF.getText(), ProjectManagerF.getText(), NumOfTasksF.getText(), AssignedTeamsF.getText());
                    ResultF.setText(project.get(project.size() - 1).toString()); // displays last item in list
                } else {
                    JOptionPane.showMessageDialog(ProjectCPanel, "Error! Avoid using special characters or invalid inputs (e.g. letters in a text field expecting only numbers)");
                    ResultF.setText("Project details appear here:");
                }
                // Resizes and centers current window by re-packing it
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.pack();
                win.setLocationRelativeTo(null);
            }
        });
        createProjectButton.addActionListener(new ActionListener() {
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
