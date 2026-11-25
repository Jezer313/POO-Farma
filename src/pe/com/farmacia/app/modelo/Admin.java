package pe.com.farmacia.app.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private int id_administrador;
    private String usuario_admin;
    private String contra_admin;

    public Admin(String usuario_admin, String contra_admin) {
        this.usuario_admin = usuario_admin;
        this.contra_admin = contra_admin;
    }
}
