package DB.Models;

import java.sql.Date;

public class Project {
  public int id;
  public String name;
  public String area;
  public Date start;
  public Date end;
  public float duration;
  public float advance;
  public float termination;
  public float financing;

  public Project $name(String name) {
    this.name = name;
    return this;
  }

  public Project $area(String area) {
    this.area = area;
    return this;
  }

  public Project $start(Date start) {
    this.start = start;
    return this;
  }

  public Project $end(Date end) {
    this.end = end;
    return this;
  }

  public Project $duration(float duration) {
    this.duration = duration;
    return this;
  }

  public Project $advance(float advance) {
    this.advance = advance;
    return this;
  }

  public Project $termination(float termination) {
    this.termination = termination;
    return this;
  }

  public Project $financing(float financing) {
    this.financing = financing;
    return this;
  }

  public Project $id(int id) {
    this.id = id;
    return this;
  }

}
