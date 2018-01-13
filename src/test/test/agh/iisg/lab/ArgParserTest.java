package test.agh.iisg.lab;

import agh.iisg.lab.ArgParser;
import agh.iisg.lab.ArgType;
import com.sun.org.apache.xpath.internal.Arg;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

/** 
* ArgParser Tester. 
* 
* @author <Artur Jopek>
* @since <pre>Jan 13, 2018</pre> 
* @version 1.0 
*/ 
public class ArgParserTest {
    ArgParser parser = new ArgParser();;

/** 
* 
* Method: readArgs(String[] args) 
* 
*/ 
@Test
public void testReadArgs() throws Exception {
    String[] args = {
            "--history",
            "--longtitude",
            "90",
            "--latitude",
            "80"
    };
    HashMap<ArgType, Optional<String>> parsed = parser.readArgs(args);

    assertEquals(true, parsed.get(ArgType.History).isPresent());
    assertEquals( "true", parsed.get(ArgType.History).orElse("false"));
    assertEquals("90", parsed.get(ArgType.Longtitiude).orElse(""));
    assertEquals("80", parsed.get(ArgType.Latitiude).orElse(""));
} 


} 
