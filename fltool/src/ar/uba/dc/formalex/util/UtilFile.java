package ar.uba.dc.formalex.util;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"JavaDoc"})
public class UtilFile {

    public static List<String> getArchivoPorLinea(File f) throws IOException {
        return getArchivoPorLinea(f, null);
    }

    public static List<String> getArchivoPorLinea(File f, String excluirSiEmpiezaCon,
                                                  boolean excluirLineasVacias) throws IOException {
        return getArchivoPorLinea(f, Integer.MAX_VALUE, excluirSiEmpiezaCon, excluirLineasVacias);
    }

    public static List<String> getArchivoPorLinea(File f, String excluirSiEmpiezaCon) throws IOException {
        if (!f.exists()){
            String s = "No existe el archivo = '" + f.getAbsolutePath() + "'";
            JOptionPane.showMessageDialog(null, s);
            System.out.println(s);
            throw new IOException("No existe el archivo = '" + f.getAbsolutePath() + "'");
        }
        return getArchivoPorLinea(f, excluirSiEmpiezaCon, false);

    }


    public static List<String> getArchivoPorLinea(File f, int cantMaxLineas,
                                                  String excluirSiEmpiezaCon,
                                                  boolean excluirLineasVacias)
			throws IOException{
        LineNumberReader lnr = null;
        int cont = 0;
        try{
            List<String> res = new ArrayList<String>();
            lnr = new LineNumberReader(new FileReader(f));
            while(lnr.ready() && cont <cantMaxLineas){
                String linea = lnr.readLine();
                if (excluirSiEmpiezaCon != null && linea.startsWith(excluirSiEmpiezaCon))
                    ;
                else if (excluirLineasVacias && linea.trim().equals(""))
                    ;
                else
                    res.add(linea);
                cont++;
            }
            return res;
        }
        finally{
            if (lnr != null)
                lnr.close();
        }
    }


    public static void guardar(File file, String contenido, boolean append) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, append);
            fos.write(contenido.getBytes());
            fos.close();
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally{
            if (fos != null)
                fos.close();
        }
    }
}
