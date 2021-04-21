package prime;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker;

/**
 * PrimeCalcSwingWorker
 */
public class PrimeCalcSwingWorker extends SwingWorker<Boolean, Integer> {
    private ExecutorService ex;
    private PrimeCalcViewInterface gui;

    PrimeCalcSwingWorker(ExecutorService ex, PrimeCalcViewInterface gui){
        this.ex = ex;
        this.gui= gui;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        int numberOfPrimes = 0;



        final int end = getEndingNumber();
        final int start = getStartingNumber();

        if (!checkStartAndEnd(start, end)){
            return false;
        }

        Collection<Future<Boolean>> futures = new ArrayList<Future<Boolean>>(end-start+1);
        for (int num = start; num <= end; num++) {
            if (!isCancelled()) {
                futures.add(ex.submit(new PrimeCalculator(num)));
            }
            else {
                return false;
            }
        }

        for (Future<Boolean> future : futures) {
            if (!isCancelled()) {
                try {
                    if (future.get()) {
                        publish(++numberOfPrimes);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else {
                return false;
            }
        }

        return true;
        }
    



    @Override
    protected void process(List<Integer> chunks) {
        if (!isCancelled()) {
            for (Integer integer : chunks) {
                gui.setLabelText(SwingJLabels.COUNT1,Integer.toString(integer));
            }
        }
    }

    @Override
    protected void done() {

        try {
            Boolean status = get();
            if(status){
                gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
                gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
                gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle");    
            }
            

        } catch (CancellationException e) {
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle");
            e.printStackTrace();
        } catch (InterruptedException e) {
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle");
            e.printStackTrace();
        } catch (ExecutionException e) {
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle");
            e.printStackTrace();
        }

    }

    public int getEndingNumber(){
        int end =-1;
        try {

            end =gui.getEndingNumber();
        }
        catch (Exception e ){        
        }
        return end;
    }

    public int getStartingNumber(){
        int end =-1;
        try {

            end =gui.getStartingNumber();
        }
        catch (Exception e ){
        }
        return end;
    }

    public Boolean checkStartAndEnd(int start,int end){
        if (start >= end || start <0 || end <0) {
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle : invalid input");
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            return false;
        }
        return true;
    }
}