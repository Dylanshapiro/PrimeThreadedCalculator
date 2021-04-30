package prime;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import java.util.stream.IntStream;
import javax.swing.SwingWorker;

/**
 * PrimeCalcSwingWorker
 */
public class PrimeCalcSwingWorker extends SwingWorker<Boolean, Integer> {
    private int numberOfPrimes = 0;
    private PrimeCalcViewInterface gui;

    PrimeCalcSwingWorker( PrimeCalcViewInterface gui){
        this.gui= gui;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        numberOfPrimes = 0;
        final int end = getEndingNumber();
        final int start = getStartingNumber();
        publish((int)IntStream.range(start,end+1).parallel().filter(argument -> isPrime(argument) == true).count());
        return true;
    }
    

    private Boolean isPrime(int n){
        if(!isCancelled()){
            if(PrimeCalculator.isPrime(n)){
                publish(numberOfPrimes++);
                return true;
            }
        }
        return false;
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
            get();
                gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
                gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
                gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle");             

        } catch (CancellationException e) {
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle : Cancellation");
            e.printStackTrace();
        } catch (InterruptedException e) {
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle : Interrupted");
            e.printStackTrace();
        } catch (ExecutionException e) {
            gui.setButtonEnabled(SwingJButtons.STARTBUTTON, true);
            gui.setButtonEnabled(SwingJButtons.STOPBUTTON, false);
            gui.setLabelText(SwingJLabels.STATUSJLABEL,"idle : Exception");
            e.printStackTrace();
        }

    }

    public int getEndingNumber(){
        return gui.getEndingNumber();
    }

    public int getStartingNumber(){
        return gui.getStartingNumber();
    }

}