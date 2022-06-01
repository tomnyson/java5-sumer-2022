package com.teachJava5.teachJava5.domain;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class PostTagkey implements Serializable {
  @Column(name = "tag_id")
  Long tagId;
  @Column(name = "post_id")
  Long postId;
}
