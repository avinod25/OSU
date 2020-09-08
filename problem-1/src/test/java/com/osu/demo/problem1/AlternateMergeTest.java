package com.osu.demo.problem1;

import java.util.logging.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple AlternateMerge.
 */
public class AlternateMergeTest 
    extends TestCase
{
	private final static Logger LOGGER = Logger.getLogger(AlternateMergeTest.class.getName());
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AlternateMergeTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AlternateMergeTest.class );
    }

    /**
     * Rigourous Test1
     */
    public void test1()
    {
    	String string1="lobster";
		String string2="like";

		LOGGER.info("string 1:  "+string1);
		LOGGER.info("string 2:  "+string2);
    	String output=AlternateMerge.alternateMergeStrings(string1, string2);
    	LOGGER.info("Output:  "+output);
        assertTrue( "lloibkseter".equals(output) );
    }
    
    /**
     * Rigourous Test2
     */
    public void test2()
    {    		
		String string1="hello";
		String string2="lobster";		
		
		LOGGER.info("string 1:  "+string1);
		LOGGER.info("string 2:  "+string2);
    	String output=AlternateMerge.alternateMergeStrings(string1, string2);
    	LOGGER.info("Output:  "+output);
        assertTrue( "lhoeblsltoer".equals(output) );
    }
    
    /**
     * Rigourous Test3
     */
    public void test3()
    {    		
		String string1="gourmet";
		String string2="lobster";		
		
		LOGGER.info("string 1:  "+string1);
		LOGGER.info("string 2:  "+string2);
    	String output=AlternateMerge.alternateMergeStrings(string1, string2);
    	LOGGER.info("Output:  "+output);
        assertTrue( "glooubrsmteetr".equals(output) );
    }
}
