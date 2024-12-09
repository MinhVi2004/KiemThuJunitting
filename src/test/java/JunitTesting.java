
import BUS.*;
import DTO.user;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Sắp xếp theo @Order
public class JunitTesting {
    private User_BUS userBUS;

    private JunitTesting(){
        userBUS = new User_BUS();
    }

    @Test
    @Order(1)
    public void testUpdateTaiKhoan_SuccessUpdate() {
        user newDataUser = new user("GV1", "12345678","HS", "1");
        userBUS.updateuser(newDataUser);
        ArrayList<user> listUser = userBUS.getList();
        boolean isUpdated = false;
        for(user u : listUser) {
            if (u.getusername().equals(newDataUser.getusername()) &&
                u.getpassword().equals(newDataUser.getpassword()) &&
                u.getrole().equals(newDataUser.getrole()) &&
                u.getenable().equals(newDataUser.getenable()) ) {
                    isUpdated = true;
                    break;
            }
        }
        assertTrue(isUpdated);
        System.out.println("--- Test Case 1 --- ");
        if(isUpdated) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }
    @Test
    @Order(2)
    public void testUpdateTaiKhoan_InvalidUsername() {
        user newDataUser = new user("NonExistentUser", "password123", "GV", "1");
        userBUS.updateuser(newDataUser);
        ArrayList<user> listUser = userBUS.getList();
        boolean isUpdated = false;

        for (user u : listUser) {
            if (u.getusername().equals(newDataUser.getusername())) {
                isUpdated = true;
                break;
            }
        }

        assertFalse(isUpdated);
        System.out.println("--- Test Case 2 --- ");
        if(!isUpdated) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }
    @Test
    @Order(3)
    public void testUpdateTaiKhoan_InvalidRole() {
        user newDataUser = new user("GV1", "abc", "INVALID_ROLE", "1");
        userBUS.updateuser(newDataUser);
        ArrayList<user> listUser = userBUS.getList();
        boolean isUpdated = false;

        for (user u : listUser) {
            if (u.getusername().equals(newDataUser.getusername()) &&
                u.getrole().equals(newDataUser.getrole())) {
                    isUpdated = true;
                    break;
            }
        }
        
        System.out.println("--- Test Case 3 --- ");
        if(!isUpdated) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        assertFalse(isUpdated);
    }
    @Test
    @Order(4)
    public void testUpdateTaiKhoan_NullOrEmptyData() {
        user newDataUser = new user("", "", "", null);
        userBUS.updateuser(newDataUser);
        ArrayList<user> listUser = userBUS.getList();
        boolean isUpdated = false;

        for (user u : listUser) {
            if (u.getusername().equals(newDataUser.getusername())) {
                isUpdated = true;
                break;
            }
        }

        assertFalse(isUpdated);
        System.out.println("--- Test Case 4 --- ");
        if(!isUpdated) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }
    @Test
    @Order(5)
    public void testUpdateTaiKhoan_NoChangeOnFailure() {
        ArrayList<user> originalList = new ArrayList<>(userBUS.getList());
        user newDataUser = new user("InvalidUser", "password123", "GV", "1");
        userBUS.updateuser(newDataUser);
        ArrayList<user> updatedList = userBUS.getList();
        
        assertEquals(originalList.size(), updatedList.size());
        assertEquals(originalList, updatedList);
        
        System.out.println("--- Test Case 5 --- ");
        if(originalList.size() == updatedList.size()) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }
    @Test
    @Order(5)
    public void testUpdateTaiKhoan_CheckRemainAttribute() {
        ArrayList<user> originalList = new ArrayList<>(userBUS.getList());
        user newDataUser = new user("GV1", "555555", "GV", "1");
        userBUS.updateuser(newDataUser);
        ArrayList<user> updatedList = userBUS.getList();
        
        assertEquals(originalList.size(), updatedList.size());
        assertEquals(originalList, updatedList);
        
        System.out.println("--- Test Case 5 --- ");
        if(originalList.size() == updatedList.size()) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }

}
