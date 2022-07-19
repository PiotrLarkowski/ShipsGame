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
public class GameHistoryShips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameHistoryId;

    @OneToOne(mappedBy = "gameHistoryShips")
    private BoardShipsGame boardShipsGame;

    private boolean isUserOneTurn;

    @OneToMany(mappedBy = "gameHistoryShips")
    private List<PointShipsGame> shootsHistory;

    private int userOneResult;
    private int userTwoResult;
}
