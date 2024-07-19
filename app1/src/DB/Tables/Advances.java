package DB.Tables;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.ResultSet;

import DB.Sqlite;
import DB.Models.Advance;

public class Advances {
  String GET_ADVANCES_BY_PROJECT_ID = "SELECT * FROM advances WHERE project_id = ?";
  String INSERT_ADVANCE = "INSERT INTO advances (project_id, date, description) VALUES (?, ?, ?)";
  String GET_LAST_ADVANCE_BY_PROJECT_ID = "SELECT * FROM advances WHERE project_id = ? ORDER BY id DESC LIMIT 1";

  public Advance[] getAdvancesByProjectId(int projectId) {
    try {
      List<Advance> advances = new ArrayList<Advance>();
      ResultSet rs = Sqlite.select(GET_ADVANCES_BY_PROJECT_ID, projectId);
      while (rs.next()) {
        int id = rs.getInt("id");
        int project_id = rs.getInt("project_id");
        Date date = rs.getDate("date");
        String description = rs.getString("description");
        Advance advance = new Advance()
            .$project_id(project_id)
            .$date(date)
            .$id(id)
            .$description(description);
        advances.add(advance);
      }
      return advances.toArray(new Advance[advances.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Advance getLastAdvanceByProjectId(int projectId) {
    try {
      ResultSet rs = Sqlite.select(GET_LAST_ADVANCE_BY_PROJECT_ID, projectId);
      if (rs.next()) {
        int id = rs.getInt("id");
        int project_id = rs.getInt("project_id");
        Date date = rs.getDate("date");
        String description = rs.getString("description");
        Advance advance = new Advance()
            .$project_id(project_id)
            .$date(date)
            .$id(id)
            .$description(description);
        return advance;
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public boolean insertAdvance(Advance advance) {
    try {
      Sqlite.insert(INSERT_ADVANCE, advance.project_id, advance.date, advance.description);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
