package fruteria.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Item")
public class ItemEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "marcaId")
	private MarcaEntity marcaId;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "fechaProduccion")
	private LocalDate fechaProduccion;

	@Column(name = "fechaCaducidad")
	private LocalDate fechaCaducidad;

	@Column(name = "precio")
	private Double precio;

	@OneToOne
	@JoinColumn(name = "origenId")
	private OrigenEntity origenId;

	@OneToOne
	@JoinColumn(name = "categoriaId")
	private CategoriaEntity categoriaId;

}
