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
    private ScalaCP cphandler ;


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
               String ProjectID=ProjectIDF.getText();
               String AssignedTasksID = handler.retrieveAssignedTasksID(ProjectID);
               System.out.print(AssignedTasksID);
               String AssignedTasks[]=AssignedTasksID.split(" & ");//array conatains all tasks in the Project
               ArrayList<String> LAssignedPTasks = new ArrayList<String>();
               ArrayList<String> LAssignedNPTasks = new ArrayList<String>();

               int count=0;
               for (int i=0; i<AssignedTasks.length;i++ ) {
                   if (AssignedTasks[i] != null) {
                       String text = AssignedTasks[i];
                       if (text.contains("->")) {
                           LAssignedPTasks.add(text);
                       }
                       else{
                           LAssignedNPTasks.add(text);
                       }
                   }
               }
               System.out.print(LAssignedPTasks.toString());
               Object[] OAssignedPTasks = LAssignedPTasks.toArray();// contains all tasks that requires prerequisites in the project
               Object[] OAssignedNPTasks = LAssignedNPTasks.toArray(); //contains all tasks that don't require prerequisites in the project
               String[] AssignedPTasks = Arrays.stream(OAssignedPTasks).toArray(String[]::new);
               String[] AssignedNPTasks = Arrays.stream(OAssignedNPTasks).toArray(String[]::new);
               cphandler.main(AssignedPTasks,AssignedNPTasks);










            }
        });
    }
}
