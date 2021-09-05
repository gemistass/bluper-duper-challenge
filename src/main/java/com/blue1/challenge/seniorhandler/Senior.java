package com.blue1.challenge.seniorhandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class Senior {


    @Id
    private String seniorId;


    @NotBlank
    private String homeId;
    private boolean enabled = false;
    private String sensorId;
    @NotBlank
    private String name;


    public Senior(String homeId, boolean enabled, String sensorId, String name) {
        this.homeId = homeId;
        this.enabled = enabled;
        this.sensorId = sensorId;
        this.name = name;
    }

    public Senior(String homeId, boolean enabled, String name) {
        this.homeId = homeId;
        this.enabled = enabled;
        this.name = name;
    }

    public Senior(String homeId, String name) {
        this.homeId = homeId;
        this.enabled = false;
        this.name = name;
    }

    public Senior(String homeId, String name, String sensorId) {
        this.homeId = homeId;
        this.enabled = false;
        this.name = name;
        this.sensorId = sensorId;
    }


    public String getSeniorId() {
        return seniorId;
    }

    public void setSeniorId(String seniorId) {
        this.seniorId = seniorId;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Senior{" +
                "seniorId='" + seniorId + '\'' +
                ", homeId='" + homeId + '\'' +
                ", enabled=" + enabled +
                ", sensorId='" + sensorId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

