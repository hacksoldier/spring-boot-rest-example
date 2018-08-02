package com.khoubyari.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DRUGS", uniqueConstraints = {
        @UniqueConstraint(name = "UC_DRUGS_AIC", columnNames = "AIC"),
        @UniqueConstraint(name = "UC_DRUGS_ATC", columnNames = "ATC")}
)
public class Drug {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_DRUGS", initialValue = 50000)
    private Long id;

    @Column(name = "AIC", length = 10, updatable = false, unique = true, nullable = false)
    private String aic;

    @Column(name = "ATC", updatable = false, unique = true)
    private String atc;

    @ManyToOne(targetEntity = ActiveSubstance.class, optional = false)
    @JoinColumn(name = "ACTIVE_SUBSTANCE_ID", foreignKey = @ForeignKey(name = "FK_DRUGS_TO_ACTIVE_SUBSTANCE", value = ConstraintMode.CONSTRAINT))
    private ActiveSubstance activeSubstance;

    @ManyToOne(targetEntity = DrugGroup.class, optional = false)
    @JoinColumn(name = "DRUG_GROUP_ID", foreignKey = @ForeignKey(name = "FK_DRUGS_TO_DRUG_GROUP", value = ConstraintMode.CONSTRAINT))
    private DrugGroup group;

    @ManyToOne(targetEntity = DrugGroup.class, optional = false)
    @JoinColumn(name = "DRUG_CLASS_ID", foreignKey = @ForeignKey(name = "FK_DRUGS_TO_DRUG_CLASS", value = ConstraintMode.CONSTRAINT))
    private DrugClass drugClass;

    @ManyToOne(targetEntity = DrugGroup.class, optional = false)
    @JoinColumn(name = "DRUG_PRODUCER_ID", foreignKey = @ForeignKey(name = "FK_DRUGS_TO_PRODUCER", value = ConstraintMode.CONSTRAINT))
    private DrugProducer producer;

    @Column(name = "PRESCRIPTION")
    private String prescription;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "STORAGE")
    private String storage; //Conservazione

    @Column(name = "GLUTEN_FREE")
    private Boolean glutenFree;

    @Column(name = "LACTOSE_FREE")
    private Boolean lactoseFree;

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

    public String getAic() {
        return aic;
    }

    public void setAic(String aic) {
        this.aic = aic;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public ActiveSubstance getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(ActiveSubstance activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public DrugGroup getGroup() {
        return group;
    }

    public void setGroup(DrugGroup group) {
        this.group = group;
    }

    public DrugClass getDrugClass() {
        return drugClass;
    }

    public void setDrugClass(DrugClass drugClass) {
        this.drugClass = drugClass;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public DrugProducer getProducer() {
        return producer;
    }

    public void setProducer(DrugProducer producer) {
        this.producer = producer;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Boolean getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public Boolean getLactoseFree() {
        return lactoseFree;
    }

    public void setLactoseFree(Boolean lactoseFree) {
        this.lactoseFree = lactoseFree;
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
        return "Drug{" +
                "id=" + id +
                ", aic='" + aic + '\'' +
                ", atc='" + atc + '\'' +
                ", prescription='" + prescription + '\'' +
                ", ssn='" + ssn + '\'' +
                ", storage='" + storage + '\'' +
                ", glutenFree=" + glutenFree +
                ", lactoseFree=" + lactoseFree +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
