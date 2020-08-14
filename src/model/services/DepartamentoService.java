/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.ArrayList;
import java.util.List;
import model.entities.Departamento;

/**
 *
 * @author c051618
 */
public class DepartamentoService {
    
    public List <Departamento> findAll () {
        List<Departamento> list = new ArrayList<>();
        list.add(new Departamento(1, "Books"));
        list.add(new Departamento(2, "Computers"));
        list.add(new Departamento(3, "Electronics"));
        
        return list;
    }
    
}
