package com.cloudshiba.jdbcplay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class User {
   private long id;
   private String name;

   public static User newRecord(long id, String name) {
      return new User(id, name);
   }
}
