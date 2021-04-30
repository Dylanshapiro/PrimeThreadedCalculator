package prime;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PrimeCalcView extends JFrame implements PrimeCalcViewInterface{


    private static final long serialVersionUID = 1L;
    private JLabel count1 = new JLabel("0");
    private JLabel statusJLabel = new JLabel("idle");
    private JButton startButton = new JButton("start");
    private JButton stopButton = new JButton("stop");
    private JLabel startNumberJLabel = new JLabel("Start Number");
    private JLabel endNumberJLabel = new JLabel("End Number");
    private JTextField startNumber = new JTextField(16);
    private JTextField endNumber = new JTextField(16);
    private PrimeCalcSwingWorker worker;


    public PrimeCalcView(String title) {
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
                count1.setText("0");
                if(!checkStartAndEnd(getStartingNumber(), getEndingNumber())){
                    setLabelText(SwingJLabels.STATUSJLABEL,"idle : invalid input");
                    setButtonEnabled(SwingJButtons.STARTBUTTON, true);
                    setButtonEnabled(SwingJButtons.STOPBUTTON, false);
                    return;
                }

                statusJLabel.setText("busy");
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
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
        worker = new PrimeCalcSwingWorker(this);
           
        worker.execute();
    }


    public void setLabelText(SwingJLabels label, String text){
        switch (label) {
            case COUNT1:
                count1.setText(text);
                break;
            case STATUSJLABEL:
                statusJLabel.setText(text);
                break;
            default:
                break;
        }
    }


    public void setButtonEnabled(SwingJButtons button, Boolean enabled){
        switch (button){
            case STARTBUTTON:
                startButton.setEnabled(enabled);
                break;
            case STOPBUTTON:
                stopButton.setEnabled(enabled);
                break;
            default:
                break;
        }
    }

    public int getStartingNumber(){
        int end =-1;
        try {
            end =Integer.parseInt(startNumber.getText());
        }
        catch (Exception e ){
        }
        return end;
    }
    
    public int getEndingNumber(){
        int end =-1;
        try {
            end =Integer.parseInt(endNumber.getText());
        }
        catch (Exception e ){
        }
        return end;
    }


    public JLabel getCount1() {
        return this.count1;
    }

    public void setCount1(JLabel count1) {
        this.count1 = count1;
    }

    public JLabel getStatusJLabel() {
        return this.statusJLabel;
    }

    public void setStatusJLabel(JLabel statusJLabel) {
        this.statusJLabel = statusJLabel;
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getStopButton() {
        return this.stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JLabel getStartNumberJLabel() {
        return this.startNumberJLabel;
    }

    public void setStartNumberJLabel(JLabel startNumberJLabel) {
        this.startNumberJLabel = startNumberJLabel;
    }

    public JLabel getEndNumberJLabel() {
        return this.endNumberJLabel;
    }

    public void setEndNumberJLabel(JLabel endNumberJLabel) {
        this.endNumberJLabel = endNumberJLabel;
    }

    public JTextField getStartNumber() {
        return this.startNumber;
    }

    public void setStartNumber(JTextField startNumber) {
        this.startNumber = startNumber;
    }

    public JTextField getEndNumber() {
        return this.endNumber;
    }

    public void setEndNumber(JTextField endNumber) {
        this.endNumber = endNumber;
    }

    public PrimeCalcSwingWorker getWorker() {
        return this.worker;
    }

    public void setWorker(PrimeCalcSwingWorker worker) {
        this.worker = worker;
    }

    public Boolean checkStartAndEnd(int start,int end){
        if (start >= end || start <0 || end <0) {
            return false;
        }
        return true;
    }


}
