package com.ap.game.chess.authentication;

import com.ap.game.chess.user.User;
import com.ap.game.chess.user.UserController;
import com.ap.game.chess.user.UserRequestBody;
import com.ap.game.chess.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    private MockMvc mockMvc;
    private UserRequestBody userRequestBody;
    private String userEmail;
    private String userRegisterRequestJson;

    @InjectMocks
    AuthController authController;

    @Mock
    private UserService userService;

    @Mock
    User mockUser;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        userEmail = "email@domain.com";
        userRequestBody = UserRequestBody.builder()
                            .email(userEmail)
                            .firstName(null)
                            .lastName("Boot")
                            .password("password")
                            .build();
        userRegisterRequestJson = new ObjectMapper().writeValueAsString(userRequestBody);
    }

/*    @Test
    @Ignore
    public void registerUser_callsUserService_test() throws Exception {
        //when(userService.create(userRequestBody)).thenReturn(mockUser);
        //Verify by catching user response entity and verifying the user object
        mockMvc.perform(
                post("/auth/register")
                        .contentType("application/json")
                        .content(userRegisterRequestJson))
                        //.andExpect(user);
        verify(userService).create(userRequestBody);
    }*/

    @Test
    public void registerUser_test() throws Exception {
        //when(userService.create(userRequestBody)).thenReturn(mockUser);
        mockMvc.perform(
                post("/auth/register")
                    .contentType("application/json")
                    .content(userRegisterRequestJson))
                    .andExpect(status().isCreated());
    }

/*    @Test
    @Ignore
    public void authenticateUser() {
    }*/
}