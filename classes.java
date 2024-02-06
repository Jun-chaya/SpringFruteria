import javax.persistence.*;
import lombok.*;

@Entity
@Data
public class Albaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proveedorId", nullable = false)
    private Proveedor proveedor;

    private String fecha;
}

@Entity
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "marcaId")
    private Marca marca;

    private String nombre;
    private String fechaProduccion;
    private String fechaCaducidad;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "origenId")
    private Origen origen;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
}

@Entity
@Data
public class LineaAlbaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "albaranId", nullable = false)
    private Albaran albaran;

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;

    private Integer cantidad;
}

@Entity
@Data
public class LineaRecibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reciboId", nullable = false)
    private Recibo recibo;

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;

    private Integer cantidad;
}

@Entity
@Data
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}

@Entity
@Data
public class Origen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ciudad;
    private String pais;
}

@Entity
@Data
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;
    private String direccion;
}

@Entity
@Data
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    private String fecha;
}
