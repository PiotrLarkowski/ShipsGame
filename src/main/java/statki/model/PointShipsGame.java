package statki.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PointShipsGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pointId;
    private int x;
    private int y;

    @OneToMany(mappedBy = "localization")
    private List<ShipShipsGame> shipShipsGame;

    @ManyToOne
    private BoardShipsGame boardUserOne;

    @ManyToOne
    private BoardShipsGame boardUserTwo;

    @ManyToOne
    private GameHistoryShips gameHistoryShips;

    public PointShipsGame(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
