/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astro;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Chart {
	private String firstName, lastName;
	private String birthCity, birthState, birthCountry;
	//private double longitude, latitude;
	private int birthDay,birthYear, birthMonth, birthDate, birthHour, birthMinute, birthSecond, lon_deg, lon_min, lon_sec, lat_deg, lat_min, lat_sec;
	private double timeZoneOffset;
	private int dstOffset, sex;
//	private boolean isNorth, isEast;//These have yet to be implemented
	private String id;

    public Chart() {
    }

    
}
