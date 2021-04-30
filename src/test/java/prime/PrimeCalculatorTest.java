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
     
            assertTrue(!PrimeCalculator.isPrime(18));
        
    }

    public void testPrimeCalculator1()
    {
        assertTrue( true );

        
            assertTrue(PrimeCalculator.isPrime(19));
        
    }

    public void testPrimeCalculator2()
    {
        assertTrue( true );


            assertTrue( !PrimeCalculator.isPrime(-1));
 
    }

    public void testPrimeCalculator3()
    {
        assertTrue( true );


            assertTrue(! PrimeCalculator.isPrime(0));

    }

    public void testPrimeCalculator4()
    {
        assertTrue( true );
        int numberOfPrimes=0;
        for(int i = 0; i<=1000000; i++){
 
                if(PrimeCalculator.isPrime(i)){
                    numberOfPrimes++;
                }
          
        }   

        assertEquals(78498,numberOfPrimes);
    }
}
