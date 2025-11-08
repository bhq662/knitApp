package hh.harjoitustyo.knitapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    // attributes
    private String statusName;

    // constructors
    public Status(String statusName) {
        this.statusName = statusName;
    }

    public Status() {

    }

    // getters, setters and toString
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Status [statusId=" + statusId + ", statusName=" + statusName + "]";
    }

}
