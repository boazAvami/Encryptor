package utilities;
import org.apache.log4j.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import org.apache.log4j.PropertyConfigurator;


public class EncryptionLogger {
    static Logger logger = Logger.getLogger(EncryptionLogger.class.getName());

    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    public String buildLog(String message, Throwable t) {
        String selfIP = null;

        try {
            selfIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            selfIP = "Error getting self IP";
        }

        Date timestamp = new Date();
        String appName = "Boaz Encryptor";
        String fileName = Thread.currentThread().getStackTrace()[2].getFileName();

        String traceLevel = null;
        if (t != null) {
            traceLevel = t.getClass().getSimpleName();
        }

        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append("[");
        logMessageBuilder.append(selfIP);
        logMessageBuilder.append("] [");
        logMessageBuilder.append(timestamp);
        logMessageBuilder.append("] [");
        logMessageBuilder.append(appName);
        logMessageBuilder.append("] [");
        logMessageBuilder.append(fileName);
        logMessageBuilder.append("] ");

        if (traceLevel != null) {
            logMessageBuilder.append("[");
            logMessageBuilder.append(traceLevel);
            logMessageBuilder.append("] ");
        }

        logMessageBuilder.append(message);

        return logMessageBuilder.toString();
    }

    public void error(String message, Throwable throwable) {
        logger.error(buildLog(message, throwable), throwable);
    }

    public void info(String message) {
        logger.info(buildLog(message, null));
    }

    public void debug(String message) {
        logger.debug(buildLog(message, null));
    }
}
