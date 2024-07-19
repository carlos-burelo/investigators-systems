package DB.Models;

import java.sql.Date;

public final class Advance {
  public int id;
  public int project_id;
  public String description;
  public Date date;

  public Advance $project_id(int project_id) {
    this.project_id = project_id;
    return this;
  }

  public Advance $description(String description) {
    this.description = description;
    return this;
  }

  public Advance $date(Date date) {
    this.date = date;
    return this;
  }

  public Advance $id(int id) {
    this.id = id;
    return this;
  }
}
