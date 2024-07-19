import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import Data.Constants;
import Layout.Menu;
import State.Window;
import State.Window.W;
import State.Window.WindowListener;
import Views.Projects.ProjectsView;
import Views.Report.ReportView;
import Views.Scientifics.ScientificView;

public class Main extends JFrame implements WindowListener {

    public Menu $Menu = new Menu(
            e -> setContent(new ProjectsView()),
            e -> setContent(new ScientificView()),
            e -> setContent(new ReportView()));

    public JPanel $Content = new JPanel();

    public JSplitPane $Window;

    public Main() {
        super("Proyecto");
        $Content.setLayout(new CardLayout());
        $Content.add(new ProjectsView()); // default
        $Window = new JSplitPane(JSplitPane.VERTICAL_SPLIT, $Menu, $Content);
        $Window.setDividerSize(0);
        add($Window);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setVisible(true);
        W.bind(this);
    }

    public void setContent(JPanel panel) {
        $Content.removeAll();
        $Content.add(panel);
        $Content.revalidate();
        $Content.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }

    @Override
    public void onChange(Window w) {
        setContent(w.newView);
    }
}