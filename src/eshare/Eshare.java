/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eshare;

import iziShare.ShareSystem;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Elwin
 */
public class Eshare {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ShareSystem ezShare = new ShareSystem("C:/", 10, 1024);
    }
}
