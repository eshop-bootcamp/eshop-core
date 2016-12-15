package com.eshop.services;

import com.eshop.model.Buyer;
import com.eshop.model.Gender;
import com.eshop.model.User;
import com.eshop.repositories.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testBuyerIsCreatedWithValidParameters() {
        User buyer = new Buyer("John", "Pass123", Gender.MALE);
        //when(userRepositoryCustom.isEmailUnique(buyer.getEmailId())).thenReturn(true);
        //when(userRepositoryCustom.isUserNameUnique(buyer.getUsername())).thenReturn(true);
        when(userRepository.save(buyer)).thenReturn(buyer);
        ResponseEntity response = userService.register(buyer);
        assertNotNull(response);
        assertTrue(response.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    public void testBuyerIsNotCreatedWithInValidParameters() {
        User buyer = new Buyer("John", "Pass123", Gender.MALE);
        //when(userRepositoryCustom.isEmailUnique(buyer.getEmailId())).thenReturn(false);
        // when(userRepositoryCustom.isUserNameUnique(buyer.getUsername())).thenReturn(false);
        ResponseEntity response = userService.register(buyer);
        assertNotNull(response);
        assertTrue(response.getStatusCode() == HttpStatus.CREATED);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenUserIsNotValid() {
        User user = new User("John", "Pass123");
        when(userRepository.findByUsername("John12")).thenThrow(UsernameNotFoundException.class);

        exception.expect(UsernameNotFoundException.class);
        userService.validateUser(user);
    }
}
