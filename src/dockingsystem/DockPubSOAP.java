/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import javax.xml.ws.Endpoint;
import sensorsystem.SensorSystem;

public class DockPubSOAP {
    
    static String onlinehttp = "http://ubuntu4.javabog.dk:5735/sensor"; // Javabog.dk

    static String http = "http://localhost:7777/ws/sensor"; // Localhost
    
    public static void publish(boolean input, SensorSystem _sensorsystem) {
        
        System.out.println("Setting up SOAP API WSDL @:");
        System.out.println(http+"?wsdl");
        
        if(input) Endpoint.publish(onlinehttp, new DockImpSOAP(_sensorsystem));
        
        else Endpoint.publish(http, new DockImpSOAP(_sensorsystem));
        
        System.out.println("WSDL Generated.");
    }
    
}
