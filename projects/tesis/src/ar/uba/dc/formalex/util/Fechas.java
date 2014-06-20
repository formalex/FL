package ar.uba.dc.formalex.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class Fechas {

    public static String getAAAAMMDD_HHMMSS(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sd.format(new Date());
    }

}