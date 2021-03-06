package com.revature.serviceTests;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.rideforce.user.UserApplication;
import com.revature.rideforce.user.beans.User;
import com.revature.rideforce.user.beans.UserCredentials;
import com.revature.rideforce.user.beans.UserRegistrationInfo;
import com.revature.rideforce.user.exceptions.EntityConflictException;
import com.revature.rideforce.user.exceptions.InvalidCredentialsException;
import com.revature.rideforce.user.exceptions.InvalidRegistrationKeyException;
import com.revature.rideforce.user.exceptions.PermissionDeniedException;
import com.revature.rideforce.user.security.RegistrationTokenProvider;
import com.revature.rideforce.user.services.AuthenticationService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Transactional
public class AuthenticationServiceTest {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private RegistrationTokenProvider tokenProvider;
	
	
	
	@Before
	public void validate() {
		Assertions.assertThat(authenticationService).isNotNull();
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void invalidCredentialsThrowsException() throws InvalidCredentialsException {
		UserCredentials userCred = new UserCredentials();
		userCred.setEmail("bobby@gmail.com");
		authenticationService.authenticate(new UserCredentials());
	}
	
	@Test(expected = InvalidRegistrationKeyException.class)
	public void registerWithInvalidRegistrationInfoThrowsException() throws InvalidRegistrationKeyException, EntityConflictException, PermissionDeniedException {
		authenticationService.register(null);
	}
	
	@Test
	public void noUserInContext_WillReturnNull() {
		Assertions.assertThat(authenticationService.getCurrentUser()).isNull();
	}
	
	@Test
	public void userInContext_WilReturnThatUser() {
		
	}
	
	
	
}
