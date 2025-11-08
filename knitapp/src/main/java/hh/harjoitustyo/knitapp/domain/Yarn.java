package hh.harjoitustyo.knitapp.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Yarn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long yarnId;

    @ManyToMany(mappedBy = "yarns")
    @JsonIgnore

    private List<Project> projects = new ArrayList<>();

    // attributes
    @NotBlank(message = "Yarn name is required")
    private String yarnName;
    private String yarnWeight;
    private String yarnColour;
    private String needleRec;
    private String gaugeRec;

    // constructors
    public Yarn(String yarnName, String yarnWeight, String yarnColour, String needleRec, String gaugeRec) {
        this.yarnName = yarnName;
        this.yarnWeight = yarnWeight;
        this.yarnColour = yarnColour;
        this.needleRec = needleRec;
        this.gaugeRec = gaugeRec;
    }

    public Yarn() {
    }

    // getters, setters and toString
    public Long getYarnId() {
        return yarnId;
    }

    public void setYarnId(Long yarnId) {
        this.yarnId = yarnId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getYarnWeight() {
        return yarnWeight;
    }

    public void setYarnWeight(String yarnWeight) {
        this.yarnWeight = yarnWeight;
    }

    public String getYarnName() {
        return yarnName;
    }

    public void setYarnName(String yarnName) {
        this.yarnName = yarnName;
    }

    public String getYarnColour() {
        return yarnColour;
    }

    public void setYarnColour(String yarnColour) {
        this.yarnColour = yarnColour;
    }

    public String getNeedleRec() {
        return needleRec;
    }

    public void setNeedleRec(String needleRec) {
        this.needleRec = needleRec;
    }

    public String getGaugeRec() {
        return gaugeRec;
    }

    public void setGaugeRec(String gaugeRec) {
        this.gaugeRec = gaugeRec;
    }

    @Override
    public String toString() {
        return "Yarn [yarnId=" + yarnId + ", projects=" + projects + ", yarnName="
                + yarnName + ", yarnWeight=" + yarnWeight + ", yarnColour=" + yarnColour + ", needleRec=" + needleRec
                + ", gaugeRec=" + gaugeRec + "]";
    }

}
