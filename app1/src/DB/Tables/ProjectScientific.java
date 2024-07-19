package DB.Tables;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.Sqlite;
import DB.Models.Scientific;

public class ProjectScientific {

  String CHECK_RELATED_SCIENTIFIC = "SELECT 1 FROM project_scientific WHERE project_id = ? AND scientific_id = ?";
  String ASSIGN_SCIENTIFIC_TO_PROJECT = "INSERT INTO project_scientific (project_id, scientific_id) VALUES (?, ?)";
  String REMOVE_SCIENTIFIC_FROM_PROJECT = "DELETE FROM project_scientific WHERE project_id = ? AND scientific_id = ?";
  String GET_SCIENTIFIC_BY_PROJECT_ID = "SELECT * FROM scientifics INNER JOIN project_scientific ON scientifics.id = project_scientific.scientific_id WHERE project_scientific.project_id = ?";
  String GET_SCIENTIFIC_BY_PROJECTS = "SELECT scientifics.name AS scientific_name, projects.name AS project_name FROM scientifics INNER JOIN project_scientific ON scientifics.id = project_scientific.scientific_id INNER JOIN projects ON project_scientific.project_id = projects.id";
  String GET_SCIENTIFICS_WITH_18_MONTHS = "SELECT s.id, s.name FROM scientifics s JOIN project_scientific ps ON s.id = ps.scientific_id JOIN projects p ON ps.project_id = p.id WHERE (p.end - p.start) >= 18";
  String GET_ALL_SCIENTIFIC_AND_SELECT_BY_PROJECT_ID = "SELECT s.id, s.name, s.phone, s.email, s.grade, s.category, IFNULL((SELECT 1 FROM project_scientific ps WHERE ps.scientific_id = s.id AND ps.project_id = ?), 0) AS selected FROM scientifics s";

  public Scientific[] getAllScientificAndSelectByProjectId(int projectId) {
    try {
      List<Scientific> scientifics = new ArrayList<Scientific>();
      ResultSet rs = Sqlite.select(GET_ALL_SCIENTIFIC_AND_SELECT_BY_PROJECT_ID, projectId);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String grade = rs.getString("grade");
        String category = rs.getString("category");
        boolean selected = rs.getBoolean("selected");
        Scientific scientific = new Scientific()
            .$name(name)
            .$id(id)
            .$phone(phone)
            .$email(email)
            .$grade(grade)
            .$category(category)
            .$selected(selected);
        scientifics.add(scientific);
      }
      return scientifics.toArray(new Scientific[scientifics.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void assignScientificToProject(int projectId, int scientificId) {
    try {
      ResultSet rs = Sqlite.select(CHECK_RELATED_SCIENTIFIC, projectId, scientificId);
      if (!rs.next()) {
        Sqlite.insert(ASSIGN_SCIENTIFIC_TO_PROJECT, projectId, scientificId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void removeScientificFromProject(int projectId, int scientificId) {
    try {
      Sqlite.delete(REMOVE_SCIENTIFIC_FROM_PROJECT, projectId, scientificId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static class ScientificProject {
    public String scientific_name;
    public String project_name;

    public ScientificProject(String scientific_name, String project_name) {
      this.scientific_name = scientific_name;
      this.project_name = project_name;
    }
  }

  public ScientificProject[] getScientificsByProyects() {
    try {
      List<ScientificProject> scientifics = new ArrayList<ScientificProject>();
      ResultSet rs = Sqlite.select(GET_SCIENTIFIC_BY_PROJECTS);
      while (rs.next()) {
        String scientific_name = rs.getString("scientific_name");
        String project_name = rs.getString("project_name");
        ScientificProject scientific = new ScientificProject(scientific_name, project_name);
        scientifics.add(scientific);
      }
      return scientifics.toArray(new ScientificProject[scientifics.size()]);
    } catch (Exception e) {
      return null;
    }
  }

  public Scientific[] getScientificByProjectId(int projectId) {
    try {
      List<Scientific> scientifics = new ArrayList<Scientific>();
      ResultSet rs = Sqlite.select(GET_SCIENTIFIC_BY_PROJECT_ID, projectId);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Scientific scientific = new Scientific()
            .$name(name)
            .$id(id);
        scientifics.add(scientific);
      }
      return scientifics.toArray(new Scientific[scientifics.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Scientific[] getScientificWith18MonthsWorking() {
    try {
      List<Scientific> scientifics = new ArrayList<Scientific>();
      ResultSet rs = Sqlite.select(GET_SCIENTIFICS_WITH_18_MONTHS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Scientific scientific = new Scientific()
            .$name(name)
            .$id(id);
        scientifics.add(scientific);
      }
      return scientifics.toArray(new Scientific[scientifics.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
