package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JLogFactory {
	
	private JLogFactory(){}
	
	public static void start(int jobId)
	{
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration config = ctx.getConfiguration();
		Layout layout = PatternLayout.createLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN, config, null, null, true, false, null, null);
		Appender appender = FileAppender.createAppender(String.format("logs/test/syncshow-job-%s.log", jobId), "true", "false", ""+jobId, null, "true", "true", null, layout, null, null, null, config);
		appender.start();
		
		config.addAppender(appender);
		AppenderRef ref = AppenderRef.createAppenderRef(""+jobId, null, null);
		AppenderRef[] refs=new AppenderRef[]{ref};
		LoggerConfig loggerConfig = LoggerConfig.createLogger("false",Level.ALL, ""+jobId, "true", refs, null, config, null);
		loggerConfig.addAppender(appender, null, null);
		config.addLogger(""+jobId, loggerConfig);
		ctx.updateLoggers();
	}
	
	
	public static void stop(int jobId)
	{
		final LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
		final Configuration config=ctx.getConfiguration();
		config.getAppender(""+jobId).stop();
		config.getLoggerConfig(""+jobId).removeAppender(""+jobId);
		config.removeLogger(""+jobId);
		ctx.updateLoggers();
	}
	
	public static Logger createLogger(int jobId)
	{
		start(jobId);
		return LoggerFactory.getLogger(""+jobId);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
