package au.amidja.core.java.legacy;

public class DemoApp {

	private LegacyEmailService email = new LegacyEmailService();
	
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.email.sendEmail(msg, rec);
	}
	
	/*Problems with this approach: 
	 * 	DemoApp is responsible for initializing the email service -> hard coded dependency. 
	 *
	 *Pure Java Solution requires:
	 *	Service components designed with a base class or interface.
	 *	Consumer class written in terms of service interface.
	 *	Injector classes that will initialize the service and then the consumer classes.  
	 */
	public static void main(String[] args) {
		DemoApp app = new DemoApp();
		app.processMessages("Hi Pankaj", "pankaj@abc.com");
	}
}
