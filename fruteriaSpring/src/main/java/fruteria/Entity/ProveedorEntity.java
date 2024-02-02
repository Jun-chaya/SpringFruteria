package fruteria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Proveedor")
public class ProveedorEntity {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@NonNull
	@Column(name = "nombre")
	private String nombre;
	
	@NonNull
	@Column(name = "precio")
	private Double precio;
	
	@NonNull
	@Column(name = "direccion")
	private String direccion;
}
