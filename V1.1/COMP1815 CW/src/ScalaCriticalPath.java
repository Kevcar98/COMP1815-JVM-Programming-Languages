import CriticalPath.ScalaCP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ScalaCriticalPath {
    private JTextField ProjectIDF;
    private JList list1;
    private JButton submitButton;
    private JButton backToMainMenuButton;
    public JPanel ScalaCPPanel;
    private ProjectHandler handler;
    private ScalaCP cphandler;


    public ScalaCriticalPath() {
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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cphandler = new ScalaCP();
                String ProjectID = ProjectIDF.getText();
                String AssignedTasksID = handler.retrieveAssignedTasksID(ProjectID);
                System.out.println(AssignedTasksID); // "31 & 32 & 123->33 & 1+2->5"
                String[] AssignedTasks = AssignedTasksID.split(" & "); // [31,32,123->33,1+2->5]
                String preq = "";
                String nPreq = "";
                for (int i = 0; i < AssignedTasks.length; i++) {
                    if (AssignedTasks[i].contains("->")) {
                        if (preq.isEmpty()) {
                            preq = AssignedTasks[i];
                        } else {
                            preq += "," + AssignedTasks[i];
                        } // if it is prerequisite task (e.g. 1+2->5), then add it to preq String, separated by , (e.g. preq = "123->33,1+2->5")
                    } else {
                        if (nPreq.isEmpty()) {
                            nPreq = AssignedTasks[i];
                        } else {
                            nPreq += "," + AssignedTasks[i];
                        } // nPreq = "31,32"
                    }
                }
                String[] AssignedPTasks = preq.split(","); // [123->33,1+2->5]
                String[] AssignedNPTasks = nPreq.split(","); // [31,32]

                cphandler.main(AssignedPTasks,AssignedNPTasks);
            }
        });
    }
}
