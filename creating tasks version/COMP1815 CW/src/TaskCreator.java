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
    private JTextField CommisionerF;
    private JTextField TaskIDF;
    public JPanel TaskCPanel;
    private JButton createTaskButton;
    private JLabel CommissionerF;
    private JTextField TaskDurationL;
    private JLabel DurationF;
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
                task = handler.createTask(TaskIDF.getText(),ProjectIDF.getText(), CommissionerF.getText(), ProjectManagerF.getText(), DurationF.getText(), AssignedTeamsF.getText());
            }
        });
    }
}
