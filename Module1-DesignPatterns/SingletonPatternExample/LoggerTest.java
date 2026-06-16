package SingletonPatternExample;

public class LoggerTest {

    public static void main(String[] args) {

        System.out.println("=== Singleton Pattern Test ===\n");


        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();


        logger1.info("Application started");
        logger2.warn("Low memory warning");


        System.out.println("\n--- Instance Verification ---");
        System.out.println("logger1 hashCode : " + logger1.hashCode());
        System.out.println("logger2 hashCode : " + logger2.hashCode());
        System.out.println("Same instance?   : " + (logger1 == logger2));


        System.out.println("\n--- AppService using Logger ---");
        new AppService().doWork();


        Logger logger3 = Logger.getInstance();
        System.out.println("\nlogger3 same as logger1? " + (logger1 == logger3));
    }
}
