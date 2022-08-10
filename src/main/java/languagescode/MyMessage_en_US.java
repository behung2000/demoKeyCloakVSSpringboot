package languagescode;

import java.util.ListResourceBundle;

public class MyMessage_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contexts;
    }

    private static final Object[][] contexts = {
            //
            {"OK", "OK"},

            //Hello controller
            {"hello", "Hello"},
            {"hello.Admin", "Hello Admin"},
            {"hello.Seller", "Hello Seller"},

            //Product service
            {"ProductNotFound", "Product not found"},
            {"ProductIdError", "Id product has to be a number !!!"},
            {"ProductNameNull", "Name is required"},
            {"ProductPriceError", "Price is required or <= 0 or price not a number"},

    };
}
