package hh.harjoitustyo.knitapp.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    @ManyToMany
    @JsonIgnore
    private List<Yarn> yarns = new ArrayList<>();

    @Transient
    private String yarnText;

    // attributes
    @NotBlank(message = "Project name is required")
    private String projectName;
    private String status;
    @NotBlank(message = "Category is required")
    private String category;
    private String size;
    private String pattern;
    private String needles;
    private String gaugeSize;
    private String notes;

    // constructors
    public Project() {
    }

    public Project(String projectName, String status, String category, String size, String pattern, String needles,
            String gaugeSize,
            String notes) {
        this.projectName = projectName;
        this.status = status;
        this.category = category;
        this.size = size;
        this.pattern = pattern;
        this.needles = needles;
        this.gaugeSize = gaugeSize;
        this.notes = notes;
    }

    // getters, setters and toString
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<Yarn> getYarns() {
        return yarns;
    }

    public void setYarns(List<Yarn> yarns) {
        this.yarns = yarns;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getNeedles() {
        return needles;
    }

    public void setNeedles(String needles) {
        this.needles = needles;
    }

    public String getGaugeSize() {
        return gaugeSize;
    }

    public void setGaugeSize(String gaugeSize) {
        this.gaugeSize = gaugeSize;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getYarnText() {
        return yarnText;
    }

    public void setYarnText(String yarnText) {
        this.yarnText = yarnText;
    }

    @Override
    public String toString() {
        return "Project [projectId=" + projectId + ", category=" + category + ", status=" + status
                + ", yarns=" + yarns + ", pattern=" + pattern + ", projectName=" + projectName + ", size=" + size
                + ", needles=" + needles + ", gaugeSize=" + gaugeSize + ", notes=" + notes + "]";
    }

}
