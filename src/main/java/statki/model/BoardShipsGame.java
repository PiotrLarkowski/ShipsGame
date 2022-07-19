package statki.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity//to wskazuje, że klasa bedzie encją
@Getter
@Setter
@NoArgsConstructor//po co jest NoArgsConstructor? dopytać
@ToString
public class BoardShipsGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserShipsGame userOne;
    @ManyToOne
    private UserShipsGame userTwo;

    @OneToMany(mappedBy = "boardUserOne")
    private List<PointShipsGame> playBoardUserOne;

    @OneToMany(mappedBy = "boardUserTwo")
    private List<PointShipsGame> playBoardUserTwo;

    @OneToOne
    private GameHistoryShips gameHistoryShips;
}
