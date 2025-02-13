package DAO;

import DTO.GiaoVienDTO;
import DATABASE.MySQLConnect;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.SQLException;
public class GiaoVienDAO {
    private  MySQLConnect mySQL = new MySQLConnect();

    public GiaoVienDAO() {
    }
    public int getQuantityGV() {
        try {
            String sql = "SELECT COUNT(*) AS Number FROM giaovien";
            ResultSet rs = mySQL.executeQuery(sql);
            if(rs.next())
            {
                return rs.getInt("Number");
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
    public ArrayList<GiaoVienDTO> list()
    {
        ArrayList<GiaoVienDTO> gv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM giaovien WHERE enable = 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maGV = rs.getString("GiaoVienid");
                String tenGV = rs.getString("TenGiaoVien");
                String GioiTinh = rs.getString("GioiTinh");
                String NamSinh= rs.getString("NamSinh");
                String DiaChi = rs.getString("DiaChi");
                String DienThoai = rs.getString("DienThoai");
                String PhanMon = rs.getString("PhanMon");
                String IMG = rs.getString("IMG");
                GiaoVienDTO s = new GiaoVienDTO(maGV, tenGV, GioiTinh, IMG, NamSinh, DienThoai, PhanMon, DiaChi);
                gv.add(s);
            }
            rs.close();
            mySQL.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(GiaoVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gv;
    }
    public void add(GiaoVienDTO gv)
    {
        String sql = "INSERT INTO giaovien VALUES (";
        sql += "'"+gv.getMaGV()+"',";
        sql += "'"+gv.getTenGV()+"',";
        sql += "'"+gv.getGioiTinh()+"',";
        sql += "'"+gv.getNamSinh()+"',";
        sql += "'"+gv.getDiachi()+"',";
        sql += "'"+gv.getDienThoai()+"',";
        sql += "'"+gv.getphanMon()+"',";
        sql += "'"+gv.getIMG()+"',";
        sql += "'0',";
        sql += "'1')";
        // System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    // public void delete(String idGV)
    // {
    //     String sql = "UPDATE giaovien SET enable = 0 WHERE GiaoVienid ='"+idGV+"'";
       
    //     System.out.println(sql);
    //     mySQL.executeUpdate(sql);
    //    System.out.println("delete");
       
    // }

    public void delete(String idGV) {
        String sql = "UPDATE giaovien SET enable = 0 WHERE GiaoVienid = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement pstmt = null;

        try {
            // Lấy kết nối từ MyConnection
            con = new MySQLConnect().getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, idGV);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("No record found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng PreparedStatement và Connection
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // public void set(GiaoVienDTO gv)
    // {
    //     String sql = "UPDATE giaovien SET ";
    //     sql += "TENGV='"+gv.getTenGV()+"', ";
    //     sql += "GIOITINH='"+gv.getGioiTinh()+"', ";
    //     sql += "NAMSINH='"+gv.getNamSinh()+"', ";
    //     sql += "DIENTHOAI='"+gv.getDienThoai()+"', ";
    //     sql += "IMG='"+gv.getIMG()+"' ";
    //     sql += "WHERE MAGV='"+gv.getMaGV()+"'";
    //     System.out.println(sql);
    //     mySQL.executeUpdate(sql);
    // }
    public void ImportExcelDatabase(File file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ImportExcelDatabase'");
    }
    // public void Update(GiaoVienDTO gv) {
   //     MySQLConnect mysql = new MySQLConnect();
    //     String sql = "UPDATE giaovien SET ";
    //     // sql += "GiaoVienid  = '" + hs.getHocSinhID() + "' ,";
    //     sql += "TenGiaoVien = '" + gv.getTenGV() + "' ,";
    //     sql += "GioiTinh = '" + gv.getGioiTinh() + "' ,";
    //     sql += "NamSinh	 = '" + gv.getNamSinh() + "' ,";
    //     sql += "DiaChi = '" + gv.getDiachi() + "' ,";
    //     sql += "DienThoai = '" + gv.getDienThoai() + "' ,";
    //     sql += "IMG = '" + gv.getIMG() + "' ";
    //     sql += "WHERE HocSinhid='" +gv.getMaGV() + "'";
    //     mysql.executeUpdate(sql);
    //     System.out.println(sql); // Đoạn này để kiểm tra xem câu lệnh SQL có đúng không
    // }
    public boolean Update(GiaoVienDTO gv) {
        String sql = "UPDATE giaovien SET TenGiaoVien = ?, GioiTinh = ?, NamSinh = ?, DiaChi = ?, DienThoai = ?, PhanMon = ?, IMG = ? WHERE GiaoVienid = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            con = new MySQLConnect().getConnection();
            if (con != null) {
                // Chuẩn bị câu lệnh SQL
                ps = con.prepareStatement(sql);
                ps.setString(1, gv.getTenGV());
                ps.setString(2, gv.getGioiTinh());
                ps.setString(3, gv.getNamSinh());
                ps.setString(4, gv.getDiachi());
                ps.setString(5, gv.getDienThoai());
                ps.setString(6, gv.getphanMon());
                ps.setString(7, gv.getIMG());
                ps.setString(8, gv.getMaGV());
                
                // Thực thi câu lệnh SQL
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Cập nhật thành công giáo viên ID: " + gv.getMaGV());
                    return true;
                } else {
                    System.out.println("Không tìm thấy giáo viên có ID: " + gv.getMaGV());
                    return false;
                }
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return false;
            }
        } catch (SQLException e) {
            // In chi tiết lỗi SQL và thông báo lỗi
            System.out.println("Lỗi khi cập nhật giáo viên: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setSubmit(String magv, int isSubit) {
        String sql = "UPDATE giaovien SET isSubmit = ? WHERE GiaoVienid = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            con = new MySQLConnect().getConnection();
            if (con != null) {
                // Chuẩn bị câu lệnh SQL
                ps = con.prepareStatement(sql);
                ps.setInt(1, isSubit);
                ps.setString(2, magv);
                
                // Thực thi câu lệnh SQL
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Cập nhật thành công isSubmit");
                    return ;
                } else {
                    System.out.println("Không tìm thấy giáo viên");
                    return;
                }
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return ;
            }
        } catch (SQLException e) {
            // In chi tiết lỗi SQL và thông báo lỗi
            System.out.println("Lỗi khi cập nhật giáo viên: " + e.getMessage());
            e.printStackTrace();
            return;
        } finally {
            try {
                if (ps != null) ps.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void resetSubmit() {
        String sql = "UPDATE giaovien SET isSubmit = 0";
        try (java.sql.Connection con = new MySQLConnect().getConnection();
             java.sql.PreparedStatement ps = con != null ? con.prepareStatement(sql) : null) {
    
            if (ps != null && ps.executeUpdate() > 0) {
                System.out.println("Cập nhật thành công isSubmit");
            } else {
                System.out.println("Không tìm thấy giáo viên hoặc không thể kết nối đến cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật giáo viên: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Integer getSubmit(String magv) {
        String sql = "SELECT isSubmit FROM giaovien WHERE GiaoVienid = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            con = new MySQLConnect().getConnection();
            if (con != null) {
                // Chuẩn bị câu lệnh SQL
                ps = con.prepareStatement(sql);
                ps.setString(1, magv);
                
                // Thực thi câu lệnh SQL
                rs = ps.executeQuery();
                
                // Kiểm tra kết quả trả về
                if (rs.next()) {
                    int isSubmit = rs.getInt("isSubmit");
                    return isSubmit; // Trả về giá trị isSubmit
                } else {
                    System.out.println("Không tìm thấy giáo viên với mã: " + magv);
                    return null; // Trả về null nếu không tìm thấy giáo viên
                }
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return null; // Trả về null nếu không thể kết nối
            }
        } catch (SQLException e) {
            // In chi tiết lỗi SQL và thông báo lỗi
            System.out.println("Lỗi khi lấy isSubmit: " + e.getMessage());
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getMonHocId( String magv) {
        String monHocId = null;  // Single result
        String sql = "SELECT monhoc.MonHocid FROM giaovien "
                   + "JOIN monhoc ON giaovien.PhanMon = monhoc.TenMonHoc and enable = 1 where GiaoVienid = ?" ;
        
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            // Connect to the database
            con = new MySQLConnect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, magv);
            
            // Execute the query
            rs = ps.executeQuery();
            
            // Get the result (if it exists)
            if (rs.next()) {
                monHocId = rs.getString("MonHocid");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();  // Log any SQL errors
        } finally {
            // Close the resources properly
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return monHocId;
    }

    public Integer CountGV() {
        String sql = "SELECT COUNT(*) AS count FROM giaovien";
        Integer count = 0;

        try {
            ResultSet rs = mySQL.executeQuery(sql);

            if (rs.next()) {
                int numberOfGiaoVien = rs.getInt("count");
                count = numberOfGiaoVien;
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }

        return count;
    }

public ArrayList<GiaoVienDTO> checkMagv() {
        ArrayList<GiaoVienDTO> dsgv = new ArrayList<>();

        String sql = "SELECT GiaoVienid  FROM giaovien";
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                String mahs = rs.getString("GiaoVienid");

                GiaoVienDTO gv = new GiaoVienDTO(mahs, "", "", "", "", "","","");
                dsgv.add(gv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsgv;
    }
    public static void main(String[] args) {
        GiaoVienDAO dao = new GiaoVienDAO();
        System.out.println(dao.getMonHocId("GV1"));
    }
}
