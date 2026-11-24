package pe.com.farmacia.app.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author «Equipo Alfa Buena Maravilla Onda Dinamita Escuadrón Lobo»
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Farmaceutico extends Persona{
    private int id_farmaceutico;
    
    public Farmaceutico(int id_farmaceutico, String dni, String nombre,
                        String apellido, String telefono, String direccion) {
        super(dni, nombre, apellido, telefono, direccion);
        this.id_farmaceutico = id_farmaceutico;
    }
}
