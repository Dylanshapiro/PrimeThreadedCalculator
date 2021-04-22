package prime;

import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PimeCalcViewTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    PrimeCalcView mCalculator;
    public void setUp(  ){
        this.mCalculator = new PrimeCalcView("test");

    }

    public void tearDown(  ){
        
    }


    public PimeCalcViewTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    
    public static Test suite()
    {
        return new TestSuite( PimeCalcViewTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void testPrimeCalcView2()
    {
        assertTrue( true );
        testSetStartNumber("0");
        testSetEndNumber("1000000");
        assertStatusEquals("idle");
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        mCalculator.getStartButton().doClick();
        while (!mCalculator.getWorker().isDone()) {
            assertButtonEnabled(SwingJButtons.STOPBUTTON);
            assertButtonDisabled(SwingJButtons.STARTBUTTON);
            assertStatusEquals("busy");
        }
        sleep(100);
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        assertStatusEquals("idle");
        assertEquals("78498", mCalculator.getCount1().getText());
    }


    public void testPrimeCalcView(){
        assertTrue( true );
        testSetStartNumber("0");
        testSetEndNumber("1000000");
        assertStatusEquals("idle");
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        mCalculator.getStartButton().doClick();
        assertStatusEquals("busy");
        sleep(300);
        mCalculator.getStopButton().doClick();
        sleep(100);
        assertStatusEquals("idle : Cancellation");
        int result= Integer.parseInt(mCalculator.getCount1().getText());
        assertTrue("Count is not greater than 0",0<result && result != 78498);
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);

    }

    public void testPrimeCalcView3(){
        assertTrue( true );
        testSetStartNumber("1000");
        testSetEndNumber("100");
        assertStatusEquals("idle");
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        mCalculator.getStartButton().doClick();
        assertStatusEquals("idle : invalid input");
        int result= Integer.parseInt(mCalculator.getCount1().getText());
        assertTrue("Count is not equal to 0",0== result);
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
    }


    public void testPrimeCalcView4(){
        assertTrue( true );
        testSetStartNumber("10000000000000000");
        testSetEndNumber("1000000000000000000");
        assertStatusEquals("idle");
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        mCalculator.getStartButton().doClick();
        assertStatusEquals("idle : invalid input");
        int result= Integer.parseInt(mCalculator.getCount1().getText());
        assertTrue("Count is not equal to 0",0== result);
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
    }

    public void testPrimeCalcView5(){
        assertTrue( true );
        testSetStartNumber("0");
        testSetEndNumber("0");
        assertStatusEquals("idle");
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        mCalculator.getStartButton().doClick();
        assertStatusEquals("idle : invalid input");
        int result= Integer.parseInt(mCalculator.getCount1().getText());
        assertTrue("Count is not equal to 0",0== result);
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
    }


    public void testPrimeCalcView6()
    {
        assertTrue( true );
        testSetStartNumber("100");
        testSetEndNumber("110");
        assertStatusEquals("idle");
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        mCalculator.getStartButton().doClick();
        while (!mCalculator.getWorker().isDone()) {
            assertButtonEnabled(SwingJButtons.STOPBUTTON);
            assertButtonDisabled(SwingJButtons.STARTBUTTON);
            assertStatusEquals("busy");
        }
        sleep(100);
        assertButtonEnabled(SwingJButtons.STARTBUTTON);
        assertButtonDisabled(SwingJButtons.STOPBUTTON);
        assertStatusEquals("idle");
        assertEquals("4", mCalculator.getCount1().getText());
    }
    

    public void assertButtonEnabled(SwingJButtons button){
        switch (button) {
            case STARTBUTTON:
                assertTrue("start button is not enabled", mCalculator.getStartButton().isEnabled());
                break;
            case STOPBUTTON:
                assertTrue("stop button is not enabled", mCalculator.getStopButton().isEnabled());
                break;
            default:
                break;
        }
    }

    public void assertButtonDisabled(SwingJButtons button){
        switch (button) {
            case STARTBUTTON:
                assertTrue("start button is not disabled", !mCalculator.getStartButton().isEnabled());
                break;
            case STOPBUTTON:
                assertTrue("stop button is not disabled", !mCalculator.getStopButton().isEnabled());
                break;
            default:
                break;
        }
    }

    public void sleep(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void assertStatusEquals(String _status){
        assertEquals(_status,mCalculator.getStatusJLabel().getText());
    }

    public void testSetEndNumber(String _endNumber){
        mCalculator.setEndNumber(new JTextField(_endNumber));
        assertEquals(_endNumber,mCalculator.getEndNumber().getText());
    }

    public void testSetStartNumber(String _startNumber){
        mCalculator.setStartNumber(new JTextField(_startNumber));
        assertEquals(_startNumber,mCalculator.getStartNumber().getText());
    }
}
