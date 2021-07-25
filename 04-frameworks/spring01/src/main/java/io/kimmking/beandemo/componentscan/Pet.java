package io.kimmking.beandemo.componentscan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data @NoArgsConstructor @AllArgsConstructor @Component
public class Pet {
    private String name = "Pet";

    public String getInfo() {
        return "Lovely " + name;
    }
}
