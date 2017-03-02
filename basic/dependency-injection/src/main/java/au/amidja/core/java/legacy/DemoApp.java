package au.amidja.core.java.legacy;

public class DemoApp {

	private LegacyEmailService email = new LegacyEmailService();
	
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.email.sendEmail(msg, rec);
	}
	
	
	public static void main(String[] args) {
		DemoApp app = new DemoApp();
		app.processMessages("Hi Pankaj", "pankaj@abc.com");
	}
}
