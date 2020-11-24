import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class TeamViewer {
    public JPanel TeamVPanel;
    private JButton backToMainMenuButton;
    private JList teamJList;
    private TeamHandler handler;
    private List<Teams> team;

    public TeamViewer() {
        handler = new TeamHandler();
        team = handler.loadTeams();
        if (team != null) {
            teamJList.setListData(team.toArray(new Teams[0])); // converts list to new array for JList
        } else if (team == null) {
            System.out.println("ERROR: Team is null");
        } else {
            System.out.println("ERROR: Team is of unknown status");
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
        teamJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
    }
}
