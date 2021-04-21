package prime;
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

public class PrimeNumberCalculatorThreaded extends JFrame {


    private static final long serialVersionUID = 1L;
    private JLabel count1 = new JLabel("0");
    private JLabel statusJLabel = new JLabel("idle");
    private JButton startButton = new JButton("start");
    private JButton stopButton = new JButton("stop");
    private JLabel startNumberJLabel = new JLabel("Start Number");
    private JLabel endNumberJLabel = new JLabel("End Number");
    private JTextField startNumber = new JTextField(16);
    private JTextField endNumber = new JTextField(16);
    private SwingWorker<Boolean, Integer> worker;
    private static ExecutorService ex = Executors.newFixedThreadPool(16);

    public PrimeNumberCalculatorThreaded(String title) {
        super(title);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        add(startNumberJLabel, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        add(startNumber, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        add(endNumberJLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        add(endNumber, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.weightx = 1;
        gc.weighty = 1;
        add(count1, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        gc.weightx = 1;
        gc.weighty = 1;
        add(statusJLabel, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        gc.weightx = 1;
        gc.weighty = 1;
        add(startButton, gc);
        gc.gridx = 0;
        gc.gridy = 7;
        gc.weightx = 1;
        gc.weighty = 1;
        add(stopButton, gc);
        stopButton.setEnabled(false);

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusJLabel.setText("busy");
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                count1.setText("0");
                start();

            }

        });

        stopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                worker.cancel(true);

            }

        });
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void start() {
        int s = 0;
        int t = 0;
        try {
            s = Integer.parseInt(startNumber.getText());
            t = Integer.parseInt(endNumber.getText());
        } catch (Exception e) {
            statusJLabel.setText("idle : NumberFormatException");
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            return;
        }
        if (s >= t) {
            statusJLabel.setText("idle : start is >= end");
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            return;
        }
        final int start = s;
        final int threshold = t;

        worker = new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                int numberOfPrimes = 0;

                
                Collection<Future<Boolean>> futures = new ArrayList<Future<Boolean>>(threshold);
                for (int num = start; num <= threshold; num++) {
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
                            // TODO: handle exceptionY
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
                        count1.setText(Integer.toString(integer));
                    }
                }
            }

            @Override
            protected void done() {

                try {
                    Boolean status = get();
                    statusJLabel.setText("idle");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);

                } catch (CancellationException e) {
                    statusJLabel.setText("idle : ended with Cancellation");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    statusJLabel.setText("idle : ended with Interrupt");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    statusJLabel.setText("idle");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    e.printStackTrace();
                }

            }
        };
        worker.execute();
    }

}