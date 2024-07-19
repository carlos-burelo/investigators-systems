package DB.Tables;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;

import DB.Sqlite;

import DB.Models.Project;

public class Projects {
  String GET_ALL_PROJECTS = "SELECT * FROM projects";
  String ADVANCES_AND_AREAS_BY_PROJECTS = "SELECT advance, area, name FROM projects";
  String GET_PROJECTS_WITH_250K_OR_MORE = "SELECT * FROM projects WHERE financing = 250000 OR financing > 250000";
  String GET_PROJECT_BY_ID = "SELECT * FROM projects WHERE id = ?";
  String INSERT_PROJECT = "INSERT INTO projects (name, area, start, end, duration, advance, termination, financing) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  String UPDATE_PROJECT = "UPDATE projects SET name = ?, area = ?, start = ?, end = ?, duration = ?, advance = ?, termination = ?, financing = ? WHERE id = ?";
  String DELETE_PROJECT = "DELETE FROM projects WHERE id = ?";
  String GET_LAST_PROJECT_INSERTED = "SELECT * FROM projects ORDER BY id DESC LIMIT 1";

  public Project[] getAllProjects() {
    try {
      List<Project> projects = new ArrayList<Project>();
      ResultSet rs = Sqlite.select(GET_ALL_PROJECTS);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String area = rs.getString("area");
        Date start = rs.getDate("start");
        Date end = rs.getDate("end");
        float duration = rs.getFloat("duration");
        float advance = rs.getFloat("advance");
        float termination = rs.getFloat("termination");
        float financing = rs.getFloat("financing");

        Project project = new Project()
            .$name(name)
            .$area(area)
            .$start(start)
            .$end(end)
            .$duration(duration)
            .$advance(advance)
            .$termination(termination)
            .$financing(financing)
            .$id(id);
        projects.add(project);
      }
      return projects.toArray(new Project[projects.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Project[] getProjectsWith250kOrMore() {
    try {
      List<Project> projects = new ArrayList<Project>();
      ResultSet rs = Sqlite.select(GET_PROJECTS_WITH_250K_OR_MORE);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String area = rs.getString("area");
        Date start = rs.getDate("start");
        Date end = rs.getDate("end");
        float duration = rs.getFloat("duration");
        float advance = rs.getFloat("advance");
        float termination = rs.getFloat("termination");
        float financing = rs.getFloat("financing");

        Project project = new Project()
            .$name(name)
            .$area(area)
            .$start(start)
            .$end(end)
            .$duration(duration)
            .$advance(advance)
            .$termination(termination)
            .$financing(financing)
            .$id(id);
        projects.add(project);
      }
      return projects.toArray(new Project[projects.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Project[] getAdvancesAndProjects() {
    try {
      List<Project> projects = new ArrayList<Project>();
      ResultSet rs = Sqlite.select(ADVANCES_AND_AREAS_BY_PROJECTS);
      while (rs.next()) {
        String area = rs.getString("area");
        String name = rs.getString("name");
        float advance = rs.getFloat("advance");

        Project project = new Project()
            .$area(area)
            .$advance(advance)
            .$name(name);
        projects.add(project);
      }
      return projects.toArray(new Project[projects.size()]);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Project getLastProjectInserted() {
    try {
      ResultSet rs = Sqlite.select(GET_LAST_PROJECT_INSERTED);
      if (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String area = rs.getString("area");
        Date start = rs.getDate("start");
        Date end = rs.getDate("end");
        float duration = rs.getFloat("duration");
        float advance = rs.getFloat("advance");
        float termination = rs.getFloat("termination");
        float financing = rs.getFloat("financing");
        Project project = new Project()
            .$name(name)
            .$area(area)
            .$start(start)
            .$end(end)
            .$duration(duration)
            .$advance(advance)
            .$termination(termination)
            .$financing(financing)
            .$id(id);
        return project;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Project getProjectById(int id) {
    try {
      ResultSet rs = Sqlite.select(GET_PROJECT_BY_ID, id);
      if (rs.next()) {
        String name = rs.getString("name");
        String area = rs.getString("area");
        Date start = rs.getDate("start");
        Date end = rs.getDate("end");
        float duration = rs.getFloat("duration");
        float advance = rs.getFloat("advance");
        float termination = rs.getFloat("termination");
        float financing = rs.getFloat("financing");
        Project project = new Project()
            .$name(name)
            .$area(area)
            .$start(start)
            .$end(end)
            .$duration(duration)
            .$advance(advance)
            .$termination(termination)
            .$financing(financing)
            .$id(id);
        return project;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertProject(Project project) {
    try {
      return Sqlite.insert(INSERT_PROJECT,
          project.name,
          project.area,
          project.start,
          project.end,
          project.duration,
          project.advance,
          project.termination,
          project.financing);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int updateProject(Project project) {
    try {
      return Sqlite.update(UPDATE_PROJECT,
          project.name,
          project.area,
          project.start,
          project.end,
          project.duration,
          project.advance,
          project.termination,
          project.financing,
          project.id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int deleteProject(int id) {
    try {
      return Sqlite.delete(DELETE_PROJECT, id);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
}
