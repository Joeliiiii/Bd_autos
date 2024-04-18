
package control;



 
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.Scanner;
 
public class Control {

    public static void menuAgregar(Connection conn, Scanner scanner) {

        try {

            System.out.println("\n--- Agregar ---");

            System.out.println("1. Diseñador");

            System.out.println("2. Marca");

            System.out.println("3. Auto");

            System.out.print("Selecciona una tabla: ");

            int opcionAgregar = scanner.nextInt();

            scanner.nextLine();
 
            switch (opcionAgregar) {

                case 1:

                    agregarDiseñador(conn, scanner);

                    break;

                case 2:

                    agregarMarca(conn, scanner);

                    break;

                case 3:

                    agregarAuto(conn, scanner);

                    break;

                default:

                    System.out.println("Opción inválida.");

                    break;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
 
    public static void menuModificar(Connection conn, Scanner scanner) {

        try {

            System.out.println("\n--- Modificar ---");

            System.out.println("1. Diseñador");

            System.out.println("2. Marca");

            System.out.println("3. Auto");

            System.out.print("Selecciona una tabla: ");

            int opcionModificar = scanner.nextInt();

            scanner.nextLine();
 
            switch (opcionModificar) {

                case 1:

                    modificarDiseñador(conn, scanner);

                    break;

                case 2:

                    modificarMarca(conn, scanner);

                    break;

                case 3:

                    modificarAuto(conn, scanner);

                    break;

                default:

                    System.out.println("Opción inválida.");

                    break;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
 
    public static void menuEliminar(Connection conn, Scanner scanner) {

        try {

            System.out.println("\n--- Eliminar ---");

            System.out.println("1. Diseñador");

            System.out.println("2. Marca");

            System.out.println("3. Auto");

            System.out.print("Selecciona una tabla: ");

            int opcionEliminar = scanner.nextInt();

            scanner.nextLine();
 
            switch (opcionEliminar) {

                case 1:

                    eliminarDiseñador(conn, scanner);

                    break;

                case 2:

                    eliminarMarca(conn, scanner);

                    break;

                case 3:

                    eliminarAuto(conn, scanner);

                    break;

                default:

                    System.out.println("Opción inválida.");

                    break;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
 
    public static void menuConsultar(Statement stmt, Scanner scanner) {

        try {

            System.out.println("\n--- Consultar ---");

            System.out.println("1. Diseñadores");

            System.out.println("2. Marcas");

            System.out.println("3. Autos");

            System.out.print("Selecciona una tabla: ");

            int opcionConsultar = scanner.nextInt();

            scanner.nextLine();
 
            switch (opcionConsultar) {

                case 1:

                    consultarDiseñadores(stmt);

                    break;

                case 2:

                    consultarMarcas(stmt);

                    break;

                case 3:

                    consultarAutos(stmt);

                    break;

                default:

                    System.out.println("Opción inválida.");

                    break;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
 
    static void agregarDiseñador(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("Nombre del diseñador: ");

    String nombre = scanner.nextLine().trim();

    if (!nombre.isEmpty()) {

        nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();

    } else {

        System.out.println("Nombre no válido. Inténtalo de nuevo.");

        return;

    }

    System.out.print("Apellido paterno del diseñador: ");

    String apellidoPaterno = scanner.nextLine().trim();

    if (!apellidoPaterno.isEmpty()) {

        apellidoPaterno = apellidoPaterno.substring(0, 1).toUpperCase() + apellidoPaterno.substring(1).toLowerCase();

    } else {

        System.out.println("Apellido paterno no válido. Inténtalo de nuevo.");

        return;

    }

    System.out.print("Apellido materno del diseñador: ");

    String apellidoMaterno = scanner.nextLine().trim();

    if (!apellidoMaterno.isEmpty()) {

        apellidoMaterno = apellidoMaterno.substring(0, 1).toUpperCase() + apellidoMaterno.substring(1).toLowerCase();

    } else {

        System.out.println("Apellido materno no válido. Inténtalo de nuevo.");

        return;

    }

    System.out.print("Nacionalidad del diseñador: ");

    String nacionalidad = scanner.nextLine().trim();

    if (!nacionalidad.isEmpty()) {

        nacionalidad = nacionalidad.substring(0, 1).toUpperCase() + nacionalidad.substring(1).toLowerCase();

    } else {

        System.out.println("Nacionalidad no válida. Inténtalo de nuevo.");

        return;

    }

    System.out.print("Sexo del diseñador (M/F): ");

    String sexo = scanner.nextLine().trim().toUpperCase();

    if (!sexo.equals("M") && !sexo.equals("F")) {

        System.out.println("Sexo no válido. Inténtalo de nuevo.");

        return;

    }

    System.out.print("Fecha de nacimiento del diseñador (YYYY-MM-DD): ");

    String fechaNacimiento = scanner.nextLine().trim();

    String sql = "INSERT INTO diseñador (nombre, apellido_paterno, apellido_materno, nacionalidad, sexo, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?)";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, nombre);

    statement.setString(2, apellidoPaterno);

    statement.setString(3, apellidoMaterno);

    statement.setString(4, nacionalidad);

    statement.setString(5, sexo);

    statement.setString(6, fechaNacimiento);

    int rowsAffected = statement.executeUpdate();

    if (rowsAffected > 0) {

        System.out.println("Diseñador agregado correctamente.");

    } else {

        System.out.println("No se pudo agregar el diseñador.");

    }

}
 
 
    static void agregarMarca(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("Nombre de la marca: ");

    String nombre = scanner.nextLine().trim();

    System.out.print("Año de la marca: ");

    int año = 0;

    boolean añoValido = false;

    while (!añoValido) {

        try {

            año = Integer.parseInt(scanner.nextLine().trim());

            añoValido = true;

        } catch (NumberFormatException e) {

            System.out.println("Año no válido. Inténtalo de nuevo.");

        }

    }
 
    int idDiseñador = 0;

    boolean idValido = false;

    while (!idValido) {

        try {

            System.out.print("ID del diseñador: ");

            idDiseñador = Integer.parseInt(scanner.nextLine().trim());

            if (idDiseñador > 0) {

                idValido = true;

            } else {

                throw new Exception("ID de diseñador no válido.");

            }

        } catch (NumberFormatException e) {

            System.out.println("ID no válido. Inténtalo de nuevo.");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
 
    System.out.print("Precio de la marca: ");

    double precio = 0;

    boolean precioValido = false;

    while (!precioValido) {

        try {

            precio = Double.parseDouble(scanner.nextLine().trim());

            precioValido = true;

        } catch (NumberFormatException e) {

            System.out.println("Precio no válido. Inténtalo de nuevo.");

        }

    }
 
    System.out.print("Combustible de la marca: ");

    String combustible = scanner.nextLine().trim();

    String sql = "INSERT INTO marca (nombre, año, diseñador, precio, combustible) VALUES (?, ?, ?, ?, ?)";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, nombre);

    statement.setInt(2, año);

    statement.setInt(3, idDiseñador);

    statement.setDouble(4, precio);

    statement.setString(5, combustible);

    statement.executeUpdate();

    System.out.println("Marca agregada correctamente.");

}
 
static void agregarAuto(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("Placas del auto: ");

    String placas = scanner.nextLine();

    System.out.print("ID de la marca: ");

    int idMarca = scanner.nextInt();

    scanner.nextLine();

    System.out.print("NIV del auto: ");

    String niv = scanner.nextLine();

    System.out.print("Número de llantas del auto: ");

    int llantas = scanner.nextInt();

    System.out.print("Número de asientos del auto: ");

    int asientos = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Color del auto: ");

    String color = scanner.nextLine();

    System.out.print("Tipo de auto: ");

    String tipoAuto = scanner.nextLine();

    System.out.print("Número de puertas del auto: ");

    int noPuertas = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Transmisión del auto: ");

    String transmision = scanner.nextLine();

    System.out.print("Número de cilindros del auto: ");

    int noCilindros = scanner.nextInt();

    String sql = "INSERT INTO auto (placas, marca, niv, llantas, asientos, color, tipo_auto, no_puertas, transmision, no_cilindros) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, placas);

    statement.setInt(2, idMarca);

    statement.setString(3, niv);

    statement.setInt(4, llantas);

    statement.setInt(5, asientos);

    statement.setString(6, color);

    statement.setString(7, tipoAuto);

    statement.setInt(8, noPuertas);

    statement.setString(9, transmision);

    statement.setInt(10, noCilindros);

    statement.executeUpdate();

    System.out.println("Auto agregado correctamente.");

}
 
    static void modificarDiseñador(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("ID del diseñador a modificar: ");

    int id = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Nuevo nombre del diseñador: ");

    String nuevoNombre = scanner.nextLine();

    System.out.print("Nuevo apellido paterno del diseñador: ");

    String nuevoApellidoPaterno = scanner.nextLine();

    System.out.print("Nuevo apellido materno del diseñador: ");

    String nuevoApellidoMaterno = scanner.nextLine();

    System.out.print("Nueva nacionalidad del diseñador: ");

    String nuevaNacionalidad = scanner.nextLine();

    System.out.print("Nuevo sexo del diseñador (M/F): ");

    String nuevoSexo = scanner.nextLine();

    System.out.print("Nueva fecha de nacimiento del diseñador (YYYY-MM-DD): ");

    String nuevaFechaNacimiento = scanner.nextLine();

    String sql = "UPDATE diseñador SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, nacionalidad = ?, sexo = ?, fecha_nacimiento = ? WHERE id_diseñador = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, nuevoNombre);

    statement.setString(2, nuevoApellidoPaterno);

    statement.setString(3, nuevoApellidoMaterno);

    statement.setString(4, nuevaNacionalidad);

    statement.setString(5, nuevoSexo);

    statement.setString(6, nuevaFechaNacimiento);

    statement.setInt(7, id);

    statement.executeUpdate();

    System.out.println("Diseñador modificado correctamente.");

}
 
static void modificarMarca(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("ID de la marca a modificar: ");

    int id = scanner.nextInt();

    scanner.nextLine(); // Limpiar el buffer de entrada

    System.out.print("Nuevo nombre de la marca: ");

    String nuevoNombre = scanner.nextLine();

    System.out.print("Nuevo año de la marca: ");

    int nuevoAño = scanner.nextInt();

    System.out.print("Nuevo ID del diseñador: ");

    int nuevoIdDiseñador = scanner.nextInt();

    System.out.print("Nuevo precio de la marca: ");

    double nuevoPrecio = scanner.nextDouble();

    scanner.nextLine();

    System.out.print("Nuevo combustible de la marca: ");

    String nuevoCombustible = scanner.nextLine();

    String sql = "UPDATE marca SET nombre = ?, año = ?, diseñador = ?, precio = ?, combustible = ? WHERE id_modelo = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, nuevoNombre);

    statement.setInt(2, nuevoAño);

    statement.setInt(3, nuevoIdDiseñador);

    statement.setDouble(4, nuevoPrecio);

    statement.setString(5, nuevoCombustible);

    statement.setInt(6, id);

    statement.executeUpdate();

    System.out.println("Marca modificada correctamente.");

}
 
static void modificarAuto(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("Placas del auto a modificar: ");

    String placas = scanner.nextLine();

    System.out.print("Nuevo ID de la marca: ");

    int nuevoIdMarca = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Nuevo NIV del auto: ");

    String nuevoNiv = scanner.nextLine();

    System.out.print("Nuevo número de llantas del auto: ");

    int nuevoLlantas = scanner.nextInt();

    System.out.print("Nuevo número de asientos del auto: ");

    int nuevoAsientos = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Nuevo color del auto: ");

    String nuevoColor = scanner.nextLine();

    System.out.print("Nuevo tipo de auto: ");

    String nuevoTipoAuto = scanner.nextLine();

    System.out.print("Nuevo número de puertas del auto: ");

    int nuevoNoPuertas = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Nueva transmisión del auto: ");

    String nuevaTransmision = scanner.nextLine();

    System.out.print("Nuevo número de cilindros del auto: ");

    int nuevoNoCilindros = scanner.nextInt();

    String sql = "UPDATE auto SET marca = ?, niv = ?, llantas = ?, asientos = ?, color = ?, tipo_auto = ?, no_puertas = ?, transmision = ?, no_cilindros = ? WHERE placas = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, nuevoIdMarca);

    statement.setString(2, nuevoNiv);

    statement.setInt(3, nuevoLlantas);

    statement.setInt(4, nuevoAsientos);

    statement.setString(5, nuevoColor);

    statement.setString(6, nuevoTipoAuto);

    statement.setInt(7, nuevoNoPuertas);

    statement.setString(8, nuevaTransmision);

    statement.setInt(9, nuevoNoCilindros);

    statement.setString(10, placas);

    statement.executeUpdate();

    System.out.println("Auto modificado correctamente.");

}
 
    static void consultarDiseñadores(Statement stmt) throws SQLException {

    String sql = "SELECT * FROM diseñador";

    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {

        int id = rs.getInt("id_diseñador");

        String nombre = rs.getString("nombre");

        String apellidoPaterno = rs.getString("apellido_paterno");

        String apellidoMaterno = rs.getString("apellido_materno");

        String nacionalidad = rs.getString("nacionalidad");

        String sexo = rs.getString("sexo");

        String fechaNacimiento = rs.getString("fecha_nacimiento");

        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidoPaterno + " " + apellidoMaterno + ", Nacionalidad: " + nacionalidad + ", Sexo: " + sexo + ", Fecha de nacimiento: " + fechaNacimiento);

    }

    rs.close();

}
 
static void consultarMarcas(Statement stmt) throws SQLException {

    String sql = "SELECT * FROM marca";

    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {

        int id = rs.getInt("id_modelo");

        String nombre = rs.getString("nombre");

        int año = rs.getInt("año");

        int idDiseñador = rs.getInt("diseñador");

        double precio = rs.getDouble("precio");

        String combustible = rs.getString("combustible");

        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Año: " + año + ", ID Diseñador: " + idDiseñador + ", Precio: " + precio + ", Combustible: " + combustible);

    }

    rs.close();

}
 
static void consultarAutos(Statement stmt) throws SQLException {

    String sql = "SELECT * FROM auto";

    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {

        String placas = rs.getString("placas");

        int idMarca = rs.getInt("marca");

        String niv = rs.getString("niv");

        int llantas = rs.getInt("llantas");

        int asientos = rs.getInt("asientos");

        String color = rs.getString("color");

        String tipoAuto = rs.getString("tipo_auto");

        int noPuertas = rs.getInt("no_puertas");

        String transmision = rs.getString("transmision");

        int noCilindros = rs.getInt("no_cilindros");

        System.out.println("Placas: " + placas + ", ID Marca: " + idMarca + ", NIV: " + niv + ", Llantas: " + llantas + ", Asientos: " + asientos + ", Color: " + color + ", Tipo de auto: " + tipoAuto + ", No. de puertas: " + noPuertas + ", Transmisión: " + transmision + ", No. de cilindros: " + noCilindros);

    }

    rs.close();

}
 
    static void eliminarDiseñador(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("ID del diseñador a eliminar: ");

    int id = scanner.nextInt();

    String sql = "DELETE FROM diseñador WHERE id_diseñador = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, id);

    statement.executeUpdate();

    System.out.println("Diseñador eliminado correctamente.");

}
 
static void eliminarMarca(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("ID de la marca a eliminar: ");

    int id = scanner.nextInt();

    String sql = "DELETE FROM marca WHERE id_modelo = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, id);

    statement.executeUpdate();

    System.out.println("Marca eliminada correctamente.");

}
 
static void eliminarAuto(Connection conn, Scanner scanner) throws SQLException {

    System.out.print("Placas del auto a eliminar: ");

    String placas = scanner.nextLine();

    String sql = "DELETE FROM auto WHERE placas = ?";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setString(1, placas);

    statement.executeUpdate();

    System.out.println("Auto eliminado correctamente.");

}

}

