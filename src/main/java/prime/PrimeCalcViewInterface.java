package prime;
/**
 * PrimeCalcViewInterface
 */

enum SwingJLabels{
    COUNT1,STATUSJLABEL
}
enum SwingJButtons{
    STARTBUTTON,STOPBUTTON
}

public interface PrimeCalcViewInterface {


    public void setLabelText(SwingJLabels label, String text);

    public void setButtonEnabled(SwingJButtons button, Boolean enabled);
    
    public int getStartingNumber();
    
    public int getEndingNumber();
}