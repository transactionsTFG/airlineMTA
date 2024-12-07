package integration.environment;

import java.util.Arrays;

public class PersistanceConfigFile {
	private final String nameFile;
	public PersistanceConfigFile() {
		boolean isRunningJunit = Arrays.stream(Thread.currentThread().getStackTrace())
								            .map(StackTraceElement::getClassName)
								            	.anyMatch(className -> className.startsWith("org.junit."));
		this.nameFile = isRunningJunit ? "MtaAirlineTest" : "MtaAirline";
	}
	
	public String getNameFile() {
		return this.nameFile;
	}
}
