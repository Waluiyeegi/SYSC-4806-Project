import static org.junit.jupiter.api.Assertions.*;

import com.example.model.Product;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    public void productTest() {

        String name = "Test Name";

        Product product = new Product();

        product.setName(name);

        assertEquals(product.getName(),name);
    }
}
