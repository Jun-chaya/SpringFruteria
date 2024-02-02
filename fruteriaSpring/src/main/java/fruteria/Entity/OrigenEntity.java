package fruteria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Origen")
public class OrigenEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "pais")
	private String pais;
	
}
