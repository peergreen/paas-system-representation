package org.ow2.jonas.jpaas.sr.init;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Listeners(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
public class SetupTest {

    public static final String PROJECT_VERSION = "1.0.0-M1-SNAPSHOT";


    @Configuration
    public Option[] config() {
        // Reduce log level.
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        return options(systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("WARN"),
                systemProperty("pax.exam.invoker").value("singleton"),
                mavenBundle("org.testng", "testng", "6.3.1"),
                mavenBundle("com.peergreen.pax-exam", "pax-exam-invoker-singleinstance").version(PROJECT_VERSION),
                mavenBundle("com.peergreen.paas", "paas-system-representation-vo").version(PROJECT_VERSION),
                mavenBundle("com.peergreen.paas", "paas-system-representation-api").version(PROJECT_VERSION),
                mavenBundle("com.peergreen.paas", "paas-system-representation-ejb").version(PROJECT_VERSION),
                systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("WARN")
        );
    }


    @BeforeSuite
    public void emptySuiteTest() {

    }

    @Test
    public void emptyTest() {

    }



}
