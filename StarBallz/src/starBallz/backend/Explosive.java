package starBallz.backend;

import starBallz.backend.explosion.Engine;
import javafx.scene.paint.Color;

public interface Explosive {

	public void setExplosion(Engine engine, int x, int y, int maxParticle,Color color);
}
