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
    private JButton createProjectButton;
    private JTextField ProjectIDF;
    private JTextField CommissionerF;
    private JTextField ProjectManagerF;
    private JLabel ResultF;
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
                // Closes current window
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validationCheck(ProjectIDF.getText(), true) &&
                        validationCheck(CommissionerF.getText(), false) &&
                        validationCheck(ProjectManagerF.getText(), false) &&
                        validationCheck(AssignedTeamsF.getText(), true)
                ) {
                    if (handler.uniqueIDCheck(ProjectIDF.getText())) {
                        project = handler.createProject(ProjectIDF.getText(), CommissionerF.getText(), ProjectManagerF.getText(), "None Currently Assigned", AssignedTeamsF.getText());
                        ResultF.setText(project.get(project.size() - 1).toString()); // Displays last item in list
                        // Resizes and centers current window by re-packing it
                        JComponent comp = (JComponent) e.getSource();
                        Window win = SwingUtilities.getWindowAncestor(comp);
                        win.pack();
                        win.setLocationRelativeTo(null);

                        // Saves project
                        handler.save(project);
                        JOptionPane.showMessageDialog(ProjectCPanel, "Project saved.");
                    } else {
                        JOptionPane.showMessageDialog(ProjectCPanel, "Error! Project ID is not unique!");
                        ResultF.setText("Project details appear here:");
                    }
                } else {
                    JOptionPane.showMessageDialog(ProjectCPanel, "Error! Avoid using special characters or invalid inputs (e.g. letters in a text field expecting only numbers)");
                    ResultF.setText("Project details appear here:");
                }
            }
        });
    }
}
