# Hitman-2D
A Hitman game in 2D

# PARA CLONEAR
* De NO tener instalada la herramienta de integración de Gradle con Eclipse(Pasar al paso siguiente de tenerla) :
- Help/Install new software
- En work with introducir el siguiente link: http://dist.springsource.com/snapshot/TOOLS/gradle/nightly
- Add/OK/Next
- Aceptar los terminos de la licencia. Eclipse se reiniciara automáticamente.

* Una vez instalada dicha herramienta:
- File/Import/Projects from Git/Clone URI
- Introducir el URI del repositorio y clonear como proyecto de Eclipse.
- Una vez finalizado el cloneado, Eclipse identficará automáticamente al proyecto como proyecto de Gradle.
- Para correr el programa, correr DesktopLauncher.java en Hitman-2D-desktop

#COMANDOS

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

* Hacer tests.

* Menues.
  - Casi terminado, Yo (masaques) voy a ver mañana lo ultimo que falta: que aparezcan carteles al ganar o al perder

* Terminado
  - Estas son las ultimas dos cosas que faltan. Luego hay que ver detalles de prolijidad
