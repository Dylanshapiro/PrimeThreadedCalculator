package prime;
import javax.swing.SwingUtilities;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PrimeCalcView("Threaded");

            }

        });
    }
}
