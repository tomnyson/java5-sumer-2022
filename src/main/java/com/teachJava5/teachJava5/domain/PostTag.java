package com.teachJava5.teachJava5.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "post_tags")
public class PostTag {
  @EmbeddedId
  PostTagkey id;

  @ManyToOne
  @MapsId("tagId")
  @JoinColumn(name = "tag_id")
  Tag tag;

  @ManyToOne
  @MapsId("postId")
  @JoinColumn(name = "post_id")
  Post post;
  Boolean status;

}
