import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class TaskCreator {
    public JPanel TaskCPanel;
    private JButton backToMainMenuButton;
    private JTextField ProjectManagerF;
    private JTextField CommissionerF;
    private JTextField TaskIDF;
    private JButton createTaskButton;
    private JTextField TaskDurationF;
    private JComboBox assignProjectsJBox;
    private JComboBox assignTeamsJBox;
    private JComboBox prerequisiteTasksJBox;
    private JCheckBox noPrerequisiteTasksCBox;
    private TaskHandler handler;
    private List<Tasks> task;
    private ProjectHandler proHandler;

    public TaskCreator() {
        handler = new TaskHandler();
        proHandler = new ProjectHandler();
        assignProjectsJBox.setModel(new DefaultComboBoxModel(handler.listProjectsForTask())); // Sets Projects combo box to list of ProjectIDs
        prerequisiteTasksJBox.setModel(new DefaultComboBoxModel(handler.listTasksForTasks())); // Sets Prerequisite Tasks combo box to list of TaskIDs
        if (assignProjectsJBox.getSelectedItem() != null) {
            // Sets Teams combo box to the Team IDs associated with the selected Project ID
            String[] projectTeams = handler.teamsAssignedToProject(assignProjectsJBox.getSelectedItem().toString());
            assignTeamsJBox.setModel(new DefaultComboBoxModel(handler.listTeamsForTask(projectTeams)));
        }

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
        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ProjectCreator.validationCheck(TaskIDF.getText(), true) &&
                        assignProjectsJBox.getSelectedItem() != null &&
                        ProjectCreator.validationCheck(CommissionerF.getText(), false) &&
                        ProjectCreator.validationCheck(ProjectManagerF.getText(), false) &&
                        ProjectCreator.validationCheck(TaskDurationF.getText(), true) &&
                        assignTeamsJBox.getSelectedItem() != null &&
                        (
                                (!noPrerequisiteTasksCBox.isSelected() && prerequisiteTasksJBox.getSelectedItem() != null) ||
                                (noPrerequisiteTasksCBox.isSelected())
                        ) // Checks if the "No Prerequisite checkbox is checked, and if not, it checks if the Prerequisite Tasks combo box is not null
                ) {
                    if (handler.uniqueIDCheck(TaskIDF.getText())) {
                        task = handler.createTask(
                                TaskIDF.getText(),
                                assignProjectsJBox.getSelectedItem().toString(),
                                CommissionerF.getText(),
                                ProjectManagerF.getText(),
                                TaskDurationF.getText(),
                                assignTeamsJBox.getSelectedItem().toString(),
                                "0"
                        );
                        handler.save(task);
                        // Gets value of TaskIDF and adds it to the String assignedTaskID which will hold any prerequisite task as well
                        String assignedTaskID = TaskIDF.getText();
                        if (assignedTaskID.isEmpty()) {
                            assignedTaskID = "0";
                        }
                        if (!noPrerequisiteTasksCBox.isSelected()) {
                            assignedTaskID = prerequisiteTasksJBox.getSelectedItem().toString() + "->" + assignedTaskID;
                        } // If the "No Prerequisites" checkbox is unchecked, use value of Prerequisite Tasks combo box
                        proHandler.updateProjectTaskData(
                                assignProjectsJBox.getSelectedItem().toString(),
                                assignedTaskID
                        ); // Upon creating a task, updates the relevant Project's AssignedTaskID to show this new task
                        JOptionPane.showMessageDialog(TaskCPanel, "Task saved.");

                        // Back to Main Menu
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
                    } else {
                        JOptionPane.showMessageDialog(TaskCPanel, "Error! Task ID is not unique!");
                    }
                } else if (assignProjectsJBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(TaskCPanel, "Error! There are no projects selected. Please select at least one project (or create one if there are none available).");
                } else if (assignTeamsJBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(TaskCPanel, "Error! There are no teams selected. Please select at least one team (or create one if there are none available).");
                } else if (!noPrerequisiteTasksCBox.isSelected() && prerequisiteTasksJBox.getSelectedItem() != null) {
                    JOptionPane.showMessageDialog(TaskCPanel, "Error! There are no prerequisite task selected. Please select at least one task (or create one if there are none available), or check the \"No Prerequisites\" checkbox.");
                } else {
                    JOptionPane.showMessageDialog(TaskCPanel, "Error! Avoid using special characters or invalid inputs (e.g. letters in a text field expecting only numbers)");
                }
            }
        });
        assignProjectsJBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (assignProjectsJBox.getSelectedItem() != null) {
                    // Sets Teams combo box to the Team IDs associated with the selected Project ID
                    String[] projectTeams = handler.teamsAssignedToProject(assignProjectsJBox.getSelectedItem().toString());
                    assignTeamsJBox.setModel(new DefaultComboBoxModel(handler.listTeamsForTask(projectTeams)));
                }
            }
        });
    }
}
