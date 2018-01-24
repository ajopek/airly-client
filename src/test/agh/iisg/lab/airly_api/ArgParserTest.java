package agh.iisg.lab.airly_api;

import agh.iisg.lab.ArgParser;
import agh.iisg.lab.ArgType;
import org.junit.Test;


import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/** 
* ArgParser Tester. 
* 
* @author <Artur Jopek>
* @since <pre>Jan 13, 2018</pre> 
* @version 1.0 
*/ 
public class ArgParserTest {
    private ArgParser parser = new ArgParser();

/** 
* 
* Method: readArgs(String[] args) 
* 
*/ 
@Test
public void testReadArgs() throws Exception {
    String[] args = {
            "--history",
            "2",
            "--longtitude",
            "90",
            "--latitude",
            "80"
    };
    HashMap<ArgType, Optional<String>> parsed = parser.readArgs(args);

    assertEquals(true, parsed.get(ArgType.History).isPresent());
    assertEquals( "2", parsed.get(ArgType.History).orElse(""));
    assertEquals("90", parsed.get(ArgType.Longitude).orElse(""));
    assertEquals("80", parsed.get(ArgType.Latitude).orElse(""));
} 


} 
