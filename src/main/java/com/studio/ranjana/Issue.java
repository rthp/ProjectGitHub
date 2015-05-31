package com.studio.ranjana;
import java.util.Date;

public class Issue implements Comparable<Issue> {
    private long number;
    private long id;
    private String state;
    private String title;
    private String body;
    private Date createdAt;
    private Date closedAt;
    private User user;
    private User assignee;

    public Issue() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }    
        if (obj == null) {
            return false;
        }    
        if (getClass() != obj.getClass())  {
            return false;
        }    
        Issue other = (Issue) obj;
        if (id != other.id)  {
            return false;
        }    
        return true;
    }

    @Override
    public String toString() {
        return "Issue [number=" + number + "," + " id=" + id + ","
                + " state=" + state + "," + " title=" + title
                + "," + "body=" + body + ", " + "createdAt="
                + createdAt + ", " +"closedAt=" + closedAt + ", "
                + "user=" + user + ", " + "assignee=" + assignee
                + "]";
    }

    @Override
    public int compareTo(Issue firstObject) {
        Issue secondObject = firstObject;
        if (id == secondObject.id) {
            return 0;
        }
        else if (id > secondObject.id) {
            return 1;
        }
        else {
            return -1;
        }
    } 
    
}