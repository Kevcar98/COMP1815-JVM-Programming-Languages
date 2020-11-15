import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProjectViewer {
    public JPanel ProjectVPanel;
    private JButton backToMainMenuButton;
    public JList projectJList;
    private ProjectHandler handler;
    private List<Project> project;

    public ProjectViewer() {
        handler = new ProjectHandler();
        project = handler.loadProjects();
        if(project != null) {
            projectJList.setListData(project.toArray(new Project[0])); // converts list to new array for JList
        } else if(project == null) {
            System.out.println("ERROR: Project is null");
        } else {
            System.out.println("ERROR: Project is of unknown status");
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
                // Closes current window - Source: https://stackoverflow.com/a/51356151
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });
        projectJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
    }
}
