package ar.uba.dc.formalex.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * User: P_BENEDETTI
 * Date: 17/12/13
 * Time: 15:56
 */
public class Util {
    private static final Logger logger = Logger.getLogger(Util.class);


    public static  <T> List<T> ordenar(Set<T> set){
        Object[] a = set.toArray();
        Arrays.sort(a, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return new ArrayList(Arrays.asList(a));
    }

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
    
    public static <T> Set<Set<T>> powerSetSet(Set<Set<T>> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<Set<T>> list = new ArrayList<Set<T>>(originalSet);
        Set<T> head = list.get(0);
        Set<Set<T>> rest = new HashSet<Set<T>>(list.subList(1, list.size()));
        for (Set<T> set : powerSetSet(rest)) {
            Set<T> newSet = new HashSet<T>();            
            newSet.addAll(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);            
        }
        return sets;
    }

    /**
     * Lee las propiedades del archivo y las asigna a System.properties
     */
    public static void loadProperties(String pFilename){
        try
        {
            Properties system = System.getProperties();
            File f = new File(pFilename);
            FileInputStream fi = new FileInputStream(f);
            system.load(fi);
            fi.close();
        }catch(IOException e){
            logger.error(e.getMessage(), e);
        }
    }
}
