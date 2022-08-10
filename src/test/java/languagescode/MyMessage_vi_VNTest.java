package languagescode;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class MyMessage_vi_VNTest {
    ResourceBundle resourceBundle = ResourceBundle
            .getBundle("languagescode.MyMessage", new Locale("vi", "VN"));

    @Test
    void testOk(){
        assertEquals("OK", resourceBundle.getString("OK"));
    }

    @Test
    void testHelloAdmin() {
        assertEquals("Xin chào Admin", resourceBundle.getString("helloAdmin"));
    }

    @Test
    void testHelloSeller() {
        assertEquals("Xin chào Seller", resourceBundle.getString("helloSeller"));
    }

    @Test
    void testProductNotFound() {
        assertEquals("Sản phẩm không tồn tại",
                resourceBundle.getString("ProductNotFound"));
    }

    @Test
    void testProductIdError() {
        assertEquals("Id sản phẩm phải là số !!!",
                resourceBundle.getString("ProductIdError"));
    }

    @Test
    void testProductNameNull() {
        assertEquals("Tên sản phẩm không được để trống",
                resourceBundle.getString("ProductNameNull"));
    }
}