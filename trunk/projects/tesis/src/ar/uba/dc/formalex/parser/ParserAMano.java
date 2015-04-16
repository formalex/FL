package ar.uba.dc.formalex.parser;



import ar.uba.dc.formalex.fl.FLInput;
import ar.uba.dc.formalex.fl.bgtheory.*;
import ar.uba.dc.formalex.fl.bgtheory.Timer;
import ar.uba.dc.formalex.util.UtilFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * User: P_BENEDETTI
 * Date: 20/11/13
 * Time: 12:23
 */
public class ParserAMano {

    private static Map<String, Action> acciones = new HashMap<String, Action>();


    public static FLInput getFLInput(File fileData) throws IOException {
        FLInput res = new FLInput();
        acciones.clear();

        List<String> apl = UtilFile.getArchivoPorLinea(fileData);

        for (String s : apl) {
            if (esComentario(s))
                ;//ignoro comentarios
            else if (s.trim().toLowerCase().startsWith("action "))
                res.add(parsearAccion(s.trim()));
            //todo falta actions
            else if (s.trim().toLowerCase().startsWith("interval "))
                res.add(parsearIntervalo(s.trim()));
            else if (s.trim().toLowerCase().startsWith("timer "))
                res.add(parsearTimer(s.trim()));
            else if (s.trim().toLowerCase().startsWith("roles "))
                res.add(parsearRoles(s.trim()));
            else if (s.trim().toLowerCase().startsWith("local counter ")|
                    s.trim().toLowerCase().startsWith("global counter "))
                res.add(parsearCounter(s.trim()));
        }
        return res;
    }

    private static boolean esComentario(String s) {
        s = s.trim();
        if (s.isEmpty())
            return true;
        return s.startsWith("////") ||s.startsWith("##") ||s.startsWith("--");
    }


    /**
     * En caso de que la acci�n est� sincronizada con otra, ac� se modifican ambas para relacionarlas.
     *
     * s puede ser de la forma:
     * action Enroll output values {jeOut1,jeOut2} performable by john
     * action Enroll output values {jeOut1,jeOut2} occurrences 17 performable by john
     * action Enroll output values {jeOut1,jeOut2} synchronizes with comprar performable by john
     * action Enroll output values {jeOut1,jeOut2} only occurs in scope enVida performable by john
     * action vender synchronizes with comprar disallow autosync performable by vendedor
     * action Graduate
     * action Graduate performable by john, x, y
     * action Graduate only occurs in scope enVida performable by john
     */
    public static Action parsearAccion(String s) {
        try {
            Action res = new Action();
            res.setOutputValues(getOutputValues(s));
            res.setName(getActionName(s));
            res.setOccursIn(getOnlyOccursIn(s));
            res.setOccurrences(getOccurrences(s));
            res.setPerformableBy(getPerformableBy(s));
            Action sync = getSync(s);

            if(sync != null){
                res.setAllowAutoSync(isAllowAutoSync(s));
                res.setSync(sync, true);
                sync.setSync(res, false);
            }

            acciones.put(res.getName(), res);
            return res;
        } catch (RuntimeException e) {
            System.out.println("Error al parsear la l�nea: " + s);
            throw e;
        }
    }

    // Ej: action vender synchronizes with comprar disallow autosync performable by vendedor
    private static boolean isAllowAutoSync(String s) {
        s = s.toLowerCase();
        if (s.contains(" autosync")){
            if (s.contains(" disallow "))
                return false;
            else if (s.contains(" allow "))
                return true;
        }
        return Action.ALLOW_AUTO_SYNC_DEFAULT;
    }

    private static Set<Role> getPerformableBy(String s) {
        int i = s.indexOf("performable by");
        if (i < 0)
            return null;
        String x = s.substring(i+ "performable by".length()+1);
        StringTokenizer st = new StringTokenizer(x, ",", false);
        Set<Role> roles = new HashSet<Role>();
        while (st.hasMoreElements()) {
            roles.add(new Role(st.nextToken()));
        }
        return roles;
    }

    private static Action getSync(String s) {
        int i = s.indexOf("synchronizes with");
        if (i < 0)
            return null;
        String x = s.substring(i+ "synchronizes with".length()+1).trim();
        int h = x.indexOf(" ");
        String actName;
        if (h > 0)
            actName = x.substring(0, h);
        else
            actName = x;

        Action action = acciones.get(actName);
        if (action ==  null){
            System.out.println("Error en la l�nea: ");
            System.out.println(s);
            System.out.println("Se quiere sincronizar con una acci�n no definida");
        }

        return action;
    }

    /**
     * s puede ser de la forma:
     * action Enroll output values {jeOut1,jeOut2} performable by john
     * action Enroll output values {jeOut1,jeOut2} only occurs in scope enVida performable by john
     * action Graduate
     * action Graduate performable by john, x, y
     * action Graduate only occurs in scope enVida performable by john
     */
    private static Interval getOnlyOccursIn(String s) {
        String aBuscar = "only occurs in scope ";
        if (s.contains(aBuscar)){
            int i = s.indexOf(aBuscar);
            int d = i + aBuscar.length();
            int h = s.indexOf(" ", d+1);
            if (h == -1)
                h = s.length();
            Interval interval = new Interval();
            interval.setName(s.substring(d, h).trim()); //ojo, es copia del intervalo
            return interval;
        }
        else
            return null;
    }

    /**
     * Funciona para intervalos y acciones
     * s puede ser de la forma:
     * action Enroll output values {jeOut1,jeOut2} occurrences 3 performable by john
     * action Enroll output values {jeOut1,jeOut2} performable by john
     * action Enroll output values {jeOut1,jeOut2} only occurs in scope enVida performable by john
     * action Graduate
     * action Graduate performable by john, x, y
     * action Graduate only occurs in scope enVida performable by john
     */
    private static int getOccurrences(String s) {
        String aBuscar = " occurrences ";
        if (s.contains(aBuscar)){
            int i = s.indexOf(aBuscar);
            int d = i + aBuscar.length();
            int h = s.indexOf(" ", d+1);
            if (h == -1)
                h = s.length();
            String res = (s.substring(d, h).trim());
            return Integer.parseInt(res);
        }
        else
            return 0;
    }

    /**
     * s puede ser de la forma:
     * action Enroll output values {jeOut1,jeOut2} performable by john
     * action Enroll output values {jeOut1,jeOut2} only occurs in scope enVida performable by john
     * action Graduate
     * action Graduate performable by john, x, y
     * action Graduate only occurs in scope enVida performable by john
     */
    private static String getActionName(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", false);
        st.nextToken();//ignoro 'action'
        return st.nextToken();
    }

    /**
     * s puede ser de la forma:
     * action Enroll output values {jeOut1,jeOut2} performable by john
     * action Graduate
     * action Graduate performable by john, x, y
     */
    private static Set<String> getOutputValues(String s) {
        if (!s.toLowerCase().contains("output values"))
            return null;

        Set<String> res = new HashSet<String>();
        int d = s.indexOf("{");
        int h = s.indexOf("}");
        String x = s.substring(d+1, h);
        StringTokenizer st = new StringTokenizer(x, ",", false);
        while (st.hasMoreElements()) {
            res.add(st.nextToken());
        }
        return res;
    }

    /**
     *puede ser de la forma:
     * timer T1, T2, T3
     */
    public static Timer parsearTimer(String s) {
        try {
            Timer res = new Timer();

            String x = s.substring(s.toLowerCase().indexOf("timer ") + "timer ".length()).trim();//x =  T1, T2, T3
            StringTokenizer st = new StringTokenizer(x, ",", false);
            while (st.hasMoreElements()) {
                res.addEvento(st.nextToken().trim());
            }

            return res;
        } catch (RuntimeException e) {
            System.out.println("Error al parsear la l�nea: " + s);
            throw e;
        }
    }

    /**
     *puede ser de la forma:
     * roles john
     * roles john, paul
     * roles john, paul disjoint, cover todo: no implementado
     */
    public static RoleSpecification parsearRoles(String s) {
        try {
            RoleSpecification res = new RoleSpecification();
            res.setDisjoint (s.contains("disjoint"));
            res.setCover (s.contains("cover"));
            s = s.replaceFirst("disjoint", "");
            s = s.replaceFirst("cover", "");
            s = s.trim().substring("roles ".length());
            StringTokenizer st = new StringTokenizer(s, ",", false);
            while (st.hasMoreElements()) {
                res.addRole(new Role(st.nextToken().trim()));
            }

            return res;
        } catch (RuntimeException e) {
            System.out.println("Error al parsear la l�nea: " + s);
            throw e;
        }
    }

    /**
     La entrada es de la forma:
     (local|global) counter > id
     (init value 17)?
     (< increases with action > actionId ( < by > < NUMBER >)? ( < provided that > raw_text )? ,)*
     (< decreases with action > actionId ( < by > < NUMBER >)? ( < provided that > raw_text )? ,)*
     (< resets with action > actionId ( < provided that > raw_text )? )*
     */
    public static Counter parsearCounter(String s) {
        Counter res = new Counter();
        res.setLocal (s.startsWith("local "));
        String x = s.substring(s.indexOf("counter ")+"counter ".length()).trim();
        res.setName(x.substring(0, x.indexOf(" ")));
        x = x.substring(x.indexOf(" ")+1).trim();
        String y = "init value ";
        if (x.startsWith(y)){
            x = x.substring(y.length()).trim();
            int h = x.indexOf(" ");
            if (h < 0)
                h = x.length();
            res.setInitValue(Integer.parseInt(x.substring(0, h).trim()));
            x = x.substring(h).trim();
        }

        x = x.replaceAll(",", " ");

        y = "increases with action ";
        while (x.startsWith(y)) {
            x = x.substring(x.indexOf(y)+y.length()).trim();
            int h = x.indexOf(" ");
            if (h<0)
                h = x.length();
            String action = x.substring(0, h);
            int v = 1;
            String providedThat = null;
            x = x.substring(action.length()).trim();
            if (x.startsWith("by ")){
                x = x.substring("by".length()).trim();
                h = x.indexOf(" ");
                if (h<0)
                    h = x.length();
                v = Integer.parseInt(x.substring(0, h));
                h = x.indexOf(" ");
                if (h<0)
                    h = x.length();
                x = x.substring(h).trim();
            }
            if (x.startsWith("provided that (")){
                x = x.substring("provided that %".length()).trim();
                h = x.indexOf("%");
                if (h < 0)
                    h = x.length();
                providedThat  = x.substring(0, h);
                h = x.indexOf(" ");
                if (h < 0)
                    h = x.length();
                x = x.substring(h).trim();
            }
            res.addIncreaseAction(getAction(action), v, providedThat);
        }

        y = "decreases with action ";
        while (x.startsWith(y)) {
            x = x.substring(x.indexOf(y)+y.length()).trim();
            int h = x.indexOf(" ");
            if (h<0)
                h = x.length();
            String action = x.substring(0, h);
            int v = 1;
            String providedThat = null;
            x = x.substring(action.length()).trim();
            if (x.startsWith("by ")){
                x = x.substring("by".length()).trim();
                h = x.indexOf(" ");
                if (h<0)
                    h = x.length();
                v = Integer.parseInt(x.substring(0, h));
                h = x.indexOf(" ");
                if (h<0)
                    h = x.length();
                x = x.substring(h).trim();
            }
            if (x.startsWith("provided that (")){
                x = x.substring("provided that (".length()).trim();
                h = x.indexOf(")");
                if (h < 0)
                    h = x.length();
                providedThat  = x.substring(0, h);
                h = x.indexOf(" ");
                if (h < 0)
                    h = x.length();
                x = x.substring(h).trim();
            }
            res.addDecreaseAction(getAction(action), v, providedThat);
        }
        y = "resets with action ";
        while (x.startsWith(y)) {
            x = x.substring(x.indexOf(y)+y.length()).trim();
            int h = x.indexOf(" ");
            if (h<0)
                h = x.length();
            String action = x.substring(0, h);
            String providedThat = null;
            x = x.substring(action.length()).trim();

            if (x.startsWith("provided that (")){
                x = x.substring("provided that (".length()).trim();
                h = x.indexOf(")");
                if (h < 0)
                    h = x.length();
                providedThat  = x.substring(0, h);
                h = x.indexOf(" ");
                if (h < 0)
                    h = x.length();
                x = x.substring(h).trim();
            }
            res.addResetAction(getAction(action), providedThat);
        }
        y = "sets with action ";
        while (x.startsWith(y)) {
            x = x.substring(x.indexOf(y)+y.length()).trim();
            int h = x.indexOf(" ");
            if (h<0)
                h = x.length();
            String action = x.substring(0, h);
            x = x.substring(action.length()).trim();

            x = x.substring("to value".length()).trim();
            h = x.indexOf(" ");
            if (h < 0)
                h = x.length();
            int v  = Integer.parseInt(x.substring(0, h));
            h = x.indexOf(" ");
            if (h < 0)
                h = x.length();
            x = x.substring(h).trim();

            String providedThat = null;
            if (x.startsWith("provided that (")){
                x = x.substring("provided that (".length()).trim();
                h = x.indexOf(")");
                if (h < 0)
                    h = x.length();
                providedThat  = x.substring(0, h);
                h = x.indexOf(" ");
                if (h < 0)
                    h = x.length();
                x = x.substring(h).trim();
            }
            res.addSetValueAction(getAction(action), v, providedThat);
        }
        return res;
    }


        /**
        *puede ser de la forma:
        * interval Student defined by actions Enroll-Graduate
        * interval Student defined by actions Enroll-Graduate global
        * interval Student defined by actions Enroll-Graduate local
        * interval Student defined by actions Enroll, Enroll2, Enroll-Graduate, Die, GiveUp
        * interval Student defined by actions Enroll, Enroll2, Enroll-Graduate, Die, GiveUp only occurs in scope enVida
        */
    public static Interval parsearIntervalo(String s) {
        try {
            Interval res = new Interval();
            String x = s.substring("interval".length() + 1 );//elimino 'interval '
            String n = x.substring(0, x.indexOf(" "));//nombre del intervalo
            res.setName(n);

            //defino si es local o global
            if (x.toLowerCase().contains(" global")){
                res.setLocal(false);
                x = x.replace(" global", "");
            }
            else if (x.toLowerCase().contains(" local")){
                res.setLocal(true);
                x = x.replace(" local", "");
            }
            else
                res.setLocal(Interval.IS_LOCAL_DEFAULT);

            res.setOccurrences(getOccurrences(s));
            if (res.getOccurrences()>0){
                x = x.replace("occurrences " + res.getOccurrences(), "");
            }

            Interval onlyOccursIn = getOnlyOccursIn(x);
            if (onlyOccursIn != null){
                x = x.substring(0, x.indexOf("only occur") );
                res.setOccursIn(onlyOccursIn);
            }
            x = x.substring(n.length()+1).trim();   //x = defined by actions Enroll, Enroll2, Enroll-Graduate, Die, GiveUp
            x = x.substring(x.indexOf("actions") + "actions".length()).trim();//x = Enroll, Enroll2, Enroll-Graduate, Die, GiveUp
            StringTokenizer st = new StringTokenizer(x, "-", false);
            String accDesde = st.nextToken(); //pueden ser una o varias
            StringTokenizer st2 = new StringTokenizer(accDesde, ",", false);
            while (st2.hasMoreElements()) {
                res.addStartTrigger(getAction(st2.nextToken().trim()));
            }
            String accHasta = st.nextToken();
            st2 = new StringTokenizer(accHasta, ",", false);
            while (st2.hasMoreElements()) {
                res.addEndTrigger(getAction(st2.nextToken().trim()));
            }

            return res;
        } catch (RuntimeException e) {
            System.out.println("Error al parsear la l�nea: " + s);
            throw e;
        }
    }

    private static Action getAction(String accion) {
        Action action = acciones.get(accion);
        if (action == null)
            throw new RuntimeException("Se est� haciendo referencia a una acci�n que no existe: " + accion);
        return action;
    }
}
