/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


 import org.junit.jupiter.api.AfterEach;
 import org.junit.jupiter.api.AfterAll;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
 
 import java.util.ArrayList;
 
 import DTO.DoiMK_DTO;
 import DTO.user;
 import DTO.test;

 import BUS.DoiMK_BUS;
 import BUS.User_BUS;
 /**
  *
  * @author dvmv2
  */
 @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 public class DoiMatKhauTest {
     private User_BUS userBUS;
     private DoiMK_BUS doiMKBUS;
     
     public DoiMatKhauTest() {
         userBUS = new User_BUS();
         doiMKBUS = new DoiMK_BUS();
     }
     
     @BeforeAll
     public static void setUpClass() {
     }
     
     @AfterAll
     public static void tearDownClass() {
     }
     
     @BeforeEach
     public void setUp() {
     }
     
     @AfterEach
     public void tearDown() {
     }
 
     //testcase 1: Thêm thành công
      @Test
      @Order(1)
      public void testChangePassword_ValidData() {
            String success = "Đổi mật khẩu thành công";
            System.out.println("        -- TestChangePassword_ValidData --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "newPassword@123","newPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Đổi mật khẩu thành công");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
       //testcase 2
      @Test
      @Order(2)
      public void testChangePassword_NoData() {
            String success = "Mật khẩu cũ không được để trống!";
            System.out.println("        -- TestChangePassword_NoData --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "", "","");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu cũ không được để trống!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      //testcase 3
      @Test
      @Order(3)
      public void testChangePassword_NoOldPassword() {
            String success = "Mật khẩu cũ không được để trống!";
            System.out.println("        -- testChangePassword_NoOldPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "", "newPassword@123","newPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu cũ không được để trống!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      @Test
      @Order(4)
      public void testChangePassword_IncorrectOldPassword() {
            String success = "Mật khẩu cũ không đúng cho username";
            System.out.println("        -- TestChangePassword_IncorrectOldPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "incorrectOldPassword@123", "newPassword@123","newPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu cũ không đúng cho username");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      @Test
      @Order(5)
      public void testChangePassword_NoNewPasswordAndRenewPassword() {
            String success = "Mật khẩu mới và mật khẩu nhập lại không được để trống!";
            System.out.println("        -- TestChangePassword_NoNewPasswordAndRenewPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "","");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu mới và mật khẩu nhập lại không được để trống!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      @Test
      @Order(6)
      public void testChangePassword_NonewPassword() {
            String success = "Mật khẩu mới không được để trống!";
            System.out.println("        -- TesthangePassword_NonewPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "","newPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu mới không được để trống!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      @Test
      @Order(7)
      public void testChangePassword_NoRenewPassword() {
            String success = "Mật khẩu nhập lại không được để trống!";
            System.out.println("        -- TestChangePassword_NoRenewPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "newPassword@123","");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu nhập lại không được để trống!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      @Test
      @Order(8)
      public void testChangePassword_IncorrectRenewPassword() {
            String success = "Mật khẩu mới và mật khẩu nhập lại không khớp!";
            System.out.println("        -- TestChangePassword_IncorrectRenewPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "newPassword@123","IncorrectRenewPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu mới và mật khẩu nhập lại không khớp!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            assertEquals(success, output);
      }
      @Test
      @Order(9)
      public void testChangePassword_Under5LengthNewPassword() {
            String success = "Mật khẩu mới phải có ít nhất 5 ký tự!";
            System.out.println("        -- TestChangePassword_Under5LengthNewPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "newP","newP");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu mới phải có ít nhất 5 ký tự!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            
            assertEquals(success, output);
      }
      @Test
      @Order(10)
      public void testChangePassword_SameOldAndNewPassword() {
            String success = "Mật khẩu mới và mật khẩu cũ không được trùng!";
            System.out.println("        -- TestChangePassword_SameOldAndNewPassword --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "oldPassword@123","oldPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu mới và mật khẩu cũ không được trùng!");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            
            assertEquals(success, output);
      }
      @Test
      @Order(11)
      public void testChangePassword_NewpasswordNoSpace() {
            String success = "Mật khẩu không được chứa khoảng trắng.";
            System.out.println("        -- testChangePassword_NewpasswordNoSpace --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "old Password@123","oldPassword@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu không được chứa khoảng trắng.");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            
            assertEquals(success, output);
      }
      @Test
      @Order(12)
      public void testChangePassword_ReNewpasswordNoSpace() {
            String success = "Mật khẩu nhập lại không được chứa khoảng trắng.";
            System.out.println("        -- testChangePassword_NewpasswordNoSpace --");
            user newUser = new user("userTest", "oldPassword@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            test newMK = new test("userTest", "oldPassword@123", "oldPassword@123","old Password@123");
            String output = doiMKBUS.changePassword1(newMK);
            System.out.println("Expect: Mật khẩu nhập lại không được chứa khoảng trắng.");
            System.out.println("Actual: " + output);
            if(output.equals(success)) {
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Test FAIL --");
            }
            
            userBUS.delete(newUser.getusername());
            
            assertEquals(success, output);
      }
 }
 