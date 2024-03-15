package org.meesho.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class UserPost {
  private String postId; // primary key
  private String username;
  private String postData;


  private LocalDateTime postedOn;
  private Integer likes = 0;
  private Integer shares = 0;
}
