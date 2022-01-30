# service
Microservice with externalized module core

## How to use it?
- Request:
curl localhost:8080/users/1

- Response:
{"id":1,"name":"Juan"}

## Usos de @EnableJpaRepositories y @EntityScan
> Teniendo en cuenta como funciona el auto-scan de spring boot en busqueda de componentes, entities y reposities.

> ✅  Defini la estructura bases de paquetes igual entre tu proyecto de service y tu otro proyecto de core
> 
> Si en tu servicio, la estructura de paquetes alcanzado por el auto-scan de spring boot es:
> - com.example.service
> 
> Entonces tienen que verse asi la estructura base de paquetes de ambos proyectos:
>> Tu proyecto service: <span style="color:blue">com.example.service</span>
> 
>> Tu proyecto core: <span style="color:blue">com.example.service</span><span style="color:green">.core</span>  (Podes darle cualquier nombre "core" es un ejemplo y darle cualquier anidamiento dentro de "core")
 
> Con esa estructura de paquetes para spring boot "lo veria a" core como si estaria dentro del mismo base paquete para el auto-scan (aunque esten en proyectos separados)

> Unicamente vas a tener que anotar la clase con lo siguiente  
>> @EnableJpaRepositories
 
> ❌ En caso de no respetar esto tendras que indicarle a spring boot donde tiene que scanear en busqueda de entities 
> y repositories es decir,
> 
> Veamos con ejemplos: Supone que tenes la siguiente estructura base paquetes (XYZ hace que rompa la "logica" de autoscan)
>
>> Tu proyecto service: com.example.service
> 
>> Tu proyecto core: com.example.XYZ.core

>
> Vas a tener que anotar la clase con lo siguiente
>> `@EnableJpaRepositories("com.example.XYZ.repository")`  
>> `@EntityScan("com.example.XYZ.entity")`

> El auto-scan en busqueda de @Entity y @Repository de spring boot seria asi:
>
>- com (1. auto-scan)  
>  - example (2. auto-scan)  
>    - service (3. auto-scan se va por este camino y mira los hijos del paquete service)  
>       - ServiceAplication.java (clase principal)  
>  - XYZ (aqui no llega el auto-scan, este paquete cae fuera del auto-scan)  
>    - repository  
>    - entity


