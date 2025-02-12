package sg.edu.nus.adproject.integrationtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Repository.AdminRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminRepository adminRepository; // 自动注入 AdminRepository，用于操作数据库

    @BeforeEach
    void setUp() {
        // 清空数据库数据，以确保每次测试都从干净的数据库开始
        adminRepository.deleteAll();
    }

    // 模拟成功的登录请求，确保与数据库的交互是有效的，并且返回正确的响应。
    @Test
    void testLoginSuccessIntegration() throws Exception {
        // 初始化数据
        Admin admin = new Admin(null, "admin", "password");
        adminRepository.save(admin);

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.redirectUrl").value("/"));
    }

    // 模拟用户名不存在的登录请求，验证返回错误消息。
    @Test
    void testLoginFailureIntegration() throws Exception {
        // 测试用户名不存在的情况
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"nonexistent\",\"password\":\"password\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Username does not exist!"));
    }

    // 测试登出功能，确保会话被注销，并返回正确的响应。
    @Test
    void testLogoutIntegration() throws Exception {
        // 初始化数据
        Admin admin = new Admin(null, "admin", "password");
        adminRepository.save(admin);

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/admin/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Logged out successfully."));
    }

    // 测试缺少用户名的登录请求
    @Test
    void testLoginFailureMissingUsername() throws Exception {
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"password\":\"password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Username is required"));
    }

    // 测试缺少密码的登录请求
    @Test
    void testLoginFailureMissingPassword() throws Exception {
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Password is required"));
    }

//sql注入
    @Test
    void testLoginFailureSqlInjection() throws Exception {
        String maliciousUsername = "admin' OR '1'='1";
        String maliciousPassword = "password' OR '1'='1";

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"" + maliciousUsername + "\",\"password\":\"" + maliciousPassword + "\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Incorrect username or password"));
    }


//    2.2 非登录状态下尝试登出
    @Test
    void testLogoutWithoutLogin() throws Exception {
        mockMvc.perform(post("/api/admin/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Logged out successfully."));
    }


//确保用户信息在登录后存储在会话中：
    @Test
    void testSessionStorageAfterLogin() throws Exception {
        Admin admin = new Admin(null, "admin", "password");
        adminRepository.save(admin);

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 模拟请求已登录的受保护资源
        mockMvc.perform(post("/api/admin/protected-endpoint"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }


//    测试用户登出后会话是否清除：
    @Test
    void testSessionInvalidAfterLogout() throws Exception {
        Admin admin = new Admin(null, "admin", "password");
        adminRepository.save(admin);

        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 登出
        mockMvc.perform(post("/api/admin/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        // 确认会话无效
        mockMvc.perform(post("/api/admin/protected-endpoint"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Please log in to access this resource"));
    }


}
