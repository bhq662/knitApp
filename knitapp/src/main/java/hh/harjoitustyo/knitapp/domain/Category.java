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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Pattern> patterns = new ArrayList<>();

    // attributes
    private String categoryName;

    // constructors
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {

    }

    // getters, setters and toString
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;

    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", patterns=" + patterns
                + ", categoryName=" + categoryName + "]";
    }

}
