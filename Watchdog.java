import java.util.TimerTask;

public class Watchdog extends TimerTask {
    Thread watched;

    public Watchdog(Thread t) {
        watched = t;
    }

    public void run() {
        watched.stop();
    }
}
