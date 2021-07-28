package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Table(name = "comments")
public class Comment extends BaseModel {
    public Comment(long id) {
        super(id);
    }

    public Comment(User user, Branch branch, String content, Date date) {
        this.user = user;
        this.branch = branch;
        this.content = content;
        this.date = date;
    }

    public Comment(long id, User user, Branch branch, String content, Date date) {
        super(id);
        this.user = user;
        this.branch = branch;
        this.content = content;
        this.date = date;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column
    private String content;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Comment() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
