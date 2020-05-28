package soliditycompiler;
import static soliditycompiler.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%
int |
int2 |
int4 |
int8 |
int16 |
int32 |
int64 |
int128 |
int256 |
if |
else |
address |
as |
bool |
break |
bytes |
bytes2 |
bytes4 |
bytes8 |
bytes16 |
bytes32 |
bytes64 |
bytes128 |
bytes256 |
byte |
byte2 |
byte4 |
byte8 |
byte16 |
byte32 |
byte64 |
byte128 |
byte256 |
constructor |
continue |
contract |
delete |
do |
else |
enum |
false |
for |
from |
function |
hex |
import |
internal |
mapping |
moddifier |
payable |
Pragma |
private |
public |
return |
returns |
solidity |
string |
struct |
this |
true |
ufixed |
uint |
uint2 |
uint4 |
uint8 |
uint16 |
uint32 |
uint64 |
uint128 |
uint256 |
var |
view |
while {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
balance |
call |
callcode |
delgatecall |
send |
transfer {lexeme=yytext(); return Transac;}
days |
ether |
finney |
hours |
minutes |
seconds |
szabo |
weeks |
wei |
years {lexeme=yytext(); return Units;}
("(-"{D}+"."{D}*")")| ("(-."{D}+")")| {D}*"."{D}+ | {D}+"."{D}* {lexeme=yytext(); return Flotante;}
"//".* {/*Ignore*/}
"!" {return Not;}
"&&" {return And;}
"^" {return Potencia;} //No estoy seguro por favor revisar
"==" {return Comparacion;}
"!=" {return Diferencia;}
"||" {return Or;} //No me acuerdo por favor revisar
"<=" {return MenorOIgualQue;}
"<<"  {return CorrerALaIzquierda;} //No estoy seguro por favor revisar
">>" {return CorrerALaDerecha;} //No estoy seguro por favor revisar
"<" {return MenorQue;}
">=" {return MayorOIgualQue;}
">" {return MayorQue;}
"&" {return And;} 
"|" {return Or;}
"~" {return Not;} //No estoy seguro por favor revisar
"%" {return Porcentaje;} //No estoy seguro por favor revisar
"**"  {return Potencia;}
"+="  {return Sume;} //No estoy seguro por favor revisar
"-=" {return Reste;} //No estoy seguro por favor revisar
"*=" {return Multiplique;} //No estoy seguro por favor revisar
"/=" {return Divida;} //No estoy seguro por favor revisar
"(" {return AbreParentesis;}
")" {return CierraParentesis;}
"[" {return AbreCorchete;}
"]" {return CierraCorchete;}
"?" {return Interrogacion;} //No estoy seguro por favor revisar
":" {return DosPuntos;} //No estoy seguro por favor revisar
";" {return PuntoYComa;}
"," {return Coma;}
"=" {return Asignacion;}
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}