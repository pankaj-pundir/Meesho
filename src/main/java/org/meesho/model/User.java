package org.meesho.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class User {
  private String username; // primary key
  private String fullName;
}
