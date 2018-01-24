package agh.iisg.lab;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.ExplicitBooleanOptionHandler;

import java.util.HashMap;
import java.util.Optional;

public class ArgParser {
    @Option(
            name = "--latitude",
            usage = "Specify latitude, works only with '--longitude'.",
            depends = {"--longitude"}
    )
    private static String latitude;

    @Option(
            name = "--longitude",
            usage = "Specify longitude, works only with '--latitude'.",
            depends = {"--latitude"}
    )
    private static String longitude;

    @Option(
            name = "--sensor-id",
            usage = "Specify sensor id.",
            forbids = {"--latitude", "--longitude"}
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
            usage = "Show also historical data for given sensor/map point",
            handler = ExplicitBooleanOptionHandler.class
    )
    private static boolean history;

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
            parsedArgs.put(ArgType.Latitude, Optional.ofNullable(latitude));
            parsedArgs.put(ArgType.Longitude, Optional.ofNullable(longitude));
            if (history) parsedArgs.put(ArgType.History, Optional.ofNullable("true"));
        } catch (CmdLineException e) {
            System.err.println(e.getLocalizedMessage());
            cmdLineParser.printUsage(System.err);
            System.exit(1);
        }
        return parsedArgs;
    }
}
