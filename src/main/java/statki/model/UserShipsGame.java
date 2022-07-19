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
public class UserShipsGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "userOne")
    private List<BoardShipsGame> boardShipsGame;

    @OneToMany(mappedBy = "userTwo")
    private List<BoardShipsGame> boardShipsGame2;
    private String userName;
    private int userTotalScore;

    public UserShipsGame(String userName) {
        this.userName = userName;
    }
}
