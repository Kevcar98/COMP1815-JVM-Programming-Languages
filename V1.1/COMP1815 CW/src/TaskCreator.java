import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class TaskCreator {
    private JButton backToMainMenuButton;
    private JTextField ProjectIDF;
    private JTextField AssignedTeamsF;
    private JTextField ProjectManagerF;
    private JTextField CommissionerF;
    private JTextField TaskIDF;
    public JPanel TaskCPanel;
    private JButton createTaskButton;
    private JTextField TaskDurationF;
    private TaskHandler handler;
    private List<Tasks> task;

    public TaskCreator() {
        handler = new TaskHandler();

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
        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ProjectCreator.validationCheck(TaskIDF.getText(), true) &&
                        ProjectCreator.validationCheck(ProjectIDF.getText(), true) &&
                        ProjectCreator.validationCheck(CommissionerF.getText(), false) &&
                        ProjectCreator.validationCheck(ProjectManagerF.getText(), false) &&
                        ProjectCreator.validationCheck(TaskDurationF.getText(), true) &&
                        ProjectCreator.validationCheck(AssignedTeamsF.getText(), true)
                ) {
                    task = handler.createTask(
                            TaskIDF.getText(),
                            ProjectIDF.getText(),
                            CommissionerF.getText(),
                            ProjectManagerF.getText(),
                            TaskDurationF.getText(),
                            AssignedTeamsF.getText(),
                            "0"
                    );
                    handler.save(task);
                    JOptionPane.showMessageDialog(TaskCPanel, "Task saved.");

                    // Back to Main Menu
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
                } else {
                    JOptionPane.showMessageDialog(TaskCPanel, "Error! Avoid using special characters or invalid inputs (e.g. letters in a text field expecting only numbers)");
                }
            }
        });
    }
}
