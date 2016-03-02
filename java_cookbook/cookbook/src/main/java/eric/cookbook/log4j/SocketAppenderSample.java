package eric.cookbook.log4j;

import org.apache.log4j.Logger;

/**
 * Created by Eric on 2015/12/14.
 */
public class SocketAppenderSample {
    private static Logger logger=Logger.getLogger(SocketAppenderSample.class);
    public static void main(String[] args) throws Exception {
        for(int i=0;i<1;i++){
            logger.info("LOG4j Socket ouput:"+i);
        }

    }

}
