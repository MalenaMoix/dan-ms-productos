package jms.dan.productos.repository;

import jms.dan.productos.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ProductRepository {
    private static final List<Product> productsList = new ArrayList<>();
    private static Integer ID_GEN = 1;

    public void createProduct(Product newProduct) {
        newProduct.setId(ID_GEN++);
        productsList.add(newProduct);
    }

    public List<Product> getProducts() {
        return productsList;
    }

    public Product getProductById(Integer id) {
        OptionalInt indexOpt = IntStream.range(0, productsList.size()).filter(i -> productsList.get(i).getId().equals(id)).findFirst();
        if (indexOpt.isPresent()) {
            return productsList.get(indexOpt.getAsInt());
        }
        return null;
    }
}
