package com.yurifont.forum_hub.domain.topic;

import com.yurifont.forum_hub.domain.course.Course;
import com.yurifont.forum_hub.domain.reply.Reply;
import com.yurifont.forum_hub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    public Topic(String title, String message, User user, Course course) {
        this.title = title;
        this.message = message;
        this.user = user;
        this.course = course;
    }
}
