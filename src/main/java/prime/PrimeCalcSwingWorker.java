package prime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;


import javax.swing.SwingWorker;

/**
 * PrimeCalcSwingWorker
 */
public class PrimeCalcSwingWorker extends SwingWorker<Boolean, Integer> {
    AtomicInteger numberOfPrimes = new AtomicInteger();
    private PrimeCalcViewInterface gui;

    PrimeCalcSwingWorker( PrimeCalcViewInterface gui){
        this.gui= gui;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        final int end = getEndingNumber();
        final int start = getStartingNumber();
        publish((int)IntStream.range(start,end+1).parallel().filter(argument -> isPrime(argument) == true).count());
        return true;
        }
    

        private Boolean isPrime(int n){
            if(!isCancelled()){
                if(PrimeCalculator.isPrime(n)){
                    publish(numberOfPrimes.incrementAndGet());
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

}