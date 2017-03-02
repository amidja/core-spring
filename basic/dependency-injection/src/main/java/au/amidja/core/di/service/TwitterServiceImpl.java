package au.amidja.core.di.service;

import org.springframework.stereotype.Service;

@Service("twitterService")
public class TwitterServiceImpl implements MessageService{

	@Override
	public void sendMessage(String msg, String rec) {
		System.out.println("Twitter message Sent to " + rec + " with Message=" + msg);
	
	}

}
