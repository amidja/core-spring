package au.amidja.core.di.consumer;

public interface Consumer {
	
	void processMessage(String msg, String rec);
}
