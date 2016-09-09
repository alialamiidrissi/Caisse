package graphics;


import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BannerLayer extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JLabel jlabel;
    private JTextField textBox;
    private JButton button;
    public BannerLayer(String label,String buttonName) {
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        this.jlabel = new JLabel(label);
        jlabel.setPreferredSize(new Dimension(200, 50));
        this.textBox = new JTextField();
        textBox.setPreferredSize(new Dimension(350, 70));
        this.button = new JButton(buttonName);
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(jlabel);
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(textBox);
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(button);
        this.add(Box.createRigidArea(new Dimension(20, 0)));
    }
    public void addButtonActionListener(ActionListener action)
    {
        button.addActionListener(action);
    }
    public String getPath()
    {
        return textBox.getText();
    }
    
    public void setPath(String path)
    {
        textBox.setText(path);
    }
    
}