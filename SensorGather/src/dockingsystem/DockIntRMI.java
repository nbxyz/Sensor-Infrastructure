/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

//import mainframe.*;

/**
 *
 * @author nb
 */
public interface DockIntRMI extends java.rmi.Remote {
    
    boolean transferData(String eUsername, String ePassword, String eData, int count) throws java.rmi.RemoteException, Exception;
    boolean requestConnection() throws java.rmi.RemoteException;
    String getNonsense() throws java.rmi.RemoteException;
    String getPublicKey() throws java.rmi.RemoteException;
    void sendCipherInonsense(byte[] cipher) throws java.rmi.RemoteException;
    void sendLogHashCipher(byte[] hashLog )throws java.rmi.RemoteException;
    boolean recieveOK() throws java.rmi.RemoteException;
    
    //boolean isThereNewData() throws java.rmi.RemoteException;
    //public String getData() throws java.rmi.RemoteException;

}