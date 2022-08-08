/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.bankingsystem;

import com.assignment.bankingsystem.exception.ValidationException;
import com.assignment.bankingsystem.repository.AccountRepository;
import com.assignment.bankingsystem.response.Reciept;
import com.assignment.bankingsystem.service.AccountImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
public class AccountSerivceTest {
    
    @Autowired 
    AccountImpl acc;
    
    @Test
    public void testDeposit(){
        
        
        try {
            
            Reciept r = acc.deposit("acc1", -100);
        
        Assertions.assertThat(r.getBalance()).isGreaterThan(100);
        
        }catch(ValidationException ve){
            ve.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    @Test
    public void testWithdrawal(){
        
        
        try {
            
            Reciept r = acc.withdrawal("acc2", 150);
        
        Assertions.assertThat(r.getBalance()).isGreaterThan(100);
        
        }catch(ValidationException ve){
            ve.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    @Test
    public void testDep_withdrawal(){
        
        /*
        add acc
        */
        
        AccountRepository.accmap.put("acc1", new Double(0));
        AccountRepository.accmap.put("acc2", new Double(0));
        AccountRepository.accmap.put("acc3", new Double(0));
        
        
        AccountImpl a = new AccountImpl();
        try {
            
            int x=0;
            while(x<5){
            Reciept r1 = a.deposit("acc4", 100.00);
            
//            System.out.println(r1.getAccno()+":"+r1.getBalance());
            
            if(x>2){
                Reciept r2 = a.withdrawal("acc1", 140.00);
            
//            System.out.println("##@ "+r2.getAccno()+":"+r2.getBalance());
            }
            x++;
            Assertions.assertThat(r1.getBalance()).isGreaterThan(0);
            }
            
        
        }catch(ValidationException ve){
            ve.printStackTrace();
        
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
