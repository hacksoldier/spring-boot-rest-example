package com.khoubyari.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DRUG_CLASS", uniqueConstraints =
@UniqueConstraint(name = "UC_DRUG_CLASS_NAME", columnNames = "NAME")
)
public class DrugClass {
    @OneToMany(targetEntity = Drug.class, mappedBy = "drugClass")
    private final List<Drug> drugList = new ArrayList<>();
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_DRUG_CLASS", initialValue = 50000)
    private Long id;
    @Column(name = "NAME", updatable = false, unique = true, nullable = false)
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Drug> getDrugList() {
        return drugList;
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
        return "DrugClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
