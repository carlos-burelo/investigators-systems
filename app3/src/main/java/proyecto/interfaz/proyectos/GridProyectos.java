package proyecto.interfaz.proyectos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import proyecto.datos.BaseDeDatos;
import proyecto.datos.Cientificos.Cientifico;
import proyecto.datos.ProyectoAvances.Avance;
import proyecto.datos.Proyectos.Proyecto;
import proyecto.eventos.OnChange;

public class GridProyectos extends JScrollPane implements OnChange<Proyecto> {

  private JPanel panelProyectos;

  public GridProyectos(Proyecto[] proyectos) {
    panelProyectos = new JPanel();
    panelProyectos.setBackground(Color.WHITE);
    panelProyectos.setLayout(new GridLayout(0, 3, 10, 10));
    Arrays.stream(proyectos).map(this::crearTarjetaProyecto).forEach(panelProyectos::add);
    setViewportView(panelProyectos);
    setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  }

  private JPanel crearTarjetaProyecto(Proyecto proyecto) {
    JPanel tarjeta = new JPanel();
    tarjeta.setLayout(new FlowLayout());

    JButton botonEditar = new JButton("📝");
    botonEditar.setPreferredSize(new Dimension(40, 40));
    botonEditar.setAlignmentX(CENTER_ALIGNMENT);
    botonEditar.setBorderPainted(false);
    botonEditar.setFocusPainted(false);
    botonEditar.setContentAreaFilled(false);
    botonEditar.setMargin(new Insets(0, 0, 0, 0));
    botonEditar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
    botonEditar.setForeground(Color.BLUE);
    botonEditar.addActionListener(e -> {

      // checar si ya esta terminado

      new ModalEditarProyecto(null, proyecto, this).mostrar();
    });

    JButton botonEliminar = new JButton("🗑️");
    botonEliminar.setPreferredSize(new Dimension(40, 40));
    botonEliminar.setAlignmentX(CENTER_ALIGNMENT);
    botonEliminar.setMargin(new Insets(0, 0, 0, 0));
    botonEliminar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
    botonEliminar.setForeground(Color.RED);
    botonEliminar.setBorderPainted(false);
    botonEliminar.setFocusPainted(false);
    botonEliminar.setContentAreaFilled(false);
    botonEliminar.addActionListener(e -> {
      String MENSAGE = "¿Eliminar " + proyecto.nombre + "?";
      int respuesta = JOptionPane.showConfirmDialog(null, MENSAGE, "Eliminar proyecto",
          JOptionPane.YES_NO_OPTION);
      if (respuesta == JOptionPane.YES_OPTION) {
        BaseDeDatos db = new BaseDeDatos();
        db.proyectos.eliminar(proyecto.clave);
        onChange(db.proyectos.lista());
      }
      return;
    });

    JButton botonRegistrarAvance = new JButton("📈");
    botonRegistrarAvance.setPreferredSize(new Dimension(40, 40));
    botonRegistrarAvance.setAlignmentX(CENTER_ALIGNMENT);
    botonRegistrarAvance.setMargin(new Insets(0, 0, 0, 0));
    botonRegistrarAvance.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
    botonRegistrarAvance.setForeground(Color.GREEN);
    botonRegistrarAvance.setBorderPainted(false);
    botonRegistrarAvance.setFocusPainted(false);
    botonRegistrarAvance.setContentAreaFilled(false);

    botonRegistrarAvance.addActionListener(e -> {
      if (proyecto.avances_meses >= 100f) {
        JOptionPane.showMessageDialog(null, "Proyecto terminado, ya no se aceptan avances", "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
      new ModalRegistrarAvance(null, proyecto, this).mostrar();
    });

    tarjeta.setForeground(Color.white);
    JLabel labelNombre = new JLabel(proyecto.nombre.toUpperCase());
    labelNombre.setForeground(Color.white);
    labelNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
    labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
    tarjeta.add(labelNombre, BorderLayout.CENTER);
    tarjeta.add(botonEditar, BorderLayout.SOUTH);
    tarjeta.add(botonEliminar, BorderLayout.EAST);
    tarjeta.add(botonRegistrarAvance, BorderLayout.WEST);
    tarjeta.setMaximumSize(new Dimension(100, 100));

    Color COLOR_AZUL = new Color(0, 153, 255);
    Color AZUL_OSCURO = new Color(0, 102, 204);

    tarjeta.setBackground(COLOR_AZUL);

    tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        tarjeta.setBackground(AZUL_OSCURO);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        tarjeta.setBackground(COLOR_AZUL);
      }

      public void mouseClicked(java.awt.event.MouseEvent evt) {

        JFrame ventana = new JFrame();

        ventana.setTitle("Informacion del proyecto");
        ventana.setLayout(new GridLayout(1, 3, 0, 0));

        // JPanel panel = new JPanel();
        // panel.setLayout(new GridLayout(1, 3, 10, 10));

        JPanel panelInfo = new JPanel();
        JPanel panelCientificos = new JPanel();
        panelCientificos.setLayout(new GridLayout(2, 1));
        JPanel panelAvances = new JPanel();

        panelInfo.setLayout(new GridLayout(0, 2, 10, 10));

        panelInfo.add(new JLabel("Clave:"));
        panelInfo.add(new JLabel("" + proyecto.clave));
        panelInfo.add(new JLabel("Nombre:"));
        panelInfo.add(new JLabel(proyecto.nombre));
        panelInfo.add(new JLabel("Area:"));
        panelInfo.add(new JLabel(proyecto.area));
        panelInfo.add(new JLabel("Monto:"));
        panelInfo.add(new JLabel("" + proyecto.monto_financiamiento));
        panelInfo.add(new JLabel("Duracion (En meses):"));
        // redondeo a 1 decimal
        panelInfo.add(new JLabel("" + Math.round(proyecto.duracion_meses * 10.0) / 10.0));
        panelInfo.add(new JLabel("Avance (%):"));
        // redondeo a 1 decimal
        panelInfo.add(new JLabel("" + Math.round(proyecto.avances_meses * 10.0) / 10.0));
        panelInfo.add(new JLabel("Terminacion (meses):"));
        // redondeo a 1 decimal
        panelInfo.add(new JLabel("" + Math.round(proyecto.terminacion_meses * 10.0) / 10.0));
        panelInfo.add(new JLabel("Fecha de inicio:"));
        panelInfo.add(new JLabel("" + proyecto.fecha_inicio.toString()));
        panelInfo.add(new JLabel("Fecha de fin:"));
        panelInfo.add(new JLabel("" + proyecto.fecha_fin.toString()));

        BaseDeDatos db = new BaseDeDatos();

        Cientifico[] cientificos = db.asignacionCientificosProyectos.obtenerCientificosProyecto(proyecto.clave);

        JScrollPane scrollPane = new JScrollPane();
        panelCientificos.add(new JLabel("Cientificos:"));
        JList<String> list = new JList<String>();
        String[] nombresCientificos = new String[cientificos.length];
        for (int i = 0; i < cientificos.length; i++) {
          nombresCientificos[i] = cientificos[i].nombre;
        }
        list.setListData(nombresCientificos);
        scrollPane.setViewportView(list);
        panelCientificos.add(scrollPane);

        panelAvances.setLayout(new GridLayout(0, 2, 10, 10));

        Avance[] avances = db.avances.lista(proyecto.clave);

        for (int i = 0; i < avances.length; i++) {
          panelAvances.add(new JLabel(avances[i].fecha.toString()));
          panelAvances.add(new JLabel(avances[i].descripcion));
        }

        ventana.setSize(700, 500);
        ventana.setVisible(true);

        ventana.add(panelInfo);
        ventana.add(panelCientificos);
        ventana.add(panelAvances);
      }
    });

    tarjeta.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(java.awt.event.MouseEvent evt) {
        tarjeta.setBackground(COLOR_AZUL);
      }
    });

    return tarjeta;
  }

  @Override
  public void onChange(Proyecto[] proyectos) {
    BaseDeDatos db = new BaseDeDatos();
    proyectos = db.proyectos.lista();

    panelProyectos.removeAll();
    Arrays.stream(proyectos).map(this::crearTarjetaProyecto).forEach(panelProyectos::add);
    revalidate();
    repaint();
  }

}
