package pe.com.farmacia.app.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author USER
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String usuario_user;
    private String contra_user;
}
