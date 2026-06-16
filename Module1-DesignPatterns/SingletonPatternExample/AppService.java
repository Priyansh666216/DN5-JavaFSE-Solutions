package SingletonPatternExample;

public class AppService {

    public void doWork() {
        Logger logger = Logger.getInstance();
        logger.info("AppService is doing some work...");
    }
}
