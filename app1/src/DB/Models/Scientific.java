package DB.Models;

public class Scientific {
  public int id;
  public String name;
  public boolean selected;
  public String email;
  public String phone;
  public String category;
  public String grade;

  public Scientific $name(String name) {
    this.name = name;
    return this;
  }

  public Scientific $selected(boolean selected) {
    this.selected = selected;
    return this;
  }

  public Scientific $email(String email) {
    this.email = email;
    return this;
  }

  public Scientific $phone(String phone) {
    this.phone = phone;
    return this;
  }

  public Scientific $category(String category) {
    this.category = category;
    return this;
  }

  public Scientific $grade(String grade) {
    this.grade = grade;
    return this;
  }

  public Scientific $id(int id) {
    this.id = id;
    return this;
  }

  public Scientific $selected(int selected) {
    this.selected = selected == 1;
    return this;
  }

}
