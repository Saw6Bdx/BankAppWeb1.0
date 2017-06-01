/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.manager;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Address;
import model.Holder;
import model.Postcode;

@Stateless
public class HolderManager {
    
    @PersistenceContext(unitName = "BankAppPU")
    private EntityManager em;
    
    @Lock(LockType.WRITE)
    public void createUser(Holder holder, Address address, Postcode postcode) {
        
        System.out.println("createUser(HolderManager)");
        // Check if the postcode and the city already exist in the database
        this.em.persist(holder);
        this.em.persist(address);
        this.em.persist(postcode);
                
    }
    
}
