package de.rwth.idsg.steve;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 14.01.2015
 */
@Slf4j
public class Application implements ApplicationStarter, AutoCloseable {

    private final ApplicationStarter delegate;

    public Application() {
        // For Hibernate validator
        System.setProperty("org.jboss.logging.provider", "slf4j");

        SteveConfiguration sc = SteveConfiguration.CONFIG;
        log.info("Loaded the properties. Starting with the '{}' profile", sc.getProfile());

        TimeZone.setDefault(TimeZone.getTimeZone(sc.getTimeZoneId()));
        DateTimeZone.setDefault(DateTimeZone.forID(sc.getTimeZoneId()));
        log.info("Date/time zone of the application is set to {}. Current date/time: {}", sc.getTimeZoneId(), DateTime.now());

        switch (sc.getProfile()) {
            case DEV:
                delegate = new SteveDevStarter();
                break;
            case TEST:
            case PROD:
                delegate = new SteveProdStarter();
                break;
            default:
                throw new RuntimeException("Unexpected profile");
        }
    }

    public static void main(String[] args) throws Exception {
        Application app = null;
        try {
            app = new Application();
            app.start();
            app.join();
        } catch (Exception e) {
            if (app != null) {
                app.stop();
            }
        }
    }

    @Override
    public void start() throws Exception {
        delegate.start();
    }

    @Override
    public void join() throws Exception {
        delegate.join();
    }

    @Override
    public void stop() throws Exception {
        delegate.stop();
    }

    @Override
    public void close() throws Exception {
        stop();
    }
}
