package com.example.mentorsstudents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @ManyToMany(mappedBy = "subjects")
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjectName.equals(subject.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
