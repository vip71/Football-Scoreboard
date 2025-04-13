package base;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class LoggerTestBase {

  protected ListAppender<ILoggingEvent> listAppender;

  public abstract Class<?> getLoggerClass();

  @BeforeMethod
  public void setup() {
    attachLogger(getLoggerClass());
  }

  protected void attachLogger(Class<?> clazz) {
    Logger logger = (Logger) LoggerFactory.getLogger(clazz);
    LoggerContext context = logger.getLoggerContext();
    listAppender = new ListAppender<>();
    listAppender.setContext(context);
    listAppender.start();
    logger.addAppender(listAppender);
  }

  @AfterMethod
  public void cleanupLogger() {
    if (listAppender != null) {
      listAppender.stop();
      listAppender = null;
    }
  }

  protected boolean logContains(String messagePart) {
    if (listAppender == null) return false;
    return listAppender.list.stream()
        .anyMatch(event -> event.getFormattedMessage().contains(messagePart));
  }
}