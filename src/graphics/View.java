package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -4809270283466159439L;
    private JButton add, substract, balance;
    private JButton[] choose = new JButton[2];
    private JTextField purpose, user;
    private JFormattedTextField amount;

    public View() {
        this.setTitle("Caisse");
        this.setSize(800, 160);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(Box.createVerticalBox());
        this.setVisible(true);
        // buttons
        JPanel buttons = new JPanel();
        GridLayout bLayout = new GridLayout(1, 3);
        bLayout.setHgap(40);
        buttons.setLayout(bLayout);
        add = new JButton("Crédit (+)");
        substract = new JButton("débit(-)");
        balance = new JButton("Solde actuel");
        buttons.add(add);
        buttons.add(substract);
        buttons.add(balance);

        // Motif
        JPanel purposePan = new JPanel();
        purposePan.setLayout(new BoxLayout(purposePan, BoxLayout.LINE_AXIS));
        JLabel pr = new JLabel("motif");
        purpose = new JTextField();
        purpose.setPreferredSize(new Dimension(750, 70));
        purposePan.add(Box.createRigidArea(new Dimension(20, 0)));
        purposePan.add(pr);
        purposePan.add(Box.createRigidArea(new Dimension(20, 0)));
        purposePan.add(purpose);
        purposePan.add(Box.createRigidArea(new Dimension(20, 0)));
        // montant+utilisateur
        JPanel userPan = new JPanel();
        userPan.setLayout(new BoxLayout(userPan, BoxLayout.LINE_AXIS));
        JLabel amountLabel = new JLabel("montant");
        JLabel userLabel = new JLabel("utilisateur");
        RegexFormatter formatter = new RegexFormatter(
                "(\\d*)|((\\d*)(\\.)(\\d*))");
        amount = new JFormattedTextField(formatter);
        formatter.setAllowsInvalid(false);
        amount.setPreferredSize(new Dimension(250, 70));
        user = new JTextField();
        user.setPreferredSize(new Dimension(250, 70));
        userPan.add(Box.createRigidArea(new Dimension(20, 0)));
        userPan.add(userLabel);
        userPan.add(Box.createRigidArea(new Dimension(20, 0)));
        userPan.add(user);
        userPan.add(Box.createRigidArea(new Dimension(20, 0)));
        userPan.add(amountLabel);
        userPan.add(Box.createRigidArea(new Dimension(20, 0)));
        userPan.add(amount);
        userPan.add(Box.createRigidArea(new Dimension(20, 0)));
        // agencement de la fenêtre
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        this.getContentPane().add(purposePan);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        this.getContentPane().add(userPan);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        this.getContentPane().add(buttons);
        this.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));

    }

    public String getPurpose() {
        return purpose.getText();

    }

    public String getAmount() {
        return amount.getText();
    }

    public String getUser() {
        return user.getText();
    }

    public void addButton(ActionListener action) {
        add.addActionListener(action);
    }

    public void substract(ActionListener action) {
        substract.addActionListener(action);
    }

    public void balance(ActionListener action) {
        balance.addActionListener(action);
    }

    public void choose(int index, ActionListener action) {
        choose[index].addActionListener(action);
    }
}
