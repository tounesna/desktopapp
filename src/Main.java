/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samar
 */
import entites.Hotel;
import entites.nbetoile;
import java.sql.*;
import services.HotelService;
public class Main {

    public static void main(String[] args) {
        Hotel h = new Hotel("belazur",nbetoile.etoile_4,"hgt","photos",100,1);
        HotelService hs = new HotelService();
        hs.addHotel(h);
        //hs.updateHotel(h);
        //hs.DeleteHotel(2);
    }

}
