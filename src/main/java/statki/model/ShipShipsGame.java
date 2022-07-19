package statki.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShipShipsGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipId;
    private int shipSize;
    boolean shipDestroyed;
    @ManyToOne
    private PointShipsGame localization;

    public ShipShipsGame(int shipSize, PointShipsGame localization, boolean shipDestroyed) {
        this.shipSize = shipSize;
        this.localization = localization;
        this.shipDestroyed = shipDestroyed;
    }
}
