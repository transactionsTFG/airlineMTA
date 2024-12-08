package integration.environment;

import java.util.Arrays;

public abstract class PersistenceConfig {
	private static PersistenceConfig PERSISTANCE;
	protected PersistenceConfig() {}

	public static synchronized  PersistenceConfig getInstance() {
		if (PERSISTANCE == null) {
			boolean isRunningJunit = Arrays.stream(Thread.currentThread().getStackTrace())
		            .map(StackTraceElement::getClassName)
		            	.anyMatch(className -> className.startsWith("org.junit."));
			if (isRunningJunit) 
				PERSISTANCE = new JUnitEnv();
			else 
				PERSISTANCE = new GlassFishEnv();
		}
		
		return PERSISTANCE;
	
	}
	
	public abstract boolean isRunningJUnit();
	public abstract String getNamePersistance();
}
