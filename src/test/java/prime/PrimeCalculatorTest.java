package prime;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class PrimeCalculatorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PrimeCalculatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PrimeCalculatorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testPrimeCalculator()
    {
        assertTrue( true );        
        PrimeCalculator mCalculator = new PrimeCalculator(18);
        try {
            assertTrue(!mCalculator.call());
        } catch (Exception e) {
        //TODO: handle exception
        }
    }

    public void testPrimeCalculator1()
    {
        assertTrue( true );

        PrimeCalculator mCalculator = new PrimeCalculator(19);
        try {
            assertTrue(mCalculator.call());
        } catch (Exception e) {
        //TODO: handle exception
        }
    }

    public void testPrimeCalculator2()
    {
        assertTrue( true );

        PrimeCalculator mCalculator = new PrimeCalculator(-1);
        try {
            assertTrue(!mCalculator.call());
        } catch (Exception e) {
        //TODO: handle exception
        }
    }

    public void testPrimeCalculator3()
    {
        assertTrue( true );

        PrimeCalculator mCalculator = new PrimeCalculator(0);
        try {
            assertTrue(!mCalculator.call());
        } catch (Exception e) {
        //TODO: handle exception
        }
    }

    public void testPrimeCalculator4()
    {
        assertTrue( true );
        int numberOfPrimes=0;
        PrimeCalculator mCalculator;
        for(int i = 0; i<=1000000; i++){
        mCalculator = new PrimeCalculator(i);
            try {
                if(mCalculator.call()){
                    numberOfPrimes++;
                }
            } catch (Exception e) {
                
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   

        assertEquals(78498,numberOfPrimes);
    }
}
