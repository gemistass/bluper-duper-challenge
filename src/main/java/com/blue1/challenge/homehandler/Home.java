package com.blue1.challenge.homehandler;

//import blue.enums.Type;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Validated
@Document(collection = "homes")
@Data
@NoArgsConstructor
public class Home {

    @Id
    private String homeId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Valid
    @NotBlank
    @Pattern(regexp="(NURSING|PRIVATE)$",message="Invalid type. Accepted values:NURSING,PRIVATE")
    private String type;



    public Home(String name, String type) {

        this.name = name;
        this.type = type;
    }



    @Override
    public String toString() {
        return "Home{" +
                "homeId=" + homeId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}