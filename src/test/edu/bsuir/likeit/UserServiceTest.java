package test.edu.bsuir.likeit;

import edu.bsuir.likeit.entity.User;
import edu.bduir.likeit.service.UserService;
import edu.bsuir.likeit.util.MD5Digest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserServiceTest {
        private static final String LOGIN = "newser";
        private static final String PASSWORD = "1111";
        private static final String FAKE_PASSWORD = "not_a_passwd_at_all";
        private static final String NEW_LOGIN = "BRAND_NEW_LOGIN";
        private User user = new User();

        @Before
        public void setUp() throws Exception {
            user.setLogin(LOGIN);
            user.setPassword(PASSWORD);
            user = UserService.registerNewUser(user);
        }

        @After
        public void tearDown() throws Exception {
            UserService.deleteUser(user);
        }

        @Test
        public void findByCredentials1() throws Exception {
            assertTrue("present user",
                    UserService.getByData(LOGIN, MD5Digest.encrypt(PASSWORD)).getId() == user.getId());
        }

        @Test
        public void findByCredentials2() throws Exception {
            assertNull("not present user",
                    UserService.getByData(LOGIN, FAKE_PASSWORD));
        }

        @Test
        public void isLoginFree1() throws Exception {
            assertTrue("free login",
                    UserService.isLoginFree(NEW_LOGIN));
        }

        @Test
        public void isLoginFree2() throws Exception {
            assertFalse("occupied login",
                    UserService.isLoginFree(LOGIN));
        }
}