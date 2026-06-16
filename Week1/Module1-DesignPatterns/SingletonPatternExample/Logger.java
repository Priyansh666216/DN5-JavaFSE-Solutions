package SingletonPatternExample;

public class Logger {
    private static volatile Logger instance = null;

    private Logger() {
        System.out.println("Logger instance created.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }


    public void log(String level, String message) {
        System.out.printf("[%s] %s%n", level.toUpperCase(), message);
    }

    public void info(String message)  { log("INFO",    message); }
    public void warn(String message)  { log("WARNING", message); }
    public void error(String message) { log("ERROR",   message); }
}
