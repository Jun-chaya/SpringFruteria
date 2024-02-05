package fruteria.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Item")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "marcaId")
	private MarcaEntity marca;

	private String nombre;
	private String fechaProduccion;
	private String fechaCaducidad;
	private Double precio;

	@ManyToOne
	@JoinColumn(name = "origenId")
	private OrigenEntity origen;

	@ManyToOne
	@JoinColumn(name = "categoriaId")
	private CategoriaEntity categoria;

}
