# parser
This project is designed to parse a script like this, similar to what [velocity](https://velocity.apache.org/) can do.
```
#GET
#set($a = 1)
#if($a == 1)
    #set($price = 10)
#elif($a == 2)
    #set($price = 20)
#else($a == 3)
    #set($price = 30)
#end
#wait(50)
#for($elem=0;$elem<5;$elem=$elem+1)
    #GET
    #wait(10)
#end
```
current support statements
- set
- if elif else
- for
- wait (imitate sleep behavior)
- GET (send http get request)
