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
@Table(name = "Origen")
public class OrigenEntity {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@NonNull
	@Column(name = "ciudad")
	private String ciudad;
	
	@NonNull
	@Column(name = "pais")
	private String pais;
	
}
