package hh.harjoitustyo.knitapp.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patternId;

    @ManyToMany
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    // attributes
    private String patternLink;

    // constructors
    public Pattern(String patternLink) {
        this.patternLink = patternLink;
    }

    public Pattern() {
    }

    // getters, setters and toString
    public Long getPatternId() {
        return patternId;
    }

    public void setPatternId(Long patternId) {
        this.patternId = patternId;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getPatternLink() {
        return patternLink;
    }

    public void setPatternLink(String patternLink) {
        this.patternLink = patternLink;
    }

    @Override
    public String toString() {
        return "Pattern [patternId=" + patternId + ", categories=" + categories
                + ", patternLink=" + patternLink + "]";
    }

}
