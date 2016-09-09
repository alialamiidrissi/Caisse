package graphics;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SetupWindow extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BannerLayer historyFile, balanceFile;
    private JButton ok;

    public SetupWindow() {
        this.setTitle("Réglage");
        this.setSize(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(Box.createVerticalBox());
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        this.historyFile = new BannerLayer("history File", "choisir");
        this.getContentPane().add(historyFile);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        this.balanceFile = new BannerLayer("Balance File", "choisir");
        this.getContentPane().add(balanceFile);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        // buttonLayer
        JPanel okButton= new JPanel();
        okButton.setLayout(new BorderLayout());
        this.ok = new JButton("OK");
        ok.setPreferredSize(new Dimension(250, 50));
        okButton.add(ok, BorderLayout.CENTER);
        // end buttonLayer
        okButton.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        this.getContentPane().add(okButton);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        this.setResizable(true);
        this.setVisible(true);
    }

    public void setBalancePath(String path) {
        balanceFile.setPath(path);
    }

    public void setHistoryPath(String path) {
        historyFile.setPath(path);
    }

    public String getBalancePath() {
        return balanceFile.getPath();
    }

    public String getHistoryPath() {
        return historyFile.getPath();
    }

    public void ChoiceButton(int index,ActionListener action) {
        switch(index){
        case 0:
        historyFile.addButtonActionListener(action);
        break;
        case 1:
        balanceFile.addButtonActionListener(action);
        break;}
    }

    public void okButton(ActionListener action) {
        ok.addActionListener(action);
    }

}
