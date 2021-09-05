package com.blue1.challenge.sensorHandler;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Data
@Document(collection = "sensors")
public class Sensor {

  @Id private String sensorId;
  @NotBlank
  private String hardwareVersion;
  @NotBlank
  private String softwareVersion;

  public Sensor(String hardwareVersion, String softwareVersion){
    this.hardwareVersion = hardwareVersion;
    this.softwareVersion = softwareVersion;
  }
  public String getSensorId() {
    return sensorId;
  }


  @Override
  public String toString() {
    return "Sensor{" +
            "sensorId=" + sensorId +
            ", hardwareVersion='" + hardwareVersion + '\'' +
            ", softwareVersion='" + softwareVersion + '\'' +
            '}';
  }
}