/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author root
 */

import classes.ServicoEmprestimos;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("webservice")
public class ApplicationConfig extends Application {

	//The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    
    public Set<Class<?>> getClasses() {
    	Set<Class<?>> resources = new HashSet<>();
    	addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources){
    	//Adicione os recursos disponibilizados usando a chamada
    	//a resources
    	resources.add(classes.ServicoEmprestimos.class);
    }
}