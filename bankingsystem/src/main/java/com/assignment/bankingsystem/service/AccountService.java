/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.bankingsystem.service;

import com.assignment.bankingsystem.exception.ValidationException;
import com.assignment.bankingsystem.model.Account;
import com.assignment.bankingsystem.response.Reciept;

/**
 *
 * @author admin
 */
public interface AccountService {
    
    public Account balance(String accno) throws ValidationException;
    
    public void updateBalance(String accno,double amount) throws ValidationException;
    
    public Reciept deposit(String accno,double amount) throws ValidationException;
    
    public Reciept withdrawal(String accno,double amount) throws ValidationException;
    
    
}
