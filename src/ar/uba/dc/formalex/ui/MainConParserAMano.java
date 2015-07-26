package ar.uba.dc.formalex.ui;

import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.grafoDeDependencia.ConstructorDeGrafoFake;
import ar.uba.dc.formalex.parser.ParserAMano;
import ar.uba.dc.formalex.util.Fechas;
import ar.uba.dc.formalex.util.LaAplanadora;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


//clase usada para pruebas
public class MainConParserAMano {

    public static void main(String[] args) throws IOException {
        if (args.length != 1){
            System.out.println("Usage : java CrearAutomata <dataFileName>");
            System.exit(-1);
        }
        new MainConParserAMano(args[0]);
    }

    public MainConParserAMano(String dataFileName) throws IOException {
        File fileData = new File(dataFileName);
        if(!fileData.exists()){
            System.out.println("No existe el archivo de datos: " + dataFileName);
            System.exit(-1);
        }
        FLInput datosAutomata = ParserAMano.getFLInput(fileData);
        LaAplanadora divididos = new LaAplanadora();
        divididos.explotarYAplanar(datosAutomata, new ConstructorDeGrafoFake());

        File out = new File(dataFileName+ "_" + Fechas.getAAAAMMDD_HHMMSS()+ ".txt");
        hacerAutomata(datosAutomata, out)  ;
        System.out.println("Listo. Salida: " + out.getAbsolutePath());
    }

    public void hacerAutomata(FLInput da, File fileOut){
        PrintWriter  writer = null;

        try{
            VelocityContext context = new VelocityContext();
            context.put("acciones", da.getActions());
            context.put("intervalos", da.getIntervals());
            context.put("timers", da.getTimers());
            context.put("agentes", da.getAgentes());
            context.put("contadores", da.getCounters());

            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            Template template = ve.getTemplate( "templates/ej.vm" )  ;

            writer = new PrintWriter (fileOut);
            template.merge( context , writer);
        }
        catch( Exception e ){
            e.printStackTrace();
        }
        finally {
            if ( writer != null){
                try{
                    writer.flush();
                    writer.close();
                }catch( Exception ee ) {
                    ee.printStackTrace();
                }
            }
        }
    }
}
