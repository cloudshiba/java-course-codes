package io.kimmking.beandemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Coffee {
    private String name;

    public String getInfo() {
        return "Have a nice " + name;
    }
}
