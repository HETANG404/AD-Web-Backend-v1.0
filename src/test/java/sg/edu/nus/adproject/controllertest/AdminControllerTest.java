package sg.edu.nus.adproject.controllertest;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sg.edu.nus.adproject.Controller.AdminController;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Service.AdminService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

//    模拟成功的登录请求，验证 userExists 和 authenticate 方法的调用，检查返回的 JSON 内容是否符合预期。
    @Test
    void testLoginSuccess() throws Exception {
        String username = "admin";
        String password = "password";

        // Mock the adminService behavior
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        when(adminService.userExists(username)).thenReturn(true);
        when(adminService.authenticate(username, password)).thenReturn(true);

        // Perform the POST request and assert the result
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.redirectUrl").value("/"));

        verify(adminService, times(1)).userExists(username);
        verify(adminService, times(1)).authenticate(username, password);
    }




//    模拟密码错误的登录请求，检查错误消息。
    @Test
    void testLoginFailureIncorrectPassword() throws Exception {
        String username = "admin";
        String password = "password";

        // Mock the adminService behavior
        when(adminService.userExists(username)).thenReturn(true);
        when(adminService.authenticate(username, password)).thenReturn(false);

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Incorrect username or password"));

        verify(adminService, times(1)).userExists(username);
        verify(adminService, times(1)).authenticate(username, password);
    }


//    模拟成功的登出请求，检查返回的 JSON 内容。
    @Test
    void testLogout() throws Exception {
        mockMvc.perform(post("/api/admin/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Logged out successfully."));
    }


    // 1.4 输入验证（缺少 username 或 password 字段）
    @Test
    void testLoginMissingFields() throws Exception {
        // 测试缺少 username 字段
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"password\":\"password\"}"))
                .andExpect(status().isBadRequest())  // 期望返回 400
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Username is required"));

        // 测试缺少 password 字段
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\"}"))
                .andExpect(status().isBadRequest())  // 期望返回 400
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Password is required"));
    }


    // 2.2 非登录状态下尝试登出
    @Test
    void testLogoutWithoutLogin() throws Exception {
        // 模拟未登录的情况
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        mockMvc.perform(post("/api/admin/logout"))
                .andExpect(status().isOk())  // 或者使用 .isUnauthorized() 视乎你的登出逻辑
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Logged out successfully."));
    }

    // 3.1 会话信息存储
    @Test
    void testSessionStorageAfterLogin() throws Exception {
        String username = "admin";
        String password = "password";

        // 模拟登录行为
        when(adminService.userExists(username)).thenReturn(true);
        when(adminService.authenticate(username, password)).thenReturn(true);

        // 创建一个 MockHttpSession
        MockHttpSession mockSession = new MockHttpSession();

        mockMvc.perform(post("/api/admin/login")
                        .session(mockSession)  // 将 MockHttpSession 注入
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.redirectUrl").value("/"));

        // 验证 session 中的用户信息
        assertEquals(username, mockSession.getAttribute("loggedInUser"));
    }


    // 3.2 会话失效
    @Test
    void testSessionInvalidationAfterLogout() throws Exception {
        String username = "admin";
        String password = "password";

        // 创建一个 MockHttpSession
        MockHttpSession mockSession = new MockHttpSession();

        // 模拟登录
        when(adminService.userExists(username)).thenReturn(true);
        when(adminService.authenticate(username, password)).thenReturn(true);

        // 登录操作，设置 session
        mockMvc.perform(post("/api/admin/login")
                        .session(mockSession)  // 使用 mock session
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.redirectUrl").value("/"));

        // 验证登录后 session 中的用户信息
        assertEquals(username, mockSession.getAttribute("loggedInUser"));

        // 模拟登出
        mockMvc.perform(post("/api/admin/logout")
                        .session(mockSession))  // 传入同一个 session
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Logged out successfully."));

        // 验证会话被清除后，不再访问会话属性
        // 不再访问 session 属性，而是直接验证会话是否无效
        assertFalse(!mockSession.isInvalid());  // 会话应当被无效化
    }


}
