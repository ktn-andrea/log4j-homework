package pkg;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.filter.TimeFilter;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Marker STATE_MARKER = MarkerManager.getMarker("STATE_MARKER");
    private static final Marker REQUIRE_MARKER = MarkerManager.getMarker("REQUIRE_MARKER");

    public static void main(String[] args) throws InterruptedException {

        Integer it = 1;
        if (args.length > 0)
        {
            try {
                it = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e){};
        }

        TimeFilter kijarasiTilalom = TimeFilter.createFilter("20:00:00", "05:00:00", "Europe/Budapest", Filter.Result.ACCEPT, Filter.Result.DENY);
        kijarasiTilalom.start();

        Integer j = 1;
        while(j <= it){
            ThreadContext.put("iterator", j.toString());
            logger.info(STATE_MARKER, "E szép házba nyitottam,");
            logger.debug(STATE_MARKER,"Nefelejcset találtam,");
            logger.warn("Nem hagyhatom hervadni,");
            logger.error(REQUIRE_MARKER, "Meg szabad-e locsolni?");

            Thread.sleep(1000);
            j++;
        }

        ThreadContext.clearMap();
        kijarasiTilalom.stop();

    }

}
