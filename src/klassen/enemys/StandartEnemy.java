package klassen.enemys;

import java.awt.Rectangle;
import java.util.LinkedList;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;

public class StandartEnemy extends Enemy{

    public StandartEnemy(float x, float y, int speed, LinkedList<PlayerSpritzer> playerSpritzers, Player player, Rectangle bounding) {
        super(x, y, speed, playerSpritzers, player, bounding);
        
        
    }
    
    
    
}
