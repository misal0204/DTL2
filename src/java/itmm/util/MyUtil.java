/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.util;

/**
 *
 * @author Misael
 */
public class MyUtil {

    public final static String PERSISTENCE_UNIT_NAME_CALIDAD = "DTL2PU_C";
    public final static String PERSISTENCE_UNIT_NAME_PRODUCTIVO = "DTL2PU_P";

    public static String baseUrl() {
        return "http://localhost:7001/DTL2";
    }

    public static String basepathlogin() {
        return "/DTL2/faces/";
    }

    public static String basepathsecured() {
        return "/DTL2/faces/secured/";
    }
}
