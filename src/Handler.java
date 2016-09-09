import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Handler {
    private File balanceFile, historyFile;
    private double balance;

    public Handler( String balanceFile, String historyFile) throws IOException {
        this.balanceFile = new File(balanceFile);
        this.historyFile = new File(historyFile);
        balance=0;
        if (balanceFile.length() >= 8) {
            try (DataInputStream balanceR = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(balanceFile)))) {
                balance = balanceR.readDouble();
            } 
        }
    }

    public boolean substract(String user, String purpose, double amount) throws IOException {
        double temp = balance - amount;
        if (temp < 0)
            return false;
        balance = temp;
        changeHistory(user, purpose, -amount, balance);
        writeBalance();
        return true;
    }

    public boolean add(String user, String purpose, double amount) throws IOException {
        balance += amount;
        changeHistory(user, purpose, amount, balance);
        writeBalance();
        return true;
    }

    private void changeHistory(String user, String purpose, double amount,
            double balance) throws IOException {
        try (BufferedWriter historyChange = new BufferedWriter(new FileWriter(
                historyFile, true))) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            historyChange.write( System.getProperty("line.separator")+ dateFormat.format(date).toString()
                    + " <---> " + user + " <---> " + purpose + " <---> "
                    +( amount>0 ? ("+"+amount):amount )+ " <---> " + balance);
        }
    }

    private void writeBalance() {
        try (DataOutputStream data = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(balanceFile)))) {
            data.writeDouble(balance);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double getBalance() {
        return balance;
    }
}
