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

        PrimeCalculator mCalculator = new PrimeCalculator(19);
        try {
            assertTrue(mCalculator.call());
        } catch (Exception e) {
        //TODO: handle exception
        }
        
        mCalculator = new PrimeCalculator(18);
        try {
            assertTrue(!mCalculator.call());
        } catch (Exception e) {
        //TODO: handle exception
        }
    }
}
