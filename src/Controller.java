import graphics.SetupWindow;
import graphics.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Controller {
    private SetupWindow setup;
    private View view;
    private Handler handler;

    public Controller() {
        setup = new SetupWindow();
        view = new View();
        view.setVisible(false);
        setBlankSetupFields();
        okButton();
        choice();
        showBalance();
        addButton();
        substract();

    }

    private void setBlankSetupFields() {
        try (InputStream in = new FileInputStream(ClassLoader
                .getSystemClassLoader().getResource(".").getPath()
                .replace("/Caisse/bin/", "")
                + "/location.txt");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in))) {
            String line = null;
            setup.setHistoryPath((line = reader.readLine()) != null ? line : "");
            setup.setBalancePath((line = reader.readLine()) != null ? line : "");
        } catch (IOException e1) {
            IOerrorMessage(e1);
        }
    }

    private void addButton() {
        view.addButton(e -> {
            String purpose = view.getPurpose();
            String user = view.getUser();
            String amount = view.getAmount();
            checkValidity(purpose, user, amount);
            try {
                handler.add(user, purpose, Double.parseDouble(amount));
            } catch (IOException e1) {
                IOerrorMessage(e1);
            }
        });
    }

    private void choice() {
        setup.ChoiceButton(0, e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                setup.setHistoryPath(chooser.getSelectedFile().getPath());
            }

        });
        setup.ChoiceButton(1, e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal2 = chooser.showOpenDialog(chooser);
            if (returnVal2 == JFileChooser.APPROVE_OPTION) {
                setup.setBalancePath(chooser.getSelectedFile().getPath());
            }
        });
    }

    private void okButton() {
        setup.okButton(e -> {
            String historyPath = setup.getHistoryPath();
            String balancePath = setup.getBalancePath();
            try {
                writePath(historyPath, balancePath);
                setup.dispose();
                handler = new Handler(balancePath, historyPath);
                view.setVisible(true);
            } catch (IOException e1) {
                IOerrorMessage(e1);
            }

        });
    }

    private void showBalance() {
        view.balance(e -> {
            JOptionPane.showMessageDialog(null, "Le solde actuel est : "
                    + handler.getBalance() + " dhs", "Solde Actuel",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void substract() {
        view.substract(e -> {
            String purpose = view.getPurpose();
            String user = view.getUser();
            String amount = view.getAmount();
            checkValidity(purpose, user, amount);
            try {
                if (!handler.substract(user, purpose,
                        Double.parseDouble(amount)))
                    JOptionPane.showMessageDialog(null, "Solde Insuffisant",
                            "avertissement", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e1) {
                IOerrorMessage(e1);
            }
        });
    }

    private void writePath(String path1, String path2) throws IOException {
        String path = (ClassLoader.getSystemClassLoader().getResource(".")
                .getPath().replace("/Caisse/bin/", "") + "/location.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(path1 + System.getProperty("line.separator") + path2);
        }
    }

    private void checkValidity(String purpose, String user, String amount) {
        if (purpose.isEmpty() || user.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "veuillez remplir tout les champs", "error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void IOerrorMessage(IOException e1) {
        e1.printStackTrace();
        JOptionPane.showMessageDialog(null, "erreur d'entrée/sortie", "erreur",
                JOptionPane.ERROR_MESSAGE);
    }
}
