package com.example.info3;

public class Point {
	public double longitude = 0f;
	public double latitude = 0f;
	public String description;
	public float x, y = 0;
	public double distance;
	public String address;
	public String opening_hours;
	public String newDesc = "";
	public String type = " ";
	public String icon = " ";

	public Point(double lat, double lon, String desc, String address, String opening_hours, String type, String icon) {
		this.icon = icon;
		this.type = type;
		this.latitude = lat;
		this.longitude = lon;
		this.description = desc;
		this.address = address;
		if(opening_hours == "false"){
			this.opening_hours = "ne";
		}else if(opening_hours == "true"){
			this.opening_hours = "taip";
		}else{
			this.opening_hours = "-";
		}
		int counter = 0;
		for (String retval: description.split(" ")) {
			if(counter == 2) {
				newDesc = newDesc + retval + "\n";
			}else{
				newDesc = newDesc + retval + " ";
			}
			counter++;
		}

	}
	public String getInfo(){
		return "PAVADINIMAS: " + /*description*/newDesc + " \nATSTUMAS: " + distance + "km \nADRESAS: " + address /*+ "\nATIDARYTA: " + opening_hours*/;
	}public String getInfo1(){
		return "PAVADINIMAS: " + /*description*/newDesc + " \nATSTUMAS: " + distance + "km \nADRESAS: " + address + "\nATIDARYTA: " + opening_hours;
	}
}
