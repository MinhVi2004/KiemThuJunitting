package BUS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JOptionPane;

import DAO.DoiMK_DAO;
import DTO.DoiMK_DTO;
import DTO.test;

public class DoiMK_BUS {
    private DoiMK_DAO doiMK_DAO;

    public DoiMK_BUS() {
        doiMK_DAO = new DoiMK_DAO();
    }

    public String changePassword1(test a) {
        if (a.getOldPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu cũ không được để trống!");
            return "Mật khẩu cũ không được để trống!";
        }
        // Kiểm tra xem trường mật khẩu mới và mật khẩu nhập lại có bị trống không
        if (a.getNewPassword().isEmpty() && a.getRenewPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu mới và mật khẩu nhập lại không được để trống!");
            return "Mật khẩu mới và mật khẩu nhập lại không được để trống!";
        }
        if (a.getNewPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu mới không được để trống!");
            return "Mật khẩu mới không được để trống!";
        }
        if (a.getRenewPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không được để trống!");
            return "Mật khẩu nhập lại không được để trống!";
        }
            // Kiểm tra xem mật khẩu mới có chứa khoảng trắng không
        if (a.getNewPassword().contains(" ")) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được chứa khoảng trắng.");
            return "Mật khẩu không được chứa khoảng trắng.";
        }
        if (a.getRenewPassword().contains(" ")) {
            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không được chứa khoảng trắng.");
            return "Mật khẩu nhập lại không được chứa khoảng trắng.";
        }
            // Kiểm tra xem mật khẩu mới và mật khẩu nhập lại có khớp nhau không
        if (!a.getNewPassword().equals(a.getRenewPassword())) {
            JOptionPane.showMessageDialog(null, "Mật khẩu mới và mật khẩu nhập lại không khớp!");
            return "Mật khẩu mới và mật khẩu nhập lại không khớp!";
        }
        if (a.getNewPassword().equals(a.getOldPassword())) {
            JOptionPane.showMessageDialog(null, "Mật khẩu mới và mật khẩu cũ không được trùng!");
            return "Mật khẩu mới và mật khẩu cũ không được trùng!";
        }
        if (a.getNewPassword().length() < 5) {
            JOptionPane.showMessageDialog(null, "Mật khẩu mới phải có ít nhất 5 ký tự!");
            return "Mật khẩu mới phải có ít nhất 5 ký tự!";
        }
        return changePassword2(new DoiMK_DTO(a.getUsername(), a.getOldPassword(), a.getNewPassword()));
    }
    public String changePassword2(DoiMK_DTO doiMK_DTO) {
        String username = doiMK_DTO.getUsername();
        String oldPassword = doiMK_DTO.getOldPassword();
        String newPassword = doiMK_DTO.getNewPassword();
        
        // Gọi phương thức changePassword từ DoiMK_DAO để kiểm tra và cập nhật mật khẩu
        return doiMK_DAO.changePassword3(username, oldPassword, newPassword);
    }
    public boolean changePassword(DoiMK_DTO doiMK_DTO) {
        String username = doiMK_DTO.getUsername();
        String oldPassword = doiMK_DTO.getOldPassword();
        String newPassword = doiMK_DTO.getNewPassword();
        
        // Gọi phương thức changePassword từ DoiMK_DAO để kiểm tra và cập nhật mật khẩu
        return doiMK_DAO.changePassword(username, oldPassword, newPassword);
    }
    
}
