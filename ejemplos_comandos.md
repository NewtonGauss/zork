# Ejemplo de uso de comandos

Simbologia:

$ lo que el jugador escribe.
& posible salida.

### Info

$ info  

Deberá aparecer una salida por pantalla que indique la información de la
historia. Esta está especificada en el json.

### Help

$ ayuda

La salida dirá lo que el jugador puede hacer, esto es, se mostrará cuales son
los comandos que el juego permite (tomar, atacar, etcétera).

### Quit

$ quit

Hace que el jugador salga del juego.

### Save

$ save

Guarda el juego. La salida dirá que se guardó o algo así.

### Score

$ puntaje

Se le muestra al jugador el puntaje.

### Movimientos

$ movimientos

Se le muestra al jugador cuantos movimientos efectuó, esto es, cuantos comandos
se ejecutaron.

### Inventario

$ mostrar inventario

$ inventario

Se le muestra al jugador una lista de su inventario.

### Diagnose

$ diagnosticar

Se le muestra al jugador en palabras el estado de su vida.

### Look

$ mirar

$ mirar alrededor

$ mirar habitación

$ mirar al pirata fantasma
& '¡No puedes pasar!' El pirata fantasma no te dejará pasar.

Se le muestra al jugador la descripción de la habitación.

### Attack with

$ atacar al pirata fantasma con la barreta

$ atacar a la pantera con el telescopio
& no se puede atacar con el telescopio (porque no es un arma).

$ atacar a los elefantes con las shurikens

La sintaxis seria: atacar {enemigo} con {item}. No se podrá atacar sin un item.

### Dar

$ dar al pirata fantasma el rociador con cerveza de raiz

Se le puede dar cualquier item a los npcs. Esto no hace que salte el 
trigger. Para que salte el trigger se debe seguir especificamente una 
accion (ataque, charla o usar)

$ dar al rociador con cerveza de raiz el pirata fantasma
& no tiene el objeto pirata fantasma

La sintaxis sería: dar {npc} {objeto}

### Tomar

$ tomar el rociador con cerveza de raiz  


$ tomar el pirata fantasma
& ese objeto no se encuentra en esta habitación

### Soltar

$ soltar la barreta

Se eliminará el item del inventario (y quizás se pone en el mapa).

### Caminar/ir

$ ir hacia el norte

$ ir hacia arriba

$ ir arriba

$ ir al norte

Se moverá el jugador hacia la siguiente habitación.

### Hablar

$ hablar con pirata fantasma
& ¡No hay nada que me digas que me haga cambiar de opinión!

Mostrará el dialogo del npc. La sintaxis es: hablar con {npc}. Hay que tener 
en cuenta que esto puede soltar un trigger del npc.

### Usar

$ usar pocion curativa
& se uso pocion curativa.

$ usar pocion curativa en pirata fantasma
& eso no ha servido de nada

Utilizar un objeto sobre uno mismo o sobre un npc. Si se usa sobre un npc, se debera
tener en cuenta los triggers del npc. Si se usa sobre uno mismo, hay que ver de que 
tipo de item se trata para ver si cura, daña o no hace nada. Al final del efecto se 
elimina el item (tan solo si hizo efecto, si no sirvio, se conserva).

