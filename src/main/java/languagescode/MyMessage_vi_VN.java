package languagescode;

import java.util.ListResourceBundle;

public class MyMessage_vi_VN extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contexts;
    }

    private static final Object[][] contexts = {
            //
            {"OK", "OK"},

            //Hello controller
            {"hello", "Xin chào"},
            {"helloAdmin", "Xin chào Admin"},
            {"helloSeller", "Xin chào Seller"},

            //Product service
            {"ProductNotFound", "Sản phẩm không tồn tại"},
            {"ProductIdError", "Id sản phẩm phải là số !!!"},
            {"ProductNameNull", "Tên sản phẩm không được để trống"},
            {"ProductPriceError", "Giá sản phẩm không được để trống hoặc <= 0 hoặc không phải là số"},

    };
}
