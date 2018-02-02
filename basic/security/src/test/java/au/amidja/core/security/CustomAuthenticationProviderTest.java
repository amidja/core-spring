package au.amidja.core.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CustomAuthenticationProviderTest.class })
public class CustomAuthenticationProviderTest {

	@Autowired
	private static CustomAuthenticationProvider authProvider;
	
	@Test
	public void testCustomAuthenticationProvider() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("Please enter your username:");
			String name = in.readLine();
			System.out.println("Please enter your password:");
			String password = in.readLine();

			try {
				Authentication request = new UsernamePasswordAuthenticationToken(name, password);
				Authentication result = authProvider.authenticate(request);
				SecurityContextHolder.getContext().setAuthentication(result);
				break;

			} catch (AuthenticationException e) {
				System.out.println("Authentication failed: " + e.getMessage());
			}
		}
		System.out.println("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
	}

}
