package DB.Tables;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.Sqlite;
import DB.Models.Scientific;

public final class Scientifics {
  String GET_ALL_SCIENTIFICS = "SELECT * FROM scientifics";
  String GET_SCIENTIFIC_BY_ID = "SELECT * FROM scientifics WHERE id = ?";
  String INSERT_SCIENTIFIC = "INSERT INTO scientifics (name, email, phone, category, grade) VALUES (?, ?, ?, ?, ?)";
  String UPDATE_SCIENTIFIC = "UPDATE scientifics SET name = ?, email = ?, phone = ?, category = ?, grade = ? WHERE id = ?";
  String DELETE_SCIENTIFIC = "DELETE FROM scientifics WHERE id = ?";

  public Scientific[] getAllScientifics() {
    try {
      List<Scientific> scientifics = new ArrayList<Scientific>();
      ResultSet rs = Sqlite.select(GET_ALL_SCIENTIFICS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String category = rs.getString("category");
        String grade = rs.getString("grade");

        Scientific scientific = new Scientific()
            .$name(name)
            .$email(email)
            .$phone(phone)
            .$category(category)
            .$grade(grade)
            .$id(id);
        scientifics.add(scientific);
      }
      return scientifics.toArray(new Scientific[scientifics.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Scientific getScientificById(int id) {
    try {
      ResultSet rs = Sqlite.select(GET_SCIENTIFIC_BY_ID, id);
      if (rs.next()) {
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String category = rs.getString("category");
        String grade = rs.getString("grade");
        Scientific scientific = new Scientific()
            .$name(name)
            .$email(email)
            .$phone(phone)
            .$category(category)
            .$grade(grade)
            .$id(id);
        return scientific;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertScientific(Scientific scientific) {
    try {
      return Sqlite.insert(INSERT_SCIENTIFIC,
          scientific.name,
          scientific.email,
          scientific.phone,
          scientific.category,
          scientific.grade);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int updateScientific(Scientific scientific) {
    try {
      return Sqlite.update(UPDATE_SCIENTIFIC,
          scientific.name,
          scientific.email,
          scientific.phone,
          scientific.category,
          scientific.grade,
          scientific.id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int deleteScientific(int id) {
    try {
      return Sqlite.delete(DELETE_SCIENTIFIC, id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

}
