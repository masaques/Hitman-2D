# Hitman-2D
A Hitman game in 2D
Comandos
 -commit (nombre,hash)
 -add
 -push
 -pull
 -merge o rebase

git push origin HEAD //en el branch con el que estabamos originalmente. 

git checkout -b Login //crea un nuevo branch que se llama LOGIN

Para mergear:
 *git checkout master //voy al master
 //git checkout Login
 //git pull origin HEAD -> para revisar que otro no me pusheo algo en el branch
 *git merge Login //lo que voy a mergear
 *git push origin HEAD

Para rebase:
 git checkout Login
 //tendria que commitear antes
 git rebase master
 git checkout master
 git merge login //lo hacemos para que no tengamos conflictos
 git push origin

Para revertir un lio:
 *git checkout master //el checkout lo lleva a un commit -> el master es el último commit hecho
 *git checkout HEAD~1 //el HEAD es el ultimo commit del master, cuando le hago ~1 voy al anterior
 *...

Para revertir un lio local:
 *git reset HEAD~1 //o el hash del commit?
   **puedo hacerlo --soft o --hard -> Lo que hacemos es cambiarle el "instruction pointer" del árbol. El anterior al que era master, ahora es master 
  //Para aplicar los cambios remotamemte hacemos
 *git push -f

***
 git status //diferencia entre el ultimo commit y el ultimo estado actual local
***
 git checkout --ours --theirs *nombreDelArchivo* //para resolver conflictos mucho mas graves.Descarto los cambios de uno y dejo solo los que funciona

git log -> todos los commits del branch en el que estoy

#TODO List

* Implementar balas y disparos.

* Hacer tests.

* Arreglar el movimiento de los npc.

* Implementar el control de la direccion del jugador por el mouse.

* Arreglar la maquina de estado.

* Menues.
  - Ya hice la clase MenuManager, que vendría a ser parte del Controller. La especificación está comentada en el .java
  - Hay que implementar la clase MenuManagerView, que es puramente libgdx
  - Implementar la función "pausa" durante el juego

* Guardar las jugadas
  - WIP, estoy viendo el tema de como guardar el mapa

* Inicializacion de niveles.
  - Se realizara junto al guardado de jugadas

* Implementar levelManager que inicialice y controle el juego.
  - Idem anterior

