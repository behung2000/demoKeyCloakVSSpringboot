package com.example.demokeycloakvsspringboot.service;

import com.example.demokeycloakvsspringboot.jpa.Product;
import com.example.demokeycloakvsspringboot.jpa.ProductDTO;
import com.example.demokeycloakvsspringboot.repository.ProductRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static final String KEY_PRODUCT_NOT_FOUND = "ProductNotFound";
    private static final String KEY_PRODUCT_ID_ERROR = "ProductIdError";


    /*
     * Lấy danh sách sản phẩm trong database
     */
    public List<Product> getListProduct(){
        return productRepository.findAll();
    }


    /*
     * Tìm 1 sản phẩm trong database
    */
    public Product getOneProduct(String id){
        try{
            Long idLong = Long.parseLong(id);
            Optional<Product> findProduct = productRepository.findById(idLong);
            if (findProduct.isPresent()) {
                return findProduct.get();
            }
            throw new ProductException(KEY_PRODUCT_NOT_FOUND);
        }
        catch (NumberFormatException e){
            throw new ProductException(KEY_PRODUCT_ID_ERROR);
        }
    }

    /*
     * Kiểm tra tên sản phẩm và giá sản phẩm có hợp lệ không
     */
    public String checkNameVsPrice(ProductDTO product){
        if(product.getName()==null || product.getName().trim().length()==0)
            return "ProductNameNull";
        try{
            int i = Integer.parseInt(product.getPrice());
            if(i<=0)
                return "ProductPriceError";
        }
        catch (NumberFormatException e){
            return "ProductPriceError";
        }
        return "OK";
    }


    /*
     * Tạo 1 sản phẩm mới trong database
     */
    public Product createOneProduct(ProductDTO product){
        String bool = checkNameVsPrice(product);
        if(!bool.equals("OK")) throw new ProductException(bool);
        Product newproduct = productRepository.save(new Product());
        newproduct.setName(product.getName());
        newproduct.setPrice(Integer.parseInt(product.getPrice()));
        return productRepository.save(newproduct);

    }

    /*
     * Cập nhật 1 sản phẩm trong database
     * Note -> tra ve product
     * Note -> Resource get message from resource bundle
     * API docx -> swagger api
     */

    public Product updateOneProduct(String id, ProductDTO product){
        try{
            Long idLong = Long.parseLong(id);
            String bool = checkNameVsPrice(product);
            if(!bool.equals("OK")) throw new ProductException(bool);
            Optional<Product> findProduct = productRepository.findById(idLong);
            if (findProduct.isPresent()) {
                Product upproduct = findProduct.get();
                upproduct.setName(product.getName());
                upproduct.setPrice(Integer.parseInt(product.getPrice()));
                return productRepository.save(upproduct);
            }

            // Nếu không tìm thấy sản phẩm có id thì trả về không tìm được
            throw new ProductException(KEY_PRODUCT_NOT_FOUND);
        }
        catch (NumberFormatException e){

            // Nếu Id hoặc Price không phải là số thì trả về lỗi
            throw new ProductException(KEY_PRODUCT_ID_ERROR);
        }
    }


    /*
     * Xóa 1 sản phẩm trong database note
     * Note -> tra ve http status hoac integer
     * Note -> ResponseEntity -> nen o controller
     */
    public HttpStatus deleteOneProduct(String id){
        try {
            if (productRepository.existsById(Long.valueOf(id))) {
                productRepository.deleteById(Long.valueOf(id));
                return HttpStatus.NO_CONTENT;
            }

            // Nếu không tìm thấy sản phẩm có id thì trả về không tìm được
            throw new ProductException(KEY_PRODUCT_NOT_FOUND);
        }
        catch (NumberFormatException e){

            // Nếu id không phải là số thì trả về lỗi
            throw new ProductException(KEY_PRODUCT_ID_ERROR);
        }
    }
}
