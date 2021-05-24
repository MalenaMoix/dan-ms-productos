package jms.dan.productos.repository;

import jms.dan.productos.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Repository
public class ProductRepository implements IProductRepository {
    private static final List<Product> productsList = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @Override
    public void createProduct(Product newProduct) {
        newProduct.setId(ID_GEN++);
        productsList.add(newProduct);
    }

    @Override
    public List<Product> getProducts() {
        return productsList;
    }

    @Override
    public Product getProductById(Integer id) {
        OptionalInt indexOpt = IntStream.range(0, productsList.size()).filter(i -> productsList.get(i).getId().equals(id)).findFirst();
        if (indexOpt.isPresent()) {
            return productsList.get(indexOpt.getAsInt());
        }
        return null;
    }
}
