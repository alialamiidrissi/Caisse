import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class DatCreator {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try (DataOutputStream balanceR = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("/home/aliostux/Bureau/setup/BalanceFile.dat")))) {
            balanceR.writeDouble(0.0);
        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("stop");
    }

}
