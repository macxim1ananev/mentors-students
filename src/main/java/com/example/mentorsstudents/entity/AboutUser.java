package com.example.mentorsstudents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "about_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AboutUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long aboutUserId;

    @Column(name = "text_about_user")
    private String textAboutUser;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AboutUser)) return false;
        AboutUser aboutUser = (AboutUser) o;
        return textAboutUser.equals(aboutUser.textAboutUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textAboutUser);
    }

    @Override
    public String toString() {
        return "AboutUser{" +
                "aboutUserId=" + aboutUserId +
                ", textAboutUser='" + textAboutUser + '\'' +
                '}';
    }
}
