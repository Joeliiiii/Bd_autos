package GUI;
 
import Base.BaseDeDatos;
import control.Control;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class GUI {
    public static void main(String[] args) {
        try {
            // Obtener la conexión a la base de datos desde la clase BaseDeDatos
            Connection conn = BaseDeDatos.getConnection();
            // Crear un objeto Statement a partir de la conexión
            Statement stmt = conn.createStatement();
            // Llamar al método setConnection con la conexión y el objeto Statement
            setConnection(conn, stmt);
            // Crear un objeto Scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);
            // Mostrar el menú principal
            mostrarMenu(scanner);
            // Cerrar la conexión al salir
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private static Connection conn; // Variable de clase para la conexión a la base de datos
    private static Statement stmt; // Variable de clase para el objeto Statement
 
    // Método para establecer la conexión a la base de datos
    public static void setConnection(Connection connection, Statement statement) {
        conn = connection;
        stmt = statement;
    }
 
    public static void mostrarMenu(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Agregar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Consultar");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
 
            int opcionPrincipal = scanner.nextInt();
            scanner.nextLine();
 
            switch (opcionPrincipal) {
                case 1:
                    Control.menuAgregar(conn, scanner);
                    break;
                case 2:
                    Control.menuModificar(conn, scanner);
                    break;
                case 3:
                    Control.menuEliminar(conn, scanner);
                    break;
                case 4:
                    Control.menuConsultar(stmt, scanner);
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        }
 
        System.out.println("¡Adiós!");
    }
}