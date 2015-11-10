
package com.mygdx.game.model.message;

import java.io.Serializable;
import com.mygdx.game.model.message.NoiseListener;

import com.badlogic.gdx.math.Vector2;

/**
 * Esta clase representa todos los sonidos emitidos por el jugador y los NPCs
 * Cada disparo, o el jugador corriendo, deben emitir un sonido. Los sonidos
 * poseen una fuente, un rango de alcance y un tipo.
 * 
 * @see NoiseListener
 * @see NoiseController
 */

public class Noise implements Message<NoiseListener>, Comparable<Noise>, Serializable {
	private Vector2 source;
	private double effectiveRange;
	private NoiseType type;

	/**
	 * @param source
	 *            - Posición fuente del sonido
	 * @param effectiveRange
	 *            - Rango de escucha
	 * @param type
	 *            - tipo de sonido
	 */
	public Noise(Vector2 source, double effectiveRange, NoiseType type) {
		this.source = source;
		this.effectiveRange = effectiveRange;
		this.type = type;
	}

	public Vector2 getPosition() {
		return new Vector2(this.source);
	}

	public double getRange() {
		return this.effectiveRange;
	}

	public NoiseType getType() {
		return type;
	}

	@Override
	public void notify(NoiseListener l) {
		if (NoiseType.SHOOT == type) {
			l.addNoise(this);
			return;
		} else if (NoiseType.WALK == type) {
			return;
		} else if (l.getPosition().dst(source) <= getRange()) {
			l.addNoise(this);
		}
	}

	@Override
	public int compareTo(Noise other) {

		if (this.getRange() > other.getRange()) {
			return 1;
		} else if (this.getRange() == other.getRange()) {
			return 0;
		} else {
			return -1;
		}

	}
}
