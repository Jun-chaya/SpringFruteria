package fruteria.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class AbstractItem {
	
	@Id
	@Column(name = "id")
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

}
