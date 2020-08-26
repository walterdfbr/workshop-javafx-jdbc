/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.ArrayList;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

/**
 *
 * @author c051618
 */
public class SellerService {
    
    private SellerDao dao = DaoFactory.createSellerDao();
    
    public List <Seller> findAll () {
        
        return dao.findAll();
    }
    
    public void saveOrUpdate (Seller obj) {
        if (obj.getId() == null) {
            dao.insert(obj);
        }
        else {
            dao.update(obj);
             }
    }
    
    public void remove (Seller obj) {
        dao.deleteById(obj.getId());
    }
    
}
