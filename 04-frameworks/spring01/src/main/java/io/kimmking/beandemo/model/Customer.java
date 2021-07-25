package io.kimmking.beandemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String birthday;
    @Autowired
    private Coffee coffee;

    public String getInfo() {
        return "Info: \nid: " + id + " - name: " + name + " - birthday: " + birthday;
    }
}
