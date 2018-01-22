package agh.iisg.lab;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.BooleanOptionHandler;

import java.util.HashMap;
import java.util.Optional;

public class ArgParser {
    @Option(
            name = "--latitude",
            usage = "Specify latitude, works only with '--longtitude'.",
            depends = {"--longtitude"}
    )
    private static String latitude;

    @Option(
            name = "--longtitude",
            usage = "Specify longtitude, works only with '--latitude'.",
            depends = {"--latitude"}
    )
    private static String longtitude;

    @Option(
            name = "--sensor-id",
            usage = "Specify sensor id.",
            forbids = {"--latitude", "--longtitude"}
    )
    private static String sensorId;

    @Option(
            name = "-h",
            aliases = {"--help"},
            help = true
    )
    private static boolean help;

    @Option(
            name = "--history",
            usage = "Specify time period for historical data for specified sensor/ coordinates"
    )
    private static String history;

    @Option(
            name = "--k",
            aliases = {"--api-key"},
            usage = "If api-key is not in API_KEY system variable, specify it in this option"
    )
    private static String apiKey;

    public HashMap<ArgType, Optional<String>> readArgs(String[] args) {
        HashMap<ArgType, Optional<String>> parsedArgs = new HashMap<>();
        // parse args
        CmdLineParser cmdLineParser = new CmdLineParser(this);
        try {
            cmdLineParser.parseArgument(args);
            if(help) {
                throw new CmdLineException("");
            }
            parsedArgs.put(ArgType.SensorId, Optional.ofNullable(sensorId));
            parsedArgs.put(ArgType.ApiKey, Optional.ofNullable(apiKey));
            parsedArgs.put(ArgType.Latitiude, Optional.ofNullable(latitude));
            parsedArgs.put(ArgType.Longtitiude, Optional.ofNullable(longtitude));
            parsedArgs.put(ArgType.History, Optional.ofNullable(history));
        } catch (CmdLineException e) {
            System.err.println(e.getLocalizedMessage());
            cmdLineParser.printUsage(System.err);
            System.exit(1);
        }
        return parsedArgs;
    }
}
