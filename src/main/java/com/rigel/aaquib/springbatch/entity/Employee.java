package com.rigel.aaquib.springbatch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Employee {

    @Id
    private Integer id;
    private String name;
    private String dept;
    private Integer ctc;
    private Date time;

    public Employee(Integer id, String name, String dept, Integer ctc, Date time) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.ctc=ctc;
        this.time = time;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

  

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dept='").append(dept).append('\'');
        sb.append(", ctc=").append(ctc);
        sb.append('}');
        return sb.toString();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

	public Integer getCtc() {
		return ctc;
	}

	public void setCtc(Integer ctc) {
		this.ctc = ctc;
	}
}
