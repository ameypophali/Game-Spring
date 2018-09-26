package com.ap.game.chess.authentication;

import com.ap.game.chess.security.JwtTokenProvider;
import com.ap.game.chess.user.User;
import com.ap.game.chess.user.UserRequestBody;
import com.ap.game.chess.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider tokenProvider;

    private MockMvc mockMvc;
    private UserRequestBody userRequestBody;
    private String userRegisterRequestJson;
    private final String email = "email";
    private final String password = "password";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        userRequestBody = UserRequestBody.builder()
                .email(email)
                .firstName("firstName")
                .lastName("lastName")
                .password(password)
                .build();
        userRegisterRequestJson = new ObjectMapper().writeValueAsString(userRequestBody);
    }

    @Test
    public void registerUser_callsUserService_test() throws Exception {
        User user = new User();
        ArgumentCaptor<UserRequestBody> userRequestBodyArgumentCaptor =
                ArgumentCaptor.forClass(UserRequestBody.class);

        when(userService.create(Mockito.any())).thenReturn(user);
        mockMvc.perform(post("/auth/register")
                .contentType("application/json")
                .content(userRegisterRequestJson))
                .andExpect(status().isCreated())
                .andReturn();

        verify(userService, times(1))
                .create(userRequestBodyArgumentCaptor.capture());
        Assert.assertThat(userRequestBody,
                Matchers.equalTo(userRequestBodyArgumentCaptor.getValue()));
    }

    @Test
    public void registerUser_test() throws Exception {
        User expectedUser = User.builder()
                .email("email")
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .build();

        when(userService.create(Mockito.any())).thenReturn(expectedUser);
        MvcResult result = mockMvc.perform(
                post("/auth/register")
                        .contentType("application/json")
                        .content(userRegisterRequestJson))
                .andExpect(status().isCreated())
                .andReturn();
        User actualUser = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), User.class);

        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void authenticateUser_callsAuthenticationManager() throws Exception {
        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(Mockito.any());
        mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content(userRegisterRequestJson))
                .andExpect(status().isOk())
                .andReturn();
        verify(authenticationManager, times(1))
                .authenticate(Mockito.any());
    }
}