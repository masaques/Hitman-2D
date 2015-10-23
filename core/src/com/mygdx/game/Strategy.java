/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

/*
 * Interfaz para los distintas estrategias que adoptan los npc.
 */
public interface Strategy {
	/*
	 * Metodo para tomar el control del npc, a base de un contexto determinado.
	 * @param context.
	 */
	public ActionRequest behave(Context context);
	
	/*
	 * Metodo para saber si la estrategia no tiene mas acciones para el npc.
	 * 
	 */
	public boolean done();
}
