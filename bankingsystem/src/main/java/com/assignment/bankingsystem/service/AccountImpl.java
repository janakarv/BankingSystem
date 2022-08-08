/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.bankingsystem.service;

import com.assignment.bankingsystem.config.CurrencyVarList;
import com.assignment.bankingsystem.exception.ValidationException;
import com.assignment.bankingsystem.model.Account;
import com.assignment.bankingsystem.repository.AccountRepository;
import com.assignment.bankingsystem.response.Reciept;

/**
 *
 * @author admin
 */
public class AccountImpl implements AccountService{

    @Override
    public Reciept deposit(String accno,double amount) throws ValidationException {
        
        Reciept r = new Reciept();
        
        Account ac = balance(accno);
        
        double bal = ac.getBalance()+amount;
        
        /*
        update db
        */
        updateBalance(accno, bal);
        
        r.setRescode("0");
        r.setMsg("success");
        r.setAccno(accno);
        r.setDepamount(amount);
        r.setBalance(bal);
        
        return r;
    }

    @Override
    public Reciept withdrawal(String accno,double amount) throws ValidationException{
       
        
        Reciept r = new Reciept();
        
        Account ac = balance(accno);
        
        if(amount <= ac.getBalance()){
        double bal = ac.getBalance()-amount;
        /*
        update db
        */
        updateBalance(accno, bal);
        
        r.setRescode("0");
        r.setMsg("success");
        r.setAccno(accno);
        r.setDepamount(amount);
        r.setBalance(bal);
        
        }
        else{
            throw new ValidationException(" Insufficient Balance ");
        }
        
        
        return r;
        
    }

    @Override
    public Account balance(String accno) throws ValidationException{
        
        Account acc = new Account();
        
        if(!AccountRepository.accmap.containsKey(accno)){
            throw new ValidationException(" Account Not Found ");
        }
        acc.setAccno(accno);
        acc.setCurency(CurrencyVarList.LKR);
        acc.setBalance(AccountRepository.accmap.get(accno));
        
        return acc;
        
    }

    @Override
    public void updateBalance(String accno, double amount) throws ValidationException{
        
        AccountRepository.accmap.put(accno, amount);   

    }
    
    public void validateInput(String accno, double amount) throws ValidationException{
        
        if(amount <= 0){
            
            throw new ValidationException("Invalid Amount");
        }
        /*
        # TO DO Account Number validation ; format , lenghth
        */
        
        
    }
    
    public static void main(String[] args) {
        
        
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
            
            System.out.println(r1.getAccno()+":"+r1.getBalance());
            
            if(x>2){
                Reciept r2 = a.withdrawal("acc1", 140.00);
            
            System.out.println("##@ "+r2.getAccno()+":"+r2.getBalance());
            }
            x++;
            }
        
        }catch(ValidationException ve){
            ve.printStackTrace();
        
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }
    
}
