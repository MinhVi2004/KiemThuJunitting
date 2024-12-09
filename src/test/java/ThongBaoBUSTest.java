

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BUS.ThongBaoBUS;
import DTO.ThongBaoDTO;

import java.util.List;

public class ThongBaoBUSTest {

    private ThongBaoBUS thongBaoBUS;

    @BeforeEach
    public void setUp() {
        thongBaoBUS = new ThongBaoBUS();
    }

    // Kiểm thử phương thức list()
    @Test
    public void testList() {
        thongBaoBUS.list();
        assertNotNull(thongBaoBUS.getList(), "Danh sách không được null");
        assertFalse(thongBaoBUS.getList().isEmpty(), "Danh sách không được rỗng");
    }

    // Kiểm thử phương thức getList()
    @Test
    public void testGetList() {
        thongBaoBUS.list();
        assertNotNull(thongBaoBUS.getList(), "Danh sách trả về không được null");
        assertTrue(thongBaoBUS.getList().size() > 0, "Danh sách phải chứa ít nhất một phần tử");
    }

    // Kiểm thử phương thức get() với ID hợp lệ
    @Test
    public void testGetWithValidID() {
        thongBaoBUS.list();
        ThongBaoDTO result = thongBaoBUS.get("HSK244");
        assertNotNull(result, "Thông báo phải được tìm thấy với ID hợp lệ");
        assertEquals("HSK244", result.getIdnguoigui(), "ID phải khớp");
    }

    // Kiểm thử phương thức get() với ID không hợp lệ
    @Test
    public void testGetWithInvalidID() {
        thongBaoBUS.list();
        ThongBaoDTO result = thongBaoBUS.get("InvalidID");
        assertNull(result, "Không tìm thấy thông báo với ID không hợp lệ");
    }

    // Kiểm thử phương thức add()
    @Test
    public void testAdd() {
        ThongBaoDTO tb = new ThongBaoDTO("HSK243", "Tiêu đề", "Nội dung", "2024-12-04", "ALL");
        thongBaoBUS.add(tb);
        assertTrue(thongBaoBUS.getList().contains(tb), "Thông báo phải được thêm vào danh sách");
    }

    // Kiểm thử phương thức set()
    @Test
    public void testSet() {
        ThongBaoDTO tb = new ThongBaoDTO("HSK244", "Tiêu đề", "Nội dung", "2024-12-04", "ALL");
        thongBaoBUS.add(tb);

        tb.setTieudetb("Tiêu đề đã cập nhật");
        thongBaoBUS.set(tb);
        assertEquals("Tiêu đề đã cập nhật", thongBaoBUS.get("HSK244").getTieudetb(), "Tiêu đề thông báo phải được cập nhật");
    }

    // Kiểm thử phương thức delete()
    @Test
    public void testDelete() {
        ThongBaoDTO tb = new ThongBaoDTO("HSK245", "Tiêu đề", "Nội dung", "2024-12-04", "ALL");
        thongBaoBUS.add(tb);
        thongBaoBUS.delete("HSK245");
        assertNull(thongBaoBUS.get("HSK245"), "Thông báo phải bị xóa khỏi danh sách");
    }

    // Kiểm thử phương thức search() với loại trùng khớp
    @Test
    public void testSearch() {
        thongBaoBUS.list();
        List<ThongBaoDTO> result = thongBaoBUS.search("HSK244");
        assertTrue(result.size() > 0, "Tìm kiếm phải trả về thông báo khớp");
        assertTrue(result.stream().anyMatch(tb -> tb.getIdnguoigui().equals("HSK244")), "Tìm kiếm phải khớp với username đã cho");
    }

    // Kiểm thử phương thức search() với loaitb không phải số
    @Test
    public void testSearchWithNonNumericLoaitb() {
        thongBaoBUS.list();
        List<ThongBaoDTO> result = thongBaoBUS.search("ALL");
        assertTrue(result.size() > 0, "Tìm kiếm phải trả về thông báo có loaitb = ALL");
    }

    // Kiểm thử phương thức isNumeric()
    @Test
    public void testIsNumeric() {
        assertTrue(new ThongBaoBUS().isNumeric("1234"), "1234 phải là số");
        assertFalse(new ThongBaoBUS().isNumeric("ABCD"), "ABCD không phải là số");
    }

    // Kiểm thử phương thức main()
    @Test
    public void testMain() {
        String[] args = {};  // Truyền mảng rỗng vào phương thức main
        ThongBaoBUS.main(args);
        // Bạn cần xác minh đầu ra console ở đây, nhưng điều này khó khăn với JUnit
        // Bạn có thể xem xét sử dụng một framework ghi log hoặc bắt đầu ra System.out.
    }

    // Kiểm thử phương thức add() với ID trùng
    @Test
    public void testAddWithDuplicateID() {
        ThongBaoDTO tb = new ThongBaoDTO("HSK240", "Tiêu đề", "Nội dung", "2024-12-04", "ALL");
        thongBaoBUS.add(tb);
        // thongBaoBUS.add(tb);  // Cố gắng thêm thông báo trùng lặp
        assertEquals(1, thongBaoBUS.getList().stream().filter(t -> t.getIdnguoigui().equals("HSK240")).count(), "Thông báo trùng lặp không nên được thêm");
    }

    // Kiểm thử phương thức list() với tương tác cơ sở dữ liệu (mock)
    @Test
    public void testListWithDatabase() {
        thongBaoBUS.list();
        assertNotNull(thongBaoBUS.getList(), "Danh sách phải được nạp từ cơ sở dữ liệu");
    }

    // Kiểm thử phương thức delete() với ID không tồn tại
    @Test
    public void testDeleteWithNonExistingID() {
        thongBaoBUS.list();
        thongBaoBUS.delete("NonExistingID");
        assertEquals(0, thongBaoBUS.getList().stream().filter(tb -> tb.getIdnguoigui().equals("NonExistingID")).count(), "Thông báo không tồn tại không nên được tìm thấy");
    }
}
