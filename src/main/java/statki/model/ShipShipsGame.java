package statki.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import statki.ship.ShipDirection;

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

    Enum<ShipDirection> shipDirectionEnum;
    @ManyToOne
    private PointShipsGame localization;

    public ShipShipsGame(int shipSize, PointShipsGame localization ,boolean shipDestroyed, Enum<ShipDirection> shipDirectionEnum) {
        this.shipSize = shipSize;
        this.shipDestroyed = shipDestroyed;
        this.shipDirectionEnum = shipDirectionEnum;
        this.localization = localization;
    }
}
