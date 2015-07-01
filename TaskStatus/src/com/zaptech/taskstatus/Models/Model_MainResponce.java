package com.zaptech.taskstatus.Models;

public class Model_MainResponce {
	String datetime;
	Model_location location;
	Model_alarm alarm;
	Model_battery battery;
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Model_location getLocation() {
		return location;
	}
	public void setLocation(Model_location location) {
		this.location = location;
	}
	public Model_alarm getAlarm() {
		return alarm;
	}
	public void setAlarm(Model_alarm alarm) {
		this.alarm = alarm;
	}
	public Model_battery getBattery() {
		return battery;
	}
	public void setBattery(Model_battery battery) {
		this.battery = battery;
	}
}
