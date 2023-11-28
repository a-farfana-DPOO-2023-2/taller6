package test;

import model.Combo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComboTest {

    private Combo combo = new Combo("combo corral",10.0, Arrays.asList("corral;papas medianas;gaseosa".split(";")));
    @Test
    void getCalorias() {
        assertEquals(850,combo.getCalorias());
    }

    @Test
    void getListCombo() {
        List<String> stringList = new ArrayList<>();
        stringList.add("corral");
        stringList.add("papas medianas");
        stringList.add("gaseosa");
        assertEquals(stringList,combo.getListCombo());
    }

    @Test
    void  generarTextoFactura(){
        assertEquals("combo corral ***** $17550",combo.generarTextoFactura());
    }
}