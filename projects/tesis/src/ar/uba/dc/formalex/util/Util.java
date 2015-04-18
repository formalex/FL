package ar.uba.dc.formalex.util;

import org.apache.log4j.Logger;

import ar.uba.dc.formalex.fl.bgtheory.Role;
import ar.uba.dc.formalex.fl.bgtheory.RolesCombination;

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
    
    public static RolesCombination powerSetSet(RolesCombination originalSet) {
    	RolesCombination sets = new RolesCombination();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<Role>());
            return sets;
        }
        List<HashSet<Role>> list = new ArrayList<HashSet<Role>>(originalSet);
        HashSet<Role> head = list.get(0);
        HashSet<HashSet<Role>> rest = new HashSet<HashSet<Role>>(list.subList(1, list.size()));
        for (HashSet<Role> set : powerSetSet((RolesCombination)rest)) {
        	HashSet<Role> newSet = new HashSet<Role>();            
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
