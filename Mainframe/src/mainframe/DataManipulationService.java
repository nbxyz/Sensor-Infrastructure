/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import SQL_forbindelse.*;
import StartLoadSer.*;
import java.rmi.RemoteException;
/**
 *
 * @author nb
 */
public class DataManipulationService extends DB {

    public SecurityBureau cia;

    // Upload Data
    // Request Data
    public SensorCommandCenter nsa;
    public CyberCommunicationCenter nasa;
    private EnumSerialize enum_db;

    public DataManipulationService(EnumSerialize e) throws RemoteException {
        super(e);
        enum_db = e;
        nsa = null;

    }

    public void storeData() throws Exception {
        if(nsa == null) {
            System.out.println("No DB connection");
            return;
        }
         
        String newData = "";
        if (nsa.isThereNewData()) {
            return;
        }
            newData = nsa.getData();
            String[] parts = newData.split(" ");
            try {
                int S_ID = Integer.parseInt(parts[0]);
                int type;
                int unit;

                if (enum_db.enumType_s.get(parts[2]) != null) {
                    type = enum_db.enumType_s.get(parts[2]);
                } else {
                    type = enum_db.setNewType(parts[2]);
                }

                if (enum_db.enumUnit_s.get(parts[3]) != null) {
                    unit = enum_db.enumUnit_s.get(parts[3]);
                } else {
                    unit = enum_db.setNewUnit(parts[3]);
                }

                float value = Float.parseFloat(parts[4]);
                int chs = Integer.parseInt(parts[7]);

                super.insertData(S_ID, parts[1], type, unit, value, (parts[5] + " " + parts[6]), chs);
            } catch (Exception e) {
                System.out.println("Erro String in DataManipulationServicee");
            }
            // 1 LYNGBY TEMPERATURE CELSIUS 23,07 2017-03-13 15:01:26 25
        

    }
    
}
