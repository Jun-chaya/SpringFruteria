package fruteria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LineaAlbaran")
public class LineaAlbaranEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "albaranId")
	private AlbaranEntity albaranId;
	
	@NonNull
	@OneToOne
	@Column(name = "itemId")
	private String itemId;
	
	@Column(name = "cantidad")
	private Integer cantidad;
}
