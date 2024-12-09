
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import BUS.ChiTietDiemBUS;
import BUS.HocKyBUS;
import BUS.MonHocBUS;
import BUS.NamHocBUS;
import DTO.ChiTietDiemDTO;

public class EditScoreTest {

    private ChiTietDiemBUS ctbus;
    private MonHocBUS mhbus;
    private HocKyBUS hkbus;
    private NamHocBUS nhbus;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Thiết lập trước khi chạy tất cả các test, nếu cần thiết
    }

    @BeforeEach
    public void setUp() {
        // Khởi tạo các đối tượng xử lý dữ liệu
        ctbus = new ChiTietDiemBUS();
        mhbus = new MonHocBUS();
        hkbus = new HocKyBUS();
        nhbus = new NamHocBUS();

        // Khởi tạo dữ liệu cần thiết cho các bài test
        ctbus.list(); // Ví dụ, giả định phương thức này sẽ tải hoặc thiết lập dữ liệu cần thiết cho
                      // các bài test
    }

    // tc1: Dữ liệu hợp lệ cho việc cập nhật điểm
    @Test
    public void testUpdateScoreValid() {

        String idhs = "HSK242";
        String idmon = "MH2";
        String idhk = "1"; // Học kỳ hiện tại
        String idnh = "20242025"; // Năm học
        Double diem1 = 7.0;
        double diem2 = 8.0;
        double diem3 = 9.0;

        // Tạo DTO để cập nhật điểm
        ChiTietDiemDTO diemDTO = new ChiTietDiemDTO(idhs, idmon, idhk, idnh);
        diemDTO.setDiem1(diem1);
        diemDTO.setDiem2(diem2);
        diemDTO.setDiem3(diem3);
        diemDTO.calDtbMon(); // Tính toán điểm trung bình môn

        // Gọi hàm sửa điểm
        boolean rs = ctbus.set(diemDTO);

        // Kiểm tra kết quả cập nhật thành công
        boolean isChanged = rs;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertTrue(rs, "Điểm sửa không thành công");

        // Lấy lại dữ liệu đã được cập nhật và kiểm tra tính chính xác
        ChiTietDiemDTO updatedDiem = ctbus.get(idhs, idnh, idhk, idmon);
        assertNotNull(updatedDiem, "Không tìm thấy dữ liệu sau khi sửa");
        assertEquals(diem1, updatedDiem.getDiem1(), 0.01, "Điểm hệ 1 không đúng");
        assertEquals(diem2, updatedDiem.getDiem2(), 0.01, "Điểm hệ 2 không đúng");
        assertEquals(diem3, updatedDiem.getDiem3(), 0.01, "Điểm hệ 3 không đúng");
        assertEquals(8.33, updatedDiem.getDtbMon(), 0.01, "Điểm trung bình không đúng");
    }

    // tc2: điểm lớn hơn 10
    @Test
    public void testSet_ExceedBoundaryScore() {
        // Tạo DTO với điểm lớn hơn 10
        ChiTietDiemDTO invalidDiem = new ChiTietDiemDTO("HSK001", "MH01", "HK1", "20242025", 10.1, 8.0, 9.0);

        // Gọi hàm set
        boolean result = ctbus.set(invalidDiem);

        // Kiểm tra kết quả
        boolean isChanged = result;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertFalse(result, "Điểm lớn hơn 10 nhưng vẫn được chấp nhận");
    }

    // tc3: điểm nhỏ hơn 0
    @Test
    public void testSet_NegativeBoundaryScore() {
        // Tạo DTO với điểm nhỏ hơn 0
        ChiTietDiemDTO invalidDiem = new ChiTietDiemDTO("HSK001", "MH01", "HK1", "20242025", -0.1, 8.0, 9.0);

        // Gọi hàm set
        boolean result = ctbus.set(invalidDiem);

        // Kiểm tra kết quả
        boolean isChanged = result;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertFalse(result, "Điểm nhỏ hơn 0 nhưng vẫn được chấp nhận");
    }

    // tc4: Kiểm tra với điểm ở biên (0.0 và 10.0)
    @Test
    public void testUpdateScoreBoundaryValues() {

        String idhs = "HSK242";
        String idmon = "MH2";
        String idhk = "1";
        String idnh = "20242025";
        double diem1 = 0.0; // Điểm tối thiểu hợp lệ
        double diem2 = 10.0; // Điểm tối đa hợp lệ
        double diem3 = 5.0; // Điểm bình thường

        // Tạo DTO với các điểm biên
        ChiTietDiemDTO diemDTO = new ChiTietDiemDTO(idhs, idmon, idhk, idnh);
        diemDTO.setDiem1(diem1);
        diemDTO.setDiem2(diem2);
        diemDTO.setDiem3(diem3);
        diemDTO.calDtbMon(); // Tính toán điểm trung bình môn

        // Gọi hàm sửa điểm
        boolean rs = ctbus.set(diemDTO);

        // Kiểm tra điểm hợp lệ ở biên có được sửa thành công không
        boolean isChanged = rs;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertTrue(rs, "Điểm hợp lệ ở biên nhưng không được cập nhật");

        // Lấy lại dữ liệu đã được cập nhật và kiểm tra tính chính xác
        ChiTietDiemDTO updatedDiem = ctbus.get(idhs, idnh, idhk, idmon);
        assertNotNull(updatedDiem, "Không tìm thấy dữ liệu sau khi sửa");
        assertEquals(diem1, updatedDiem.getDiem1(), 0.01, "Điểm hệ 1 không đúng");
        assertEquals(diem2, updatedDiem.getDiem2(), 0.01, "Điểm hệ 2 không đúng");
        assertEquals(diem3, updatedDiem.getDiem3(), 0.01, "Điểm hệ 3 không đúng");
        assertEquals(5.83, updatedDiem.getDtbMon(), 0.01, "Điểm trung bình không đúng");
    }

    // tc5: Kiểm tra trường hợp đầu vào có giá trị rỗng
    @Test
    public void testUpdateScoreWithEmptyValues() {

        String idhs = "HSK242";
        String idmon = "MH2";
        String idhk = "1";
        String idnh = "20242025";
        Double diem1 = null; // Điểm rỗng
        Double diem2 = null; // Điểm rỗng
        Double diem3 = null; // Điểm rỗng

        // Tạo DTO với điểm rỗng
        ChiTietDiemDTO diemDTO = new ChiTietDiemDTO(idhs, idmon, idhk, idnh);
        diemDTO.setDiem1(diem1);
        diemDTO.setDiem2(diem2);
        diemDTO.setDiem3(diem3);

        // Gọi hàm sửa điểm
        boolean rs = ctbus.set(diemDTO);

        // Kiểm tra điểm không thể sửa với giá trị rỗng
        boolean isChanged = rs;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertFalse(rs, "Điểm rỗng vẫn được phép cập nhật");
    }

    // tc6 điểm nhập là chữ
    @Test
    public void testSet_ScoreWithNonNumeric() {
        ChiTietDiemDTO invalidDiem = new ChiTietDiemDTO("HSK001", "MH01", "HK1", "20242025");

        // Set điểm dạng chuỗi
        try {
            invalidDiem.setDiem1(Double.valueOf("abc")); // Giá trị không phải số
            invalidDiem.setDiem2(8.0);
            invalidDiem.setDiem3(9.0);
        } catch (NumberFormatException e) {
            // Bắt lỗi nếu set giá trị không phải số
        }

        // Gọi hàm set
        boolean result = ctbus.set(invalidDiem);

        // Kiểm tra kết quả
        boolean isChanged = result;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertFalse(result, "Điểm không phải là số nhưng vẫn được chấp nhận");
    }

    // tc7:điểm nhập là ký tự đặc biệt
    @Test
    public void testSet_ScoreWithNonNumeric2() {
        ChiTietDiemDTO invalidDiem = new ChiTietDiemDTO("HSK001", "MH01", "HK1", "20242025");

        // Set điểm dạng chuỗi
        try {
            invalidDiem.setDiem1(Double.valueOf("@#$")); // Giá trị không phải số
            invalidDiem.setDiem2(8.0);
            invalidDiem.setDiem3(9.0);
        } catch (NumberFormatException e) {
            // Bắt lỗi nếu set giá trị không phải số
        }

        // Gọi hàm set
        boolean result = ctbus.set(invalidDiem);

        // Kiểm tra kết quả
        boolean isChanged = result;
        if (isChanged == true) {
            System.out.println("Actual: Cập nhật điểm THÀNH CÔNG");
            System.out.println("-- Test PASS --");
        } else {
            System.out.println("Actual: Cập nhật điểm THẤT BẠI");
            System.out.println("-- Test FAIL --");
        }
        assertFalse(result, "Điểm không phải là số nhưng vẫn được chấp nhận");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        // Dọn dẹp tài nguyên nếu cần
    }
}
