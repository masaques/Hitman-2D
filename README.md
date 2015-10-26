# Hitman-2D
A Hitman game in 2D

***COMANDOS PARA GITHUB***
Comandos
 -commit (nombre,hash)
 -add
 -push
 -pull
 -merge o rebase

git push origin HEAD //en el branch con el que estabamos originalmente. 

git checkout -b Login //crea un nuevo branch que se llama LOGIN

Para mergear:
 git checkout master //voy al master
 git merge Login //lo que voy a mergear
 git push origin HEAD

Para rebase:
 git checkout Login
 git rebase master
 git checkout master
 git merge login //lo hacemos para que no tengamos conflictos
 git push origin

Para revertir un lio:
 *git checkout master //el checkout lo lleva a un commit -> el master es el último commit hecho
 *git checkout HEAD~1 //el HEAD es el ultimo commit del master, cuando le hago ~1 voy al anterior
 *...

Para revertir un lio local:
 *git reset HEAD~1
   **puedo hacerlo --soft o --hard -> Lo que hacemos es cambiarle el "instruction pointer" del árbol. El anterior al que era master, ahora es master 
  //Para aplicar los cambios remotamemte hacemos
 *git push -f

***
 git status //diferencia entre el ultimo commit y el ultimo estado actual local
***
 git checkout --ours --theirs *nombreDelArchivo* //para resolver conflictos mucho mas graves.Descarto los cambios de uno y dejo solo los que funciona
