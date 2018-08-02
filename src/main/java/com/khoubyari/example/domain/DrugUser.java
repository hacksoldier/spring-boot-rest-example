package com.khoubyari.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DRUG_USER")
public class DrugUser {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_DRUG_USER", initialValue = 50000)
    private Long id;

    @ManyToOne(targetEntity = Drug.class)
    @JoinColumn(name = "DRUG_ID", updatable = false, nullable = false,
            foreignKey = @ForeignKey(name = "FK_DRUG_USER_TO_DRUG", value = ConstraintMode.CONSTRAINT))
    private Drug drug;

    @ManyToOne(targetEntity = Drug.class)
    @JoinColumn(name = "USER_ID", updatable = false, nullable = false,
            foreignKey = @ForeignKey(name = "FK_DRUG_USER_TO_USER", value = ConstraintMode.CONSTRAINT))
    private User user;

    @Lob
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "DEADLINE")
    private LocalDateTime deadline;

    @Column(name = "CREATED", updatable = false)
    private LocalDateTime created;

    @Column(name = "MODIFIED")
    private LocalDateTime modified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "DrugUser{" +
                "id=" + id +
                ", notes='" + notes + '\'' +
                ", deadline=" + deadline +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
