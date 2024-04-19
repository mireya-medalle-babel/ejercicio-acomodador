## ACOMODADOR AUTOMÁTICO

Tenemos 9 filas con 10 butacas por fila. Nuestro teatro no tiene pasillos al medio, y las butacas centrales son la 1 y la 2, estando a la izquierda los impares y a la derecha los pares. <br />

PANTALLA <br />
[9][7][5][3][1][2][4][6][8][10] 1  <br />
[9][7][5][3][1][2][4][6][8][10] 2 <br />
[9][7][5][3][1][2][4][6][8][10] 3 <br />
[9][7][5][3][1][2][4][6][8][10] 4 <br />
[9][7][5][3][1][2][4][6][8][10] 5 <br />
[9][7][5][3][1][2][4][6][8][10] 6 <br />
[9][7][5][3][1][2][4][6][8][10] 7 <br />
[9][7][5][3][1][2][4][6][8][10] 8 <br />
[9][7][5][3][1][2][4][6][8][10] 9 <br />

En caso de vender más entradas conjuntas debemos buscar la manera de que el sistema nos permita asignarlas mejores butacas libres.
Las mejores butacas serán aquellas que están juntas, lo más cerca del escenario/pantalla y lo más centradas posibles.
La aplicación debe pintar por consola la situación del teatro: butacas libres, butacas ocupadas y butacas reservadas.
Las butacas reservadas son las asignadas al solicitar butacas/entradas.

Para ello, la lógica que he empleado es la siguiente: 

Comprobar que se pueden reservar en la fila más cercana (hay suficientes butacas libres juntas) <br />
Si se puede: Elegir el sitio más centrado: <br />
si la fila está completamente libre, los sitios más centrados se corresponden con el número de sitio de menor a mayor <br />
(entre el 3 y el 4 daría igual si se piden 3 asientos, pero por defecto asignará el 3).<br />
    
si la fila está ya ocupada, el sitio más centrado corresponde con el número de sitio vacío menor. <br />
asignar ahí el primer asiento y, dependiendo de si es par o impar, como los acompañantes deben ir juntos, <br />
se asignan a su izquierda (si es impar) o a su derecha (si es par). <br />
      
Si no se puede: Pasar a la siguiente fila <br />

 
