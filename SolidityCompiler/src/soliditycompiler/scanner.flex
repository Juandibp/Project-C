package soliditycompiler;
import static soliditycompiler.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
simbolo=[\\,/,!,;,#,$,%,=,?,¡,¿,|,_,-]+
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
"/**" ("\n*"|.)* "*/" {return ComentarioBloque;}
"/**" ("\n*"|.)* {return Error;}
"/**" (.|"\n")*  {return ERROR;}
({D}+ | "."{D}+  | {D}* "." {D}+ | {D}+ "." {D}*) "e" ("-"{D}+|{D}+) {return Cientifico;}
({D}+"."{D}*)| ({D}*"."{D}+)  {lexeme=yytext(); return Flotante;}
"//".* {/*Ignore*/}
"!" {return Not;}
"&&" {return And;}
"^" {return Potencia;} //Revisado, funciona
"==" {return Comparacion;}
"!=" {return Diferencia;}
"||" {return Or;} //Revisado, funciona
"<=" {return MenorOIgualQue;}
"<<"  {return CorrerALaIzquierda;} //Revisado, funciona
">>" {return CorrerALaDerecha;} //Revisado, funciona
"<" {return MenorQue;}
">=" {return MayorOIgualQue;}
">" {return MayorQue;}
"&" {return And;} 
"|" {return Or;}
"~" {return Not;} //Revisado, funciona
"%" {return Porcentaje;} //Revisado, funciona
"**"  {return Potencia;}
"+="  {return Sume;} //Revisado, funciona
"-=" {return Reste;} //Revisado, funciona
"*=" {return Multiplique;} //Revisado, funciona
"/=" {return Divida;} //Revisado, funciona
"(" {return AbreParentesis;}
")" {return CierraParentesis;}
"[" {return AbreCorchete;}
"]" {return CierraCorchete;}
"{" {return AbreLlave;}
"}" {return CierraLlave;}
"?" {return Interrogacion;} //Revisado, funciona
":" {return DosPuntos;} //Revisado, funciona
";" {return PuntoYComa;}
"." {return Punto;}
"=" {return Asignacion;}
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
("hex\""|"hex'") ( {D} | "A" | "B" | "C" | "D" | "E" | "F" )+ ("\""|"'")("\\n")* {return Hexadecimal;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* ("\""|"'") {return String;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | "\\".| {simbolo} | " ")* ("\""|"'") { return ERROR;}
("\""|"'") ({L}|{D} | "\\n" | "\\xNN" | "\\uNNNN" | {simbolo} | " ")* { return ERROR;}
"\\n" | "\\xNN" | "\\uNNNN" | "\\xNN" {return Escape;}
{D}+ {lexeme=yytext(); return Numero;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
{D}({L}|{D})* { return ERROR;}
{L}+ {simbolo} ( {simbolo}|{L})* { return ERROR;}
 . {return ERROR;}