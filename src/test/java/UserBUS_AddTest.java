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
 
 import DTO.user;
 import BUS.User_BUS;
 /**
  *
  * @author dvmv2
  */
 @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 public class UserBUS_AddTest {
     private User_BUS userBUS;
     
     public UserBUS_AddTest() {
         userBUS = new User_BUS();
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
 
     //? Kiểm thử add() tích cực
      @Test
      @Order(1)
      public void testAddUser_ValidUser() {
            System.out.println("1.        -- TestAddUser_ValidUser --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("userTest", "userTest@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + (beforeLength+1) + "| Actual: " + afterLength);
            if(beforeLength+1 == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertTrue(isAdded);
            assertEquals(beforeLength + 1 , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
       
      //? Kiểm thử add() tiêu cực
      @Test
      @Order(2)
      public void testAddUser_NoData() {
            System.out.println("2.        -- TestAddUser_NoData --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("", "", "", "");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + (beforeLength) + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(3)
      public void testAddUser_NoUsername() {
            System.out.println("3.        -- TestAddUser_NoUsername --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("", "userTest@123", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(4)
      public void testAddUser_ExistedUsername() {
            System.out.println("4.        -- TestAddUser_ExistedUsername --");
            int beforeLength = userBUS.getList().size();
            user existedUser = new user("userTestExisted", "userTest@123", "GV", "1");
            userBUS.add(existedUser);
            userBUS.list();
            
            user newUser = new user("userTestExisted", "userTest@12344", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(5)
      public void testAddUser_NoPassword() {
            System.out.println("5.        -- TestAddUser_NoPassword --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("userTest", "", "GV", "1");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(6)
      public void testAddUser_NoRole() {
            System.out.println("6.        -- TestAddUser_NoRole --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("userTest", "userTest@123", "", "1");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(7)
      public void testAddUser_UnvalidRole() {
            System.out.println("7.        -- TestAddUser_UnvalidRole --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("userTest", "userTest@123", "123", "1");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(8)
      public void testAddUser_NoEnable() {
            System.out.println("8.        -- TestAddUser_NoEnable --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("userTest", "userTest@123", "GV", "");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
      @Test
      @Order(9)
      public void testAddUser_UnvalidEnabe() {
            System.out.println("9.        -- TestAddUser_UnvalidEnabe --");
            int beforeLength = userBUS.getList().size();
            user newUser = new user("userTest", "userTest@123", "GV", "a");
            userBUS.add(newUser);
            userBUS.list();
            boolean isAdded = false;
            int afterLength = userBUS.getList().size();
            for(user u : userBUS.getList()) {
                  if(u.getusername().equals(newUser.getusername()) && 
                     u.getpassword().equals(newUser.getpassword()) &&
                     u.getrole().equals(newUser.getrole()) &&
                     u.getenable().equals(newUser.getenable())) {
                        isAdded = true;
                  }
            }
            if(isAdded == true) {
                  System.out.println("-- Thêm tài khoản THÀNH CÔNG --");
            } else {
                  System.out.println("-- Thêm tài khoản THẤT BẠI --");
            }
            System.out.println("-- Before:"+beforeLength+" |  Expect: " + beforeLength + "| Actual: " + afterLength);
            if(beforeLength == afterLength) {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản không thành công --");
                  System.out.println("-- Test PASS --");
            } else {
                  System.out.println("-- Mong đợi: Thêm tài khoản không thành công \n-- Thực Tế : Thêm tài khoản thành công --");
                  System.out.println("-- Test FAIL --");
            }
            assertFalse(isAdded);
            assertEquals(beforeLength , afterLength);
            
            userBUS.delete(newUser.getusername());
      }
 }
 