package test;
import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoMenuTest {

    private ProductoMenu base = new ProductoMenu("corral",14000,500);


    @Test
    void getCalorias() {
        assertEquals(500,base.getCalorias());
    }

    @Test
    void generarTextoFactura() {
        assertEquals("corral ***** $14000",base.generarTextoFactura());
    }
}